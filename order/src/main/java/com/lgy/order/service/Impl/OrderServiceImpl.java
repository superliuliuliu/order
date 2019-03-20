package com.lgy.order.service.Impl;

import com.lgy.order.converter.OrderMaster2OrderDto;
import com.lgy.order.dataobject.OrderDetail;
import com.lgy.order.dataobject.OrderMaster;
import com.lgy.order.dataobject.ProductInfo;
import com.lgy.order.dto.CartDto;
import com.lgy.order.dto.OrderDto;
import com.lgy.order.enums.OrderStatusEnum;
import com.lgy.order.enums.PayStatusEnum;
import com.lgy.order.enums.ResultEnum;
import com.lgy.order.exception.SellException;
import com.lgy.order.repository.OrderDetailRepository;
import com.lgy.order.repository.OrderMasterRepository;
import com.lgy.order.service.OrderService;
import com.lgy.order.service.ProductService;
import com.lgy.order.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    //新建订单的实现类 @Transactional事务操作 当抛出异常时数据库操作会回滚
    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        //首先从数据库中查询商品的数量和价格
        //计算总价
        //写入订单数据库(需要我们注意的是前端返回的订单详情信息 一般只有商品id与商品的数量信息) 需要写入订单详情库与订单总表
        //减少库存
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);
        for(OrderDetail orderDetail: orderDto.getOrderDetailList()){
            //首先从数据库中查出订单对应的商品
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                //抛出商品不存在的异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //将订单详情数据入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            //将商品信息的属性拷贝到订单详情项中  比如商品名称 商品价格等
            BeanUtils.copyProperties(productInfo, orderDetail);
            //计算订单总价
            orderAmount = orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            orderDetailRepository.save(orderDetail);
        }
        //接着将数据写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);

        //进行库存的更改
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e->
                new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());

        productService.decreaseStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }

    //查询订单列表
    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDto.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtoList, pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    /**
     * CANCEL
     * @description 修改数据库中该订单的状态为CANCEL  需要注意的是订单取消之后订单对应的每个产品的库存应该在加回去 并删除响应的订单详情记录
     * @param orderDto
     * @return
     * @author liugaoyang
     * @date 2019/3/19 8:57
     * @version 1.0.0
     */
    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        //在修改订单状态之时 首先要查看订单的状态
        String orderId = orderDto.getOrderId();
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //查询并判断订单的状态 订单处于完结状态和取消状态的话不能取消
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确，已经完结的订单不能再取消并且不能重复取消同一个订单");
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }
        //修改订单状态 并根据结果来判定订单修改是否成功
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result == null) {
            log.error("【取消订单】订单更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //商品库存信息更新  首先判断是否有商品详情
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【取消订单】订单详情为空,orderDto={}", orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_NULL);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e->
                new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDtoList);

        //如果订单支付成功之后需要进行相关的退款工作
        if(orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        return orderDto;
    }

    /**
     * finish
     * @description 完结订单
     * @param orderDto
     * @return com.lgy.order.dto.OrderDto
     * @author liugaoyang
     * @date 2019/3/20 17:30
     * @version 1.0.0
     */
    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        //首先根据订单ID查询主订单信息
        String orderId = orderDto.getOrderId();
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //查询订单状态 不能重复将一个订单修改为完结状态 取消的订单和新建的订单可以修改为完结状态
        if(orderMaster.getOrderStatus().equals(OrderStatusEnum.FINISH.getCode())){
            log.error("【完结订单】订单已完结,无法执行其他修改操作");
            throw new SellException(ResultEnum.ORDER_ALREADY_FINISH);
        }
        orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result == null){
            log.error("【完结订单】完结订单失败");
            throw new SellException(ResultEnum.ORDER_FINISH_ERROR);
        }
        orderDto.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        return orderDto;
    }

    @Override
    @Transactional
    /**
     * paid
     * @description 对应订单的支付操作  将对应状态为修改为已支付状态
     * @param orderDto
     * @return com.lgy.order.dto.OrderDto
     * @author liugaoyang
     * @date 2019/3/19 9:05
     * @version 1.0.0
     */
    public OrderDto paid(OrderDto orderDto) {
        //在修改订单状态之时 订单dto对象中id属性不应该为空
        String orderId = orderDto.getOrderId();
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        return null;
    }
}

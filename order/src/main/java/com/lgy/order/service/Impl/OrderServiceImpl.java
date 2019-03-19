package com.lgy.order.service.Impl;

import com.lgy.order.dataobject.OrderDetail;
import com.lgy.order.dataobject.OrderMaster;
import com.lgy.order.dataobject.ProductInfo;
import com.lgy.order.dto.CartDto;
import com.lgy.order.dto.OrderDto;
import com.lgy.order.enums.ResultEnum;
import com.lgy.order.exception.SellException;
import com.lgy.order.repository.OrderDetailRepository;
import com.lgy.order.repository.OrderMasterRepository;
import com.lgy.order.service.OrderService;
import com.lgy.order.service.ProductService;
import com.lgy.order.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
            //计算订单总价
            orderAmount = orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //将订单详情数据入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            //将商品信息的属性拷贝到订单详情项中  比如商品名称 商品价格等
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //接着将数据写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMasterRepository.save(orderMaster);

        //进行库存的更改
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e->
                new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());

        productService.decreaseStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
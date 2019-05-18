package com.lgy.order.controller.Sell;

import com.lgy.order.model.VO.ResultVo;
import com.lgy.order.common.converter.OrderForm2OrderDto;
import com.lgy.order.model.dto.OrderDto;
import com.lgy.order.common.enums.ResultEnum;
import com.lgy.order.common.exception.SellException;
import com.lgy.order.model.form.OrderForm;
import com.lgy.order.service.BuyerService;
import com.lgy.order.service.OrderService;
import com.lgy.order.common.util.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BuyrOrderController
 * @description 买家端订单接口
 * 初步估计买家端须实现以下接口
 * 1.买家新建订单
 * 2.买家查询订单列表（及查看个人的历史订单信息）
 * 3.买家查看订单详情
 * 4.买家取消订单
 * @param 
 * @return 
 * @author liugaoyang
 * @date 2019/3/20 23:32
 * @version 1.0.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/buyer/order")
public class BuyerOrderController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private OrderService orderService;
    /**
     * create
     * @description 买家新建订单 返回给前端的vo里 只需有生成的订单id即可其他信息不需要返回 在这里以Map的形式返回订单数据
     * @param orderForm 需要进行校验的订单信息
     * @return com.lgy.order.model.VO.ResultVo
     * @author liugaoyang
     * @date 2019/3/21 7:54
     * @version 1.0.0
     */
    @PostMapping(value = "/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult){
        //检验提交的表单是否符合要求
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确 orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        //将orderform中的数据复制到orderDto中
        OrderDto orderDto = OrderForm2OrderDto.convert(orderForm);
        //判断订单中的购物车是否为空
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【创建订单】购物车为空 orderDto={}", orderDto);
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        //创建订单  一系列数据操作 数据库中生成了订单主记录与订单详情记录
        OrderDto result = orderService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());

        return ResultVoUtil.success(map);
    }

    /**
      * @description 订单API 返回用户的订单列表用于用户查询历史订单
      */
    @GetMapping(value = "/list")
    public ResultVo<List<OrderDto>> getOrderList(@RequestParam("openid") String openid,
                                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size", defaultValue = "6") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【订单列表查询】参数不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid, pageRequest);
        return ResultVoUtil.success(orderDtoPage.getContent());
    }

    /**
      * @description 返回订单的详情信息
      */
    @GetMapping(value = "/detail")
    public ResultVo<OrderDto> getOrderDetail(@RequestParam("openid") String openid,
                                             @RequestParam("orderId") String orderId){
        if(StringUtils.isEmpty(openid)|| StringUtils.isEmpty(orderId)){
            log.error("【订单详情查询】参数不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDto orderDto = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(orderDto);
    }

    /**
      * @description 取消订单
      */
    @PostMapping(value = "/cancel")
    public ResultVo<OrderDto> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        if(StringUtils.isEmpty(openid)|| StringUtils.isEmpty(orderId)){
            log.error("【订单详情查询】参数不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success();
    }

}

package com.lgy.order.controller;

import com.lgy.order.VO.ResultVo;
import com.lgy.order.converter.OrderForm2OrderDto;
import com.lgy.order.dataobject.OrderMaster;
import com.lgy.order.dto.OrderDto;
import com.lgy.order.enums.ResultEnum;
import com.lgy.order.exception.SellException;
import com.lgy.order.form.OrderForm;
import com.lgy.order.repository.OrderMasterRepository;
import com.lgy.order.service.OrderService;
import com.lgy.order.util.KeyUtil;
import com.lgy.order.util.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
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
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderService orderService;
    /**
     * create
     * @description 买家新建订单 返回给前端的vo里 只需有生成的订单id即可其他信息不需要返回 在这里以Map的形式返回订单数据
     * @param orderForm 需要进行校验的订单信息
     * @return com.lgy.order.VO.ResultVo
     * @author liugaoyang
     * @date 2019/3/21 7:54
     * @version 1.0.0
     */
    @RequestMapping(value = "/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult){
        //检验提交的表单是否符合要求
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确 orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = new OrderDto();
        //将orderform中的数据复制到orderDto中
        orderDto = OrderForm2OrderDto.convert(orderForm);
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



}

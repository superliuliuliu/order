package com.lgy.order.service.Impl;

import com.lgy.order.dto.OrderDto;
import com.lgy.order.enums.ResultEnum;
import com.lgy.order.exception.SellException;
import com.lgy.order.service.BuyerService;
import com.lgy.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        OrderDto orderDto = orderService.findOne(orderId);
        //忽略大小写进行比较
        if(!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】权限不足");
            throw new SellException(ResultEnum.ORDER_AUTH_ERROR);
        }
        return orderDto;
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto = findOrderOne(openid, orderId);
        if(orderDto == null){
            log.error("【取消订单】订单为空");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderService.cancel(orderDto);
        return orderDto;
    }
}

package com.lgy.order.service;


import com.lgy.order.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    //创建订单
    OrderDto create(OrderDto orderDto);

    //查询单个订单
    OrderDto findOne(String orderId);

    //查询订单(查询历史订单：通过用户id来表示、查询未支付订单)
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    //修改订单状态  订单已完成  订单已付款  取消订单
    //取消订单  对应修改订单的状态码即可
    OrderDto cancel(OrderDto orderDto);

    //完成订单
    OrderDto finish(OrderDto orderDto);

    //支付订单
    OrderDto paid(OrderDto orderDto);

}

package com.lgy.order.service;


import com.lgy.order.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    //创建订单
    OrderDto create(OrderDto orderDto);

    //查询单个订单
    OrderDto findOne(String orderId);

    //根据手机号码查询订单信息(支持模糊查询)
    Page<OrderDto> findListByPhone(String phone, Pageable pageable);

    //根据顾客姓名查询订单信息(支持模糊查询)
    Page<OrderDto> findListByName(String name, Pageable pageable);

    //查询订单(用户用来查询自己的历史订单：通过用户id来表示、查询未支付订单)
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    //卖家查询订单列表 用于卖家管理
    Page<OrderDto> findList(Pageable pageable);

    //取消订单  对应修改订单的状态码即可
    OrderDto cancel(OrderDto orderDto);

    //完成订单
    OrderDto finish(OrderDto orderDto);

    //支付订单
    OrderDto paid(OrderDto orderDto);

}

package com.lgy.order.service;

import com.lgy.order.model.dto.OrderDto;

public interface BuyerService {

    /**
      * @description 查询一个订单
      */
    OrderDto findOrderOne(String openid, String orderId);

    /**
      * @description 取消订单
      */
    OrderDto cancelOrder(String openid, String orderId);
}

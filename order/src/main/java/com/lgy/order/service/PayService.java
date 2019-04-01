package com.lgy.order.service;

import com.lgy.order.dto.OrderDto;

/**
 * PayService
 * @description  调用微信支付完成相关的支付操作
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/25 16:33
 * @version 1.0.0
 */
public interface PayService {


    void create(OrderDto orderDto);
}

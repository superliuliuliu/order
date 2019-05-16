package com.lgy.order.controller.Sell;

import com.lgy.order.dto.OrderDto;
import com.lgy.order.enums.ResultEnum;
import com.lgy.order.exception.SellException;
import com.lgy.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * PayController
 * @description 微信支付相关接口
 * @author liugaoyang
 * @date 2019/3/25 16:14
 * @version 1.0.0
 */

@Controller
@Slf4j
@RequestMapping(value = "/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/create")
    public String demo(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl){
        //微信支付订单流程 1.首先查询订单(订单是否存在)以及订单状态(订单是否支付)
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null){
            log.error("【微信支付】订单不存在");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //支付订单
        return null;
    }
}

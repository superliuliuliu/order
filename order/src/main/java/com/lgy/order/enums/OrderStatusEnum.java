package com.lgy.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * OrderStatusEnum
 * @description 订单状态枚举类
 * @param 
 * @return 
 * @author liugaoyang
 * @date 2019/3/17 11:38
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public enum  OrderStatusEnum {

    NEW(0, "新订单"),
    FINISH(1, "已完成的订单"),
    CANCEL(2, "订单已被取消");
    private Integer code;
    private String message;

}

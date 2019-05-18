package com.lgy.order.common.enums;

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
public enum  OrderStatusEnum implements CodeEnums{

    NEW(0, "新订单"),
    FINISH(1, "已完结"),
    CANCEL(2, "已取消");
    private Integer code;
    private String message;

}

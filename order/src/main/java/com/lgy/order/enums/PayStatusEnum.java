package com.lgy.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * PayStatusEnum
 * @description 订单支付状态
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/17 11:41
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum {

    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功");
    private Integer code;
    private String message;
}

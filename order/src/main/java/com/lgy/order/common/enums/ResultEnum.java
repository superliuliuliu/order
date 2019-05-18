package com.lgy.order.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ResultEnum
 * @description 结果枚举类 用来说明各种出错的信息
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/17 21:17
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    PARAM_ERROR(1, "参数异常"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(11, "商品库存错误"),

    PRODUCT_STATUS_ERROR(25, "商品状态错误"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATE_ERROR(14, "订单状态不正确"),

    ORDER_UPDATE_ERROR(15, "订单更新失败"),

    ORDER_DETAIL_NULL(16, "订单详情为空"),

    ORDER_FINISH_ERROR(17, "订单完结失败"),

    ORDER_ALREADY_FINISH(18, "订单重复完结"),

    ORDER_ALREADY_PAY(19, "订单重复支付"),

    ORDER_PAY_ERROR(20, "订单支付失败"),

    ORDER_TIMEOUT(21, "订单已过期"),

    CART_EMPTY(22, "购物车中无商品"),

    ORDER_AUTH_ERROR(23, "订单权限错误"),

    WECHAT_MP_ERROR(24, "微信授权出错"),
    ;

    private Integer code;

    private String message;
}

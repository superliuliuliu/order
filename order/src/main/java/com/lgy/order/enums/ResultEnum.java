package com.lgy.order.enums;

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

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存错误"),
    ;

    private Integer code;

    private String message;
}

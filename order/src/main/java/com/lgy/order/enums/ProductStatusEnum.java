package com.lgy.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatusEnum {

    UP(0, "在售"),
    DOWN(1, "下架");

    private Integer code;
    private String message;
}

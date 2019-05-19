package com.lgy.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * CartDto
 * @description 前端返回给后端所购买商品的信息 包括购买商品的ID以及商品的数量
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/17 22:44
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class CartDto implements Serializable {
    private static final long serialVersionUID = -8760616726065847620L;
    private String productId;

    private Integer productQuantity;
}

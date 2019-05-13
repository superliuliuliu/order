package com.lgy.order.form;

import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

/**
 *
 * @description 新增商品表单
 * @author liugaoyang
 * @date 2019/5/12 19:27
 * @version 1.0.0
 */
public class ProductForm {

    // 商品名称
    @NotEmpty(message = "商品名称必填")
    private String productName;

    // 图片URL
    @NotEmpty(message = "图片地址必填")
    private String productIcon;

    // 商品定价
    @NotEmpty(message = "商品定价必填")
    private BigDecimal productPrice;

    // 商品库存
    @NotEmpty(message = "商品库存必填")
    private Integer productStock;

    // 商品介绍
    @NotEmpty(message = "商品介绍必填")
    private String productDescription;

    // 所属类目
    @NotEmpty(message = "类目必填")
    private Integer categoryType;
}

package com.lgy.order.model.form;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 *
 * @description 新增商品表单
 * @author liugaoyang
 * @date 2019/5/12 19:27
 * @version 1.0.0
 */
@Data
public class ProductForm {

    // 编辑商品时使用 商品ID
    private String productId;

    // 商品名称
    @NotEmpty(message = "商品名称必填")
    private String productName;

    // 图片URL
    @NotEmpty(message = "图片地址必填")
    private String productIcon;

    // 商品定价
    private BigDecimal productPrice;

    // 商品库存
    private Integer productStock;

    // 商品介绍
    @NotEmpty(message = "商品介绍必填")
    private String productDescription;

    // 所属类目
    private Integer categoryType;
}

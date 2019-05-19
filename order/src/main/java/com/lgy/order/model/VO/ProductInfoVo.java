package com.lgy.order.model.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ProductInfoVo
 * @description  商品详细信息
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/16 23:22
 * @version 1.0.0
 */
@Data
public class ProductInfoVo  implements Serializable {

    private static final long serialVersionUID = 1236723270964151493L;
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

}

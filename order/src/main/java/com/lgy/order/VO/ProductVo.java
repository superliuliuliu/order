package com.lgy.order.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * ProductVo
 * @description  商品类别列表信息
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/16 23:22
 * @version 1.0.0
 */
@Data
public class ProductVo {

    //类别的名称
    @JsonProperty("name")
    private String categoryName;

    //类别编号
    @JsonProperty("type")
    private Integer categoryType;

    //类别列表信息
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;

}

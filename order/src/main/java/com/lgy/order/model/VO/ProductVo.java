package com.lgy.order.model.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
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
public class ProductVo implements Serializable {


    private static final long serialVersionUID = -8760279146944966792L;
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

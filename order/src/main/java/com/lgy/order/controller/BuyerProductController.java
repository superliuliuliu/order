package com.lgy.order.controller;

import com.lgy.order.VO.ProductInfoVo;
import com.lgy.order.VO.ProductVo;
import com.lgy.order.VO.ResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @RequestMapping(value = "/list")
    public ResultVo index(){
        ResultVo resultVo = new ResultVo();
        ProductVo productVo = new ProductVo();
        ProductInfoVo productInfoVo = new ProductInfoVo();

        productVo.setProductInfoVoList(Arrays.asList(productInfoVo));
        resultVo.setCode(0);
        resultVo.setMessage("成功");
        resultVo.setData(Arrays.asList(productVo));

        return resultVo;

    }
}

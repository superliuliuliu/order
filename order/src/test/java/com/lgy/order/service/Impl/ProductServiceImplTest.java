package com.lgy.order.service.Impl;

import com.google.gson.Gson;
import com.lgy.order.dataobject.ProductInfo;
import com.lgy.order.form.ProductForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("41040219971106");
        Assert.assertEquals("寂寞凉面", productInfo.getProductName());
    }

    @Test
    public void findOnAll() {
        List<ProductInfo> productInfoList = productService.findOnAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findAll() {
        //Pageable只是一个接口而不是一个类  pageRequest实现了该接口
        PageRequest pageRequest = new PageRequest(0,5);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        System.out.println(productInfoPage.getTotalElements());

    }

    @Test
    public void down(){
        ProductInfo productInfo = productService.findOne("41040219971106");
        productService.obtain(productInfo);
        System.out.println(productInfo);
    }

    @Test
    public void update(){
        ProductInfo productInfo = productService.findOne("41040219971106");
        ProductForm productForm = new ProductForm();
        BeanUtils.copyProperties(productInfo, productForm);
        productForm.setProductName("寂寞凉面修改测试");
        productService.update(productForm);

    }

    /**
      * @description 基本数据类型解析测试
      */
    @Test
    public void save() {
        Gson gson = new Gson();
        int i  = gson.fromJson("100", int.class);
        double d = gson.fromJson("99.99", double.class);
        boolean b = gson.fromJson("true", boolean.class);
        String str = gson.fromJson("string", String.class);
        log.info("【Gson测试】i={}, d={}, b={}, str={}", i, d, b, str);
    }

    /**
      * @description 基本数据类型生成
      */
    @Test
    public void save1() {
        Gson gson = new Gson();
        int[] values = {1,2};
        String str = gson.toJson(values);
        log.info("ceshi:{}",str);
    }

}
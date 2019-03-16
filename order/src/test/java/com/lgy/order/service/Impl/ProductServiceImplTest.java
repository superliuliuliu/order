package com.lgy.order.service.Impl;

import com.lgy.order.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
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
    public void save() {
    }
}
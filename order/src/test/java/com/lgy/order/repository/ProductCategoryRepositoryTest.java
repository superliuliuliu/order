package com.lgy.order.repository;

import com.lgy.order.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * saveTest
     * @description  测试
     * @param
     * @return
     * @author liugaoyang
     * @date 2019/3/16 19:29
     * @version 1.0.0
     */
    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("41040219971107");
        productInfo.setProductName("寂寞凉面2");
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("非常好吃的一款凉面再配上一份皮蛋瘦肉粥简直无法用言语来描述");
        productInfo.setProductIcon("https://osstestlgy.oss-cn-beijing.aliyuncs.com/default_handsome.jpg?x-oss-process=style/imgforhead");
        productInfo.setProductStatus(0);
        productInfo.setProductStock(10);
        productInfo.setProductPrice(new BigDecimal(8.0));
        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);

    }
}
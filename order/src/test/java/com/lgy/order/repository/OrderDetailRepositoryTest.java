package com.lgy.order.repository;

import com.lgy.order.DO.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("201909171623");
        orderDetail.setOrderId("41040219975559");
        orderDetail.setProductId("41040219971109");
        orderDetail.setProductName("虾仁寿司");
        orderDetail.setProductIcon("https://osstestlgy.oss-cn-beijing.aliyuncs.com/default_handsome.jpg?x-oss-process=style/imgforhead");
        orderDetail.setProductPrice(new BigDecimal(24));
        orderDetail.setProductQuantity(1);
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }
}
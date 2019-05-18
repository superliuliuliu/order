package com.lgy.order.repository;

import com.lgy.order.DO.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


/**
 * OrderMasterRepositoryTest
 * @description 主订单信息的测试类
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/17 15:53
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderMasterRepositoryTest.class);

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("41040219975559");
        orderMaster.setBuyerName("刘高阳");
        orderMaster.setBuyerAddress("四川省成都市电子科技大学清水河校区学生公寓19栋406");
        orderMaster.setBuyerPhone("15837562085");
        orderMaster.setOrderAmount(new BigDecimal(64));
        orderMaster.setBuyerOpenid("734190426");
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenidTest(){
        PageRequest pageRequest = new PageRequest(0,5);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid("734190426", pageRequest);
        logger.info("总共的数据量：" + result.getTotalElements());
    }

    @Test
    public void findByPhoneTest(){
        PageRequest pageRequest = new PageRequest(0,5);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerPhone("15837562085", pageRequest);
        logger.info("总共的数据量：" + result.getTotalElements());
    }

}
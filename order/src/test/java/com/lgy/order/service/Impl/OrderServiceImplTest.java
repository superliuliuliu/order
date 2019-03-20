package com.lgy.order.service.Impl;

import com.lgy.order.dataobject.OrderDetail;
import com.lgy.order.dto.OrderDto;
import com.lgy.order.repository.OrderMasterRepositoryTest;
import com.lgy.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderServiceImplTest.class);

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        //在这里模拟测试前端想后端返回订单的数据信息
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerAddress("河南省平顶山市新华区新七街");
        orderDto.setBuyerName("刘倩");
        orderDto.setBuyerPhone("13781846189");
        orderDto.setBuyerOpenid("734190427");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("41040219971108");
        o1.setProductQuantity(2);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("41040219971109");
        o2.setProductQuantity(1);
        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderService.create(orderDto);
        logger.info("【创建订单】 result={}", result);
    }

    @Test
    public void findOne() {
        OrderDto orderDto = orderService.findOne("3233791552922032297");
        logger.info("【查询订单】 order={}", orderDto);
    }

    @Test
    public void findList() {
        Page<OrderDto> orderDTOPage = orderService.findList("734190427", new PageRequest(0,5));
        logger.info("result:{}" , orderDTOPage);
    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne("41040219975559");
        orderDto = orderService.cancel(orderDto);
        logger.info("【取消订单】 order={}", orderDto);
    }

    @Test
    public void finish() {
        OrderDto orderDto = orderService.findOne("41040219975559");
        orderDto = orderService.finish(orderDto);
        logger.info("【完结订单】 order={}", orderDto);
    }

    @Test
    public void paid() {
    }
}
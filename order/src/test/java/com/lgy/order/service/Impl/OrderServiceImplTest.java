package com.lgy.order.service.Impl;

import com.lgy.order.model.DO.OrderDetail;
import com.lgy.order.model.dto.OrderDto;
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
        orderDto.setBuyerAddress("四川省成都市成华区建设北路东二段");
        orderDto.setBuyerName("Tony");
        orderDto.setBuyerPhone("17120985690");
        orderDto.setBuyerOpenid("734190420");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("41040219971108");
        o1.setProductQuantity(1);
//        OrderDetail o2 = new OrderDetail();
//        o2.setProductId("41040219971106");
//        o2.setProductQuantity(1);
        orderDetailList.add(o1);
//        orderDetailList.add(o2);

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
        Page<OrderDto> orderDTOPage = orderService.findList("734190427", PageRequest.of(0,5));
        logger.info("result:{}" , orderDTOPage);
    }

    @Test
    public void findList2(){
        Page<OrderDto> orderDtoPage = orderService.findListByPhone("137",PageRequest.of(0,5));
        logger.info("result:{}" , orderDtoPage);
    }

    @Test
    public void findList3(){
        Page<OrderDto> orderDtoPage = orderService.findListByName("李", PageRequest.of(0,5));
        logger.info("result:{}" , orderDtoPage);
    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne("8561901553091610362");
        orderDto = orderService.cancel(orderDto);
        logger.info("【取消订单】 order={}", orderDto);
    }

    @Test
    public void finish() {
        OrderDto orderDto = orderService.findOne("8561901553091610362");
        orderDto = orderService.finish(orderDto);
        logger.info("【完结订单】 order={}", orderDto);
    }

    @Test
    public void paid() {
        OrderDto orderDto = orderService.findOne("2117491553092807239");
        orderDto = orderService.paid(orderDto);
        logger.info("【支付订单】 order={}", orderDto);
    }
}
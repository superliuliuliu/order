package com.lgy.order.dto;

import com.lgy.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * OrderDto
 * @description 该类用来传输数据对象
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/17 20:06
 * @version 1.0.0
 */
@Data
public class OrderDto {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /**订单状态 初始化为新订单状态*/
    private Integer orderStatus;

    /**支付状态 初始化为未支付状态*/
    private Integer payStatus;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**对应的是商品详情信息的ID*/
    List<OrderDetail> orderDetailList;
}
package com.lgy.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lgy.order.DO.OrderDetail;
import com.lgy.order.enums.OrderStatusEnum;
import com.lgy.order.enums.PayStatusEnum;
import com.lgy.order.util.EnumUtil;
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
//@JsonInclude(JsonInclude.Include.NON_NULL)//该注解的作用是 当返回的json数据中有值为空时不返回null的数据 不足之处是该注解只对单个对象有效为了批量配置 我们可以在配置文件中进行配置已实现批量操作
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

    /**根据订单数据库中存储的码值获取枚举类中的实体*/
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}

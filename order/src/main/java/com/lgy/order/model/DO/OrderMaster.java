package com.lgy.order.model.DO;

import com.lgy.order.common.enums.OrderStatusEnum;
import com.lgy.order.common.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@DynamicUpdate
@Data
@Entity
@Table(name = "order_master")
public class OrderMaster {

    @Id
    @Size(max = 32)
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_phone")
    private String buyerPhone;

    @Column(name = "buyer_address")
    private String buyerAddress;

    @Column(name = "buyer_openid")
    private String buyerOpenid;

    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    /**订单状态 初始化为新订单状态*/
    @Column(name = "order_status")
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**支付状态 初始化为未支付状态*/
    @Column(name = "pay_status")
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;


}

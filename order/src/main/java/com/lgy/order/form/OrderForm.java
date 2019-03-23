package com.lgy.order.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * OrderForm
 * 需提交：
 *  买家姓名
 *  买家手机号
 * @author liugaoyang
 * @version 1.0
 * @description 用户创建订单时需提交的表单验证信息
 * @date 2019/3/22 14:11
 **/
@Data
public class OrderForm {

    /**
      * @description 买家姓名
      */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
      * @description 买家手机号
      */
    @Size(max = 11,min = 11, message = "手机号不正确")
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
      * @description 买家地址
      */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
      * @description 买家微信号（不需要用户填写在提交订单时自动获取）
      */
    @NotEmpty(message = "买家微信id")
    private String openid;

    /**
      * @description 购物车信息
      */
    /*@NotEmpty(message = "购物车不能为空")*/
    private String items;

}

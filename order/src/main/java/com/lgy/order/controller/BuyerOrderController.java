package com.lgy.order.controller;

import com.lgy.order.VO.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BuyrOrderController
 * @description 买家端订单接口
 * 初步估计买家端须实现以下接口
 * 1.买家新建订单
 * 2.买家查询订单列表（及查看个人的历史订单信息）
 * 3.买家查看订单详情
 * 4.买家取消订单
 * @param 
 * @return 
 * @author liugaoyang
 * @date 2019/3/20 23:32
 * @version 1.0.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/buyer/order")
public class BuyerOrderController {

    /**
     * create
     * @description 买家新建订单 返回给前端的vo里 只需有生成的订单id即可其他信息不需要返回
     * @param
     * @return com.lgy.order.VO.ResultVo
     * @author liugaoyang
     * @date 2019/3/21 7:54
     * @version 1.0.0
     */
    @RequestMapping(value = "/create")
    public ResultVo create(){
        ResultVo resultVo = new ResultVo();
        return null;
    }



}

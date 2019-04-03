package com.lgy.order.controller;

import com.lgy.order.VO.ResultVo;
import com.lgy.order.dto.OrderDto;
import com.lgy.order.service.OrderService;
import com.lgy.order.util.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * SellerOrderController
 * @description 卖家端订单
 * @author liugaoyang
 * @date 2019/4/1 14:54
 * @version 1.0.0
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * list
     * @description 所有的订单数据列表  支持根据顾客姓名查找  根据顾客手机号查找订单信息
     * @param page 分页数据第几页 前端显示从第一页开始 而pageable中是包含0下标的
     * @param size 一页包含几条数据
     * @return org.springframework.web.servlet.ModelAndView
     * @author liugaoyang
     * @date 2019/4/1 14:56
     * @version 1.0.0
     */
    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "8") Integer size,
                             Map<String, Object> map){
        PageRequest pageRequest = new PageRequest(page - 1, size);
        // 查询的分页的主订单数据
        Page<OrderDto> orderDtoPage = orderService.findList(pageRequest);
        map.put("orderDtoPage", orderDtoPage);
        map.put("currentPage", page);
        return new ModelAndView("order/list", map);
    }

    /**
     * detail
     * @description 订单详情API 获取订单的详细商品信息(商品数量以及商品价格) 以sweetalert弹框的形式弹出订单的详细信息
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @author liugaoyang
     * @date 2019/4/1 15:30
     * @version 1.0.0
     */
    @GetMapping(value = "/detail")
    public ResultVo detail(@RequestParam(value = "orderId") String orderId){
        return ResultVoUtil.success();
    }

    /**
     * cancelOrder
     * @description 取消订单(卖家原因取消订单)
     * @param orderId 订单Id 订单的唯一标识
     * @return org.springframework.web.servlet.ModelAndView
     * @author liugaoyang
     * @date 2019/4/3 17:47
     * @version 1.0.0
     */
    @ResponseBody
    @GetMapping(value = "/cancel")
    public ResultVo cancelOrder(@RequestParam(value = "orderId") String orderId){
        return ResultVoUtil.success();
    }
}

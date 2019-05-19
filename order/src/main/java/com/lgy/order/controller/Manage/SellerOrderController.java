package com.lgy.order.controller.Manage;

import com.lgy.order.model.VO.ResultVo;
import com.lgy.order.model.DO.OperationResult;
import com.lgy.order.model.DO.OrderDetail;
import com.lgy.order.model.dto.OrderDto;
import com.lgy.order.common.exception.SellException;
import com.lgy.order.service.OrderService;
import com.lgy.order.common.util.ResultVoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
        PageRequest pageRequest = PageRequest.of(page - 1, size, new Sort(Sort.Direction.DESC, "updateTime"));
        // 查询的分页的主订单数据
        Page<OrderDto> orderDtoPage = orderService.findList(pageRequest);
        map.put("orderDtoPage", orderDtoPage);
        map.put("currentPage", page);
        return new ModelAndView("order/list", map);
    }

    /**
     * detail
     * @description 订单详情API 获取订单的详细商品信息(商品数量以及商品价格) 跳转到新的页面查看订单的信息
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @author liugaoyang
     * @date 2019/4/1 15:30
     * @version 1.0.0
     */
    @GetMapping (value = "/detail")
    public ModelAndView detail(@RequestParam(value = "orderId") String orderId,
                               Map<String, Object> map){
        //首先根据订单Id 查到orderDto  进而查到订单详情
        try{
            OrderDto orderDto = orderService.findOne(orderId);
            List<OrderDetail> orderDetailList = orderDto.getOrderDetailList();
            map.put("priceAmount", orderDto.getOrderAmount());
            map.put("buyer", orderDto.getBuyerName());
            map.put("orderDetailList", orderDetailList);
            map.put("orderDto", orderDto);
        }catch (SellException e){
            log.error("【订单查询】订单详情查询异常{}", e);
            map.put("error", e.getMessage());
            return new ModelAndView("order/error", map);
        }
        return new ModelAndView("order/detail", map);
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
    @PostMapping(value = "/cancel")
    public ResultVo cancelOrder(@RequestParam(value = "orderId") String orderId){
        try{
            OrderDto orderDto = orderService.findOne(orderId);
            orderService.cancel(orderDto);
        }catch (SellException e){
            log.error("【订单管理】取消订单异常{}",e);
            return ResultVoUtil.error(e.getCode(), e.getMessage());
        }
        return ResultVoUtil.success(new OperationResult(200, "成功取消订单"));
    }

    /**
     * complete
     * @description 完结订单
     * @param orderId  订单Id
     * @return com.lgy.order.model.VO.ResultVo
     * @author liugaoyang
     * @date 2019/4/7 21:10
     * @version 1.0.0
     */
    @ResponseBody
    @PostMapping(value = "/complete")
    public ResultVo complete(@RequestParam(value = "orderId") String orderId){
        try{
            OrderDto orderDto = orderService.findOne(orderId);
            orderService.finish(orderDto);
        }catch (SellException e){
            log.error("【订单管理】完结订单异常{}", e);
            return ResultVoUtil.error(e.getCode(), e.getMessage());
        }
        return ResultVoUtil.success(new OperationResult(200, "成功完结订单"));
    }


}

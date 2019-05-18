package com.lgy.order.common.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgy.order.model.DO.OrderDetail;
import com.lgy.order.model.dto.OrderDto;
import com.lgy.order.common.enums.ResultEnum;
import com.lgy.order.common.exception.SellException;
import com.lgy.order.model.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderForm2OrderDto
 *
 * @author liugaoyang
 * @version 1.0
 * @description
 * @date 2019/3/22 15:19
 **/
@Slf4j
public class OrderForm2OrderDto {
    private OrderForm2OrderDto() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderDto convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerName(orderForm.getName());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            //这里需要将json数据转换为object
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转化】:Json格式错误！ String={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //Json数据转换没问题后再进行相关操作
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}

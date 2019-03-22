package com.lgy.order.converter;

import com.lgy.order.dataobject.OrderMaster;
import com.lgy.order.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderMaster2OrderDto
 * @description 类型转换器将OrderMaster类转换为OrderDto
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/19 21:55
 * @version 1.0.0
 */

public class OrderMaster2OrderDto {

    private OrderMaster2OrderDto() {
        throw new IllegalStateException("Utility class");
    }

    private static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    //
    private static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e-> convert(e)).collect(Collectors.toList());
    }

}

package com.lgy.order.repository;

import com.lgy.order.model.DO.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    //根据订单id查询商品的详细信息
    List<OrderDetail> findByOrderId(String oderId);
}

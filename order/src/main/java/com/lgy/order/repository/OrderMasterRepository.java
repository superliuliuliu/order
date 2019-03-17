package com.lgy.order.repository;

import com.lgy.order.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    //根据购买者的微信号查询订单信息
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    //根据购买者的手机号查找购买信息
    Page<OrderMaster> findByBuyerPhone(String buyerPhone, Pageable pageable);
}

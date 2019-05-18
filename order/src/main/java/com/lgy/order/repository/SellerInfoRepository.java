package com.lgy.order.repository;

import com.lgy.order.model.DO.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenId(String openId);
}

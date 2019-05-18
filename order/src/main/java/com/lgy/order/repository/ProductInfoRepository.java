package com.lgy.order.repository;

import com.lgy.order.DO.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    //根据商品的状态查询商品信息
    List<ProductInfo> findByProductStatus(Integer productStatus);
}

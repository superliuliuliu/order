package com.lgy.order.service;

import com.lgy.order.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    //查询所有的商品
    Page<ProductInfo> findAll(Pageable pageable);

    //查询正在上架的所有商品(查询得到的数据可分页)
    List<ProductInfo> findOnAll();

    ProductInfo save(ProductInfo productInfo);

    //加库存  减库存 在用户购买商品时以及买家更新数据时使用
}

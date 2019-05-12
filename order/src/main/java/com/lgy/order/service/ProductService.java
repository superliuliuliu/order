package com.lgy.order.service;

import com.lgy.order.dataobject.ProductInfo;
import com.lgy.order.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    //查询所有的商品
    Page<ProductInfo> findAll(Pageable pageable);

    //查询正在上架的所有商品(查询得到的数据可分页)
    List<ProductInfo> findOnAll();

    // 修改商品信息
    ProductInfo save(ProductInfo productInfo);

    // 商品下架对应修改商品的状态码
    ProductInfo obtain(ProductInfo productInfo);

    ProductInfo Shelf(ProductInfo productInfo);

    //加库存  减库存 在用户购买商品时以及买家更新数据时使用  因为在同一时刻可能有多个用户提交了订单信息
    void increaseStock(List<CartDto> cartDtoList);

    void decreaseStock(List<CartDto> cartDtoList);
}

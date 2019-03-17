package com.lgy.order.service.Impl;

import com.lgy.order.dataobject.ProductInfo;
import com.lgy.order.dto.CartDto;
import com.lgy.order.enums.ProductStatusEnum;
import com.lgy.order.enums.ResultEnum;
import com.lgy.order.exception.SellException;
import com.lgy.order.repository.ProductInfoRepository;
import com.lgy.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findOnAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {
        for(CartDto cartDto: cartDtoList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            productInfo.setProductStock(productInfo.getProductStock() + cartDto.getProductQuantity());
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        for(CartDto cartDto: cartDtoList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }

    }
}

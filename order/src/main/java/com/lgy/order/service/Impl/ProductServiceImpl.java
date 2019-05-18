package com.lgy.order.service.Impl;

import com.lgy.order.model.DO.ProductInfo;
import com.lgy.order.model.dto.CartDto;
import com.lgy.order.common.enums.ProductStatusEnum;
import com.lgy.order.common.enums.ResultEnum;
import com.lgy.order.common.exception.SellException;
import com.lgy.order.model.form.ProductForm;
import com.lgy.order.repository.ProductInfoRepository;
import com.lgy.order.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
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
    public ProductInfo update(ProductForm productForm) {
        ProductInfo productInfo = productInfoRepository.findOne(productForm.getProductId());
        if (productInfo == null){
            log.error("【更新商品】商品ID错误");
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        BeanUtils.copyProperties(productForm, productInfo);
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public ProductInfo obtain(ProductInfo productInfo) {
        String productId = productInfo.getProductId();
        ProductInfo productInfo1 = productInfoRepository.findOne(productId);
        if (productInfo1 == null){
            log.error("【更新商品上下架】此商品不存在");
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo1.getProductStatus().equals(ProductStatusEnum.DOWN)){
            log.error("【更新商品上下架】商品已处于下架状态");
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo1.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfo1;
    }

    @Override
    @Transactional
    public ProductInfo Shelf(ProductInfo productInfo) {
        String productId = productInfo.getProductId();
        ProductInfo productInfo1 = productInfoRepository.findOne(productId);
        if (productInfo1 == null){
            log.error("【更新商品上下架】此商品不存在");
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo1.getProductStatus().equals(ProductStatusEnum.UP)){
            log.error("【更新商品上下架】商品已处于上架状态");
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo1.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfo1;
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

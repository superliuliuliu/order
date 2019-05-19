package com.lgy.order.service.Impl;

import com.lgy.order.model.DO.ProductCategory;
import com.lgy.order.repository.ProductCategoryRepository;
import com.lgy.order.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    @Cacheable(cacheNames = "productCategory", key = "001")
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @CachePut(cacheNames = "productCategory", key = "001")
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}

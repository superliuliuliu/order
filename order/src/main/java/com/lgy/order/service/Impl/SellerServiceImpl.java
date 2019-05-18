package com.lgy.order.service.Impl;

import com.lgy.order.DO.SellerInfo;
import com.lgy.order.repository.SellerInfoRepository;
import com.lgy.order.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;

public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenId(String openId) {
        return sellerInfoRepository.findByOpenId(openId);
    }
}

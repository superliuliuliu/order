package com.lgy.order.service;

import com.lgy.order.model.DO.SellerInfo;

public interface SellerService {


    /**
     * findSellerInfoByOpenId
     * @description 通过用户的微信OpenId 查询卖家信息
     * @param openId
     * @return com.lgy.order.model.DO.SellerInfo
     * @author liugaoyang
     * @date 2019/5/18 11:24
     * @version 1.0.0
     */
    SellerInfo findSellerInfoByOpenId(String openId);
}

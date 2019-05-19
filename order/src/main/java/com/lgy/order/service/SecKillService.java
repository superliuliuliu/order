package com.lgy.order.service;

/**
 *
 * @description 秒杀服务的相关操作
 * @author liugaoyang
 * @date 2019/5/18 20:52
 * @version 1.0.0
 */
public interface SecKillService {

    /**
     * querySecKillProductInfo
     * @description 查询秒杀商品的信息
     * @param productId 商品ID
     * @return java.lang.String
     * @author liugaoyang
     * @date 2019/5/18 20:54
     * @version 1.0.0
     */
    String querySecKillProductInfo(String productId);

    /**
     * orderProductMockDiffUser
     * @description 模拟不同用户秒杀同一商品的请求
     * @param productId 商品ID
     * @return void
     * @author liugaoyang
     * @date 2019/5/18 20:55
     * @version 1.0.0
     */
    void orderProductMockDiffUser(String productId);
}

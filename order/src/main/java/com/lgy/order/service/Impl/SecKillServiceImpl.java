package com.lgy.order.service.Impl;

import com.lgy.order.common.exception.SellException;
import com.lgy.order.common.util.KeyUtil;
import com.lgy.order.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SecKillServiceImpl implements SecKillService {

    // 用三个HashMap 分别作为商品信息 库存信息  订单信息的缓存
    static Map<String, Integer> products;
    static Map<String, Integer> stocks;
    static Map<String, String> order;

    // 静态代码块初始化内存
    static {
        // 商品选择虾仁寿司
        products = new HashMap<>();
        stocks = new HashMap<>();
        order = new HashMap<>();
        products.put("41040219971109", 10000);
        stocks.put("41040219971109", 10000);

    }

    private String queryMap(String productId)
    {
        return "国庆活动，虾仁寿司特价，限量"
                + products.get(productId)
                +"份 还剩：" + stocks.get(productId)+" 份"
                +" 该商品成功下单用户数目："
                +  order.size() +" 人" ;
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        int stockNum = stocks.get(productId);
        if (stockNum == 0){
            throw new SellException(100, "秒杀活动已经结束");
        }
        // 模拟生成订单
        else{
            order.put(KeyUtil.getUUID(), productId);
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stocks.put(productId,stockNum);
        }
    }
}

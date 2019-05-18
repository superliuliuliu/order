package com.lgy.order.repository;

import com.lgy.order.model.DO.SellerInfo;
import com.lgy.order.common.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void add(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.getUUID());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("6789@jkl");
        sellerInfo.setOpenId("wx082497a386ccc4cd");
        sellerInfoRepository.save(sellerInfo);
    }

    @Test
    public void findByOpenid(){
        String openid = "wx082497a386ccc4cd";
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenId(openid);
        log.info("查询到的卖家信息为{}", sellerInfo);
    }

}
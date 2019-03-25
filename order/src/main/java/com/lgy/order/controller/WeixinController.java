package com.lgy.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * WeixinController
 * @description 微信controller授权接口
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/24 14:29
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/weixin")
@Slf4j
public class WeixinController {

    @GetMapping(value = "/auth")
    public void auth(@RequestParam("code") String code){
        log.info("授权验证界面");
        log.info("code={}", code);
        //接收微信返回的code值
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx082497a386ccc4cd&secret=f8746223863d449a76bf8666cce9d457&code="+ code +"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }

}

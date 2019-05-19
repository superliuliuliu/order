package com.lgy.order.controller.Sell;

import com.lgy.order.common.enums.ResultEnum;
import com.lgy.order.common.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

import static java.net.URLEncoder.*;

/**
 * WechatController
 * @description 通过第三方SDK来获取所需的相关授权信息
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/24 18:16
 * @version 1.0.0
 */
@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    /**
      * @description 每次都需要授权后才能在微信app上访问相关url   returnUrl接收的是未经过处理想去访问的地址  redirectUrl则是经过授权处理的微信里能够访问的URL
      */
    @GetMapping(value = "/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        String url = "http://liugaoyang.natapp1.cc/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, encode(returnUrl));
        return "redirect:" + redirectUrl;

    }

    @GetMapping(value = "/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
            //获取token
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e)
        {
            log.error("【微信授权】", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openId=" + openId;
    }
}

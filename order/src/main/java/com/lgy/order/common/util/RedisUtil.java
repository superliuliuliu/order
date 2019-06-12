package com.lgy.order.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @description redis 常用工具类
 * @author liugaoyang
 * @date 2019/5/19 23:28
 * @version 1.0.0
 */
@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean set(String key, String value){
        try{
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            log.error("redis set操作失败：{}", e.getMessage());
        }
        return false;
    }

}

package com.lgy.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLockService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * lock
     * @description redis分布式锁
     * @param key,
     * @param value 存储的是 当前时间+有效时间
     * @return boolean 加锁是否成功
     * @author liugaoyang
     * @date 2019/5/19 13:36
     * @version 1.0.0
     */
    public boolean lock(String key, String value){
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)){
            return true;
        }
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        // 锁已经过期了
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue))
                return true;
        }
        return false;
    }

    public void unlock(String key, String value){
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e) {
            log.error("【redis分布式锁】解锁异常, {}", e);
        }
    }

}

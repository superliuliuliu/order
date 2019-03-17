package com.lgy.order.util;

import java.util.Random;

/**
 * KeyUtil
 * @description 用来生成唯一的主键
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/17 21:58
 * @version 1.0.0
 */
public class KeyUtil {

    //以当前的毫秒数与随机数  同时因为是获取唯一id因此要防止多线程下时间相同
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return String.valueOf(number) + System.currentTimeMillis();
    }
}

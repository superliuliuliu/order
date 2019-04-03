package com.lgy.order.util;

import com.lgy.order.enums.CodeEnums;

public class EnumUtil {

    /**
     * getByCode
     * 首先要对泛型T进行限制  必须是实现了CodeEnums接口的类
     * @description 根据code获取枚举类实体
     * @param code code值
     * @enumClass 哪一个枚举类
     * @return T
     * @author liugaoyang
     * @date 2019/4/2 16:15
     * @version 1.0.0
     */
    public static <T extends CodeEnums> T getByCode(Integer code, Class<T> enumClass){
        for (T each: enumClass.getEnumConstants()) {
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}

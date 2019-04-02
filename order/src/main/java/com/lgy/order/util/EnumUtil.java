package com.lgy.order.util;

import com.lgy.order.enums.CodeEnums;

public class EnumUtil {
    public static <T extends CodeEnums> T getByCode(Integer code, Class<T> enumClass){
        for (T each: enumClass.getEnumConstants()) {
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}

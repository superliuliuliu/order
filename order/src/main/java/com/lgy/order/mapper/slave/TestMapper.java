package com.lgy.order.mapper.slave;

import org.apache.ibatis.annotations.Insert;

import java.util.Map;

public interface TestMapper {

    @Insert("insert into product_category (category_name, category_type) values(#{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);
}

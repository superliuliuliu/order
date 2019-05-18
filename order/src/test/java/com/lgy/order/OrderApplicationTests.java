package com.lgy.order;

import com.lgy.order.common.util.KeyUtil;
import com.lgy.order.mapper.ProductCategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {

	@Autowired
	private ProductCategoryMapper productCategoryMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMybatis(){
		Map<String, Object> map = new HashMap<>();
		map.put("category_name", "麻辣专区");
		map.put("category_type", 4);
		productCategoryMapper.insertByMap(map);
	}

}

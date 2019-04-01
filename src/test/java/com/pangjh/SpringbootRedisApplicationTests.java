package com.pangjh;

import com.pangjh.model.User;
import com.pangjh.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {
	private static final String key = "springboot1219";//这里的key值可以自己修改
	public static final Logger logger = LoggerFactory.getLogger(SpringbootRedisApplicationTests.class);

	@Autowired
	private RedisUtils redisUtils;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testfind() {
		//如果缓存存在
		boolean hasKey = redisUtils.exists(key.concat("1011"));
		if(hasKey){
			//获取缓存
			Object object =  redisUtils.get(key.concat("1011"));
			logger.info("从缓存获取的数据"+ object);
		}else{
			User user = new User();
			user.setId("101");
			user.setName("香菇");
			user.setAge(18);
			redisUtils.set(key,user,10L, TimeUnit.MINUTES);
			logger.info("数据插入缓存"+user.toString());
		}

	}

	/**
	 * @Date:10:11 2017/12/20
	 *删除数据
	 *
	 */
	@Test
	public void testdel(){
		//缓存存在
		boolean hasKey = redisUtils.exists(key.concat("1011"));
		if(hasKey){
			Object object =  redisUtils.get(key.concat("1011"));
			redisUtils.remove(key.concat("1011"));
			logger.info("从缓存中删除数据");
		}else {
			logger.info("缓存中没有数据！");
		}
	}
}

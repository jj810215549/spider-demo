package cn.xrn.spider_ip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

	/***** phantomjs驱动位置 *****/
	public static String PHANTOMJS_DRIVER;
	/******** redis配置 **********/
	public static Integer REDIS_MAX_IDLE;
	public static Integer REDIS_MAX_TOTAL;
	public static Integer REDIS_MAX_WAIT_MILLS;
	public static Boolean REDIS_TEST_ON_BORROW;
	public static String REDIS_IP;
	public static Integer REDIS_PORT;
	/******* 代理IP存储的键 *******/
	public static String PROXY_IP_REDIS_KEY;
	/******** 代理IP订单号 **********/
	public static String PROXY_IP_ORDER_NUMBER;

	static {
		Properties properties = new Properties();
		FileInputStream input = null;
		try {
			input = new FileInputStream("src/main/resources/config.properties");
			properties.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PHANTOMJS_DRIVER = properties.getProperty("driver.PhantomjsDriver");
		REDIS_MAX_IDLE = Integer.valueOf(properties.getProperty("redis.MaxIdle"));
		REDIS_MAX_TOTAL = Integer.valueOf(properties.getProperty("redis.MaxTotal"));
		REDIS_MAX_WAIT_MILLS = Integer.valueOf(properties.getProperty("redis.MaxWaitMillis"));
		REDIS_TEST_ON_BORROW = Boolean.parseBoolean(properties.getProperty("redis.TestOnBorrow"));
		REDIS_IP = properties.getProperty("redis.ip");
		REDIS_PORT = Integer.valueOf(properties.getProperty("redis.port"));
		PROXY_IP_REDIS_KEY = properties.getProperty("proxy.ip.redis.key");
		PROXY_IP_ORDER_NUMBER = properties.getProperty("proxy.ip.order.nunmber");
	}

}

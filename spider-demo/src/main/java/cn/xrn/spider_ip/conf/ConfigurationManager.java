package cn.xrn.spider_ip.conf;

import java.io.InputStream;
import java.util.Properties;

/**
 * 配置管理组件 1、配置管理组件可以复杂，也可以很简答，
 * 对于简单的配置管理组件涞水，只要开发一个类，可以在第一次访问他的时候，就从对应的properties文件中，读取配置项，并提供外界获取某个配置key对应的value的方法
 * 2、如果是特别复杂的配置管理组件，那么可能需要使用一些软件设计中的设计模式，比如单利模式、解释器模式，可能需要管理多个不同的properties，甚至是xml类型的配置文件
 * 
 * @author Administrator
 *
 */
public class ConfigurationManager {

	private static Properties prop = new Properties();

	static {
		try {
			InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream("my.properties");
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取key对应的value
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return prop.getProperty(key);
	}

	public static Integer getInteger(String key) {
		String value = get(key);
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Boolean getBoolean(String key) {
		String value = get(key);
		try {
			return Boolean.valueOf(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}

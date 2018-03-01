package cn.xrn.spider_ip.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.xrn.spider_ip.service.ICountValidate;

public class CountValidate implements ICountValidate {

	private static CountValidate instance;

	private static Map<String, Integer> map = new HashMap<>();

	private CountValidate() {

	}

	public static CountValidate getInstance() {
		if (instance == null) {
			synchronized (CountValidate.class) {
				if (instance == null) {
					instance = new CountValidate();
				}
			}
		}
		return instance;
	}

	@Override
	public void addFailUrl(String url) {
		Integer count = this.serchFailCount(url);
		map.put(url, count+1);
	}

	@Override
	public Integer serchFailCount(String url) {
		Integer count = map.get(url);
		if(Objects.isNull(count)) {
			return 0;
		}
		return count;
	}

}

package cn.xrn.spider_ip.service;

public interface ICountValidate {

	void addFailUrl(String url);
	
	Integer serchFailCount(String url);
}

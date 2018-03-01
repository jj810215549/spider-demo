package cn.xrn.spider_ip.service;

import java.io.Serializable;
import java.util.Map;

public interface IDownLoadService {
	Serializable download(String url,Map<?, ?>... maps);
}
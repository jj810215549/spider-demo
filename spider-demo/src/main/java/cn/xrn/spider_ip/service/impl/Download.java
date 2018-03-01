package cn.xrn.spider_ip.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.xrn.spider_ip.entity.Data;
import cn.xrn.spider_ip.service.IDownLoadService;
import cn.xrn.spider_ip.utils.PageDownLoadUtil;

public class Download implements IDownLoadService {

	@Override
	public Serializable download(String url, Map<?, ?>... maps) {
		Data data = new Data();
		data.setUrl(url);
		String result = PageDownLoadUtil.httpClientGet(url, maps);
		if (StringUtils.isNotBlank(result)) {
			data.setHtml(result);
			return data;
		}
		return null;
	}

}

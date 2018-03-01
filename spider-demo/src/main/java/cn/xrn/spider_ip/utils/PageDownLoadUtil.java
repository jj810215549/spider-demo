package cn.xrn.spider_ip.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.xrn.spider_ip.conf.ConfigurationManager;
import cn.xrn.spider_ip.constant.Constants;

public class PageDownLoadUtil {

	/**
	 * @Description 本地连接get
	 * @author 徐仁杰
	 * @action httpClientDefultGet
	 * @return String
	 */
	public static String httpClientDefultGet(String url, Map<?, ?>... maps) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet get = new HttpGet(url);
			setGetHeaders(get, maps);
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			return result;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description 发送get请求
	 * @author 徐仁杰
	 * @date 2017年11月24日 上午9:19:13
	 * @action httpClientGet
	 * @return String
	 */
	public static String httpClientGet(String url, Map<?, ?>... maps) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String key = "";
		try {
			HttpGet get = new HttpGet(url);
			/******************* 设置代理IP ******************/
			key = getProxyIpPort();
			RequestConfig config = getRequestConfig(key);
			/******************* 设置代理IP ******************/
			get.setConfig(config);
			setGetHeaders(get, maps);
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			return result;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description 获取代理IP
	 * @author 徐仁杰
	 * @date 2017年12月15日 上午11:38:53
	 * @action getProxyIpPort
	 * @return String
	 */
	private static String getProxyIpPort() {
		String result = httpClientDefultGet(ConfigurationManager.get(Constants.PROXY_IP_URL),
				new HashMap<String, String>());
		String[] split = result.split("\n");
		String ipPort = split[0].trim();
		return ipPort;
	}

	/**
	 * @Description 获取httpclient上下文
	 * @author 徐仁杰
	 * @date 2017年12月13日 下午2:11:18
	 * @action getRequestConfig
	 * @return RequestConfig
	 */
	private static RequestConfig getRequestConfig(String key) {
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(2000).setConnectTimeout(2000)
				.setSocketTimeout(2000).build();
		if (StringUtils.isNotBlank(key)) {
			String[] arr = key.split(":");
			String proxy_ip = arr[0];
			int proxy_port = Integer.parseInt(arr[1]);
			HttpHost proxy = new HttpHost(proxy_ip, proxy_port);
			config = RequestConfig.custom().setProxy(proxy).setConnectionRequestTimeout(2000).setConnectTimeout(2000)
					.setSocketTimeout(2000).build();
		}
		return config;
	}

	/**
	 * 设置请求头信息
	 * 
	 * @param get
	 * @param maps
	 */
	private static void setGetHeaders(HttpGet get, Map<?, ?>... maps) {
		get.setHeader("User-Agent", HeadersUtils.getUserAgent());
		for (Map<?, ?> map : maps) {
			Iterator<?> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String next = (String) iterator.next();
				String object = (String) map.get(next);
				get.setHeader(next, object);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String result = PageDownLoadUtil.httpClientGet("https://book.douban.com/subject/10532384/", new HashMap<>());
		Document doc = Jsoup.parse(result);
		String info = doc.select("#info").html();
		// 书名
		String bookName = doc.select("#wrapper > h1 > span").text();
		System.out.println(bookName);
		String[] split = info.split("\n");
		// 评分
		String score = doc.select("#interest_sectl > div > div.rating_self.clearfix > strong").text();
		System.out.println(score);
		// 评分人数
		String scoreNums = doc
				.select("#interest_sectl > div > div.rating_self.clearfix > div > div.rating_sum > span > a").text();
		System.out.println(scoreNums);
		for (int i = 0; i < split.length; i++) {
			if (split[i].contains("作者")) {
				String str = split[i];
				String substring = str.substring(str.indexOf("<a"), str.indexOf("</a>"));
				StringBuffer subChinese = StrUtil.subChinese(substring);
				System.out.println(subChinese);
			}
			if (split[i].contains("出版社")) {
				String str = split[i];
				String substring = str.substring(str.indexOf("</span>")+7).trim();
				System.out.println(substring);
			}
			if (split[i].contains("出版年")) {
				String str = split[i];
				String substring = str.substring(str.indexOf("</span>")+7).trim();
				System.out.println(substring);
			}
			if (split[i].contains("定价")) {
				String str = split[i];
				String substring = str.substring(str.indexOf("</span>")+7).trim();
				System.out.println(substring);
			}
			if (split[i].contains("ISBN")) {
				String str = split[i];
				String substring = str.substring(str.indexOf("</span>")+7).trim();
				System.out.println(substring);
			}
		}

	}

	public static void main1(String[] args) {
		String proxyIpPort = getProxyIpPort();
		String[] split = proxyIpPort.split("\n");
		for (int i = 0; i < split.length; i++) {
			if (split[i].contains("作者")) {
				System.out.println(split[i]);
			}
		}
		// System.out.println(Arrays.toString(split));
	}
}

package cn.xrn.spider_ip.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {

	/**
	 * 正则表达式匹配两个指定字符串中间的内容
	 * 
	 * @param soap
	 * @return
	 */
	public static List<String> getSubUtil(String soap, String rgex) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			int i = 1;
			list.add(m.group(i));
			i++;
		}
		return list;
	}

	/**
	 * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
	 * 
	 * @param soap
	 * @param rgex
	 * @return
	 */
	public static String getSubUtilSimple(String soap, String rgex) {
		Pattern pattern = Pattern.compile(rgex);// 匹配的模式
		Matcher m = pattern.matcher(soap);
		while (m.find()) {
			return m.group(1);
		}
		return "";
	}

	/**
	 * 提取字符串中的中文
	 * @param str
	 * @return
	 */
	public static StringBuffer subChinese(String str) {
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
		Matcher m = p.matcher(str);
		while(m.find()) {
			sb.append(m.group());
		}
		return sb;
	}

	public static void main(String[] args) {
		
	}

}

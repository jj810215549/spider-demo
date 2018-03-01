package cn.xrn.spider_ip.junit;

import java.util.HashMap;

import org.junit.Test;

import cn.xrn.spider_ip.utils.PageDownLoadUtil;

/**
 * 下载工具类测试用例
 * @author 徐仁杰
 *
 */
public class PageDownloadTest {

	@Test
	public void download() {
		String url = "https://www.douban.com/j/search?q=%E4%BA%92%E8%81%94%E7%BD%91%EF%BC%8C%E7%BC%96%E7%A8%8B%EF%BC%8C%E7%AE%97%E6%B3%95&start=793&cat=1001";
		String result = PageDownLoadUtil.httpClientDefultGet(url, new HashMap<>());
		System.out.println(result);
	}

}

package cn.xrn.spider_ip.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.xrn.spider_ip.entity.Book;
import cn.xrn.spider_ip.entity.Data;
import cn.xrn.spider_ip.service.IParse;
import cn.xrn.spider_ip.utils.StrUtil;

public class Parse implements IParse {

	@Override
	public void bookParse(Data data) {
		String url = data.getUrl();
		if (url.startsWith("https://www.douban.com")) {
			listJsonParse(data);
		}
		if (url.startsWith("https://book.douban.com")) {
			bookHtmlParse(data);
		}
	}

	/**
	 * 列表页jsom数据解析代码
	 * 
	 * @param data
	 */
	private void listJsonParse(Data data) {
		System.out.println("进入解析");
		String html = data.getHtml();
		JSONObject json = new JSONObject(html);
		boolean more = json.getBoolean("more");
		data.setMore(more);
		if (more) {
			String rgex = "start=(.*?)&";
			String subStr = StrUtil.getSubUtilSimple(data.getUrl(), rgex);
			int num = Integer.valueOf(subStr) + 20;
			String nextUrl = "https://www.douban.com/j/search?q=%E4%BA%92%E8%81%94%E7%BD%91%EF%BC%8C%E7%BC%96%E7%A8%8B%EF%BC%8C%E7%AE%97%E6%B3%95&start="
					+ num + "&cat=1001";
			System.out.println("下一页链接" + nextUrl);
			data.setNextUrl(nextUrl);
		}
		JSONArray items = json.getJSONArray("items");
		for (int i = 0; i < items.length(); i++) {
			String result = items.getString(i);
			Document doc = Jsoup.parse(result);
			String onclick = doc.select("div.content > div > h3 > a").attr("onclick");
			String subStr = StrUtil.getSubUtilSimple(onclick, "sid: (.*?),");
			String href = "https://book.douban.com/subject/" + subStr + "/";
			System.out.println(href);
			data.setUrlList(href);
		}
	}

	/**
	 * 书本详情链接解析
	 * 
	 * @param data
	 */
	private void bookHtmlParse(Data data) {
		Document doc = Jsoup.parse(data.getHtml());
		Book book = new Book();
		String info = doc.select("#info").html();
		// 书名
		String bookName = doc.select("#wrapper > h1 > span").text();
		book.setBookName(bookName);
		String[] split = info.split("\n");
		// 评分
		String score = doc.select("#interest_sectl > div > div.rating_self.clearfix > strong").text();
		book.setScore(score);
		// 评分人数
		String scoreNums = doc
				.select("#interest_sectl > div > div.rating_self.clearfix > div > div.rating_sum > span > a").text();
		book.setScoreNums(scoreNums);
		for (int i = 0; i < split.length; i++) {
			if (split[i].contains("作者")) {
				String str = split[i];
				String substring = str.substring(str.indexOf("<a"), str.indexOf("</a>"));
				String author = StrUtil.subChinese(substring).toString();
				book.setAuthor(author);
			}
			if (split[i].contains("出版社")) {
				String str = split[i];
				String press = str.substring(str.indexOf("</span>") + 7).trim();
				book.setPress(press);
			}
			if (split[i].contains("出版年")) {
				String str = split[i];
				String dateOfPublication = str.substring(str.indexOf("</span>") + 7).trim();
				book.setDateOfPublication(dateOfPublication);
			}
			if (split[i].contains("定价")) {
				String str = split[i];
				String price = str.substring(str.indexOf("</span>") + 7).trim();
				book.setPrice(price);
			}
			if (split[i].contains("ISBN")) {
				String str = split[i];
				String id = str.substring(str.indexOf("</span>") + 7).trim();
				book.setId(id);
			}
		}
		data.setBooks(book);
	}

}

package cn.xrn.spider_ip.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer uuid;

	private String html;

	private String url;

	private Boolean more;
	
	private String nextUrl;
	
	private Book books;
	
	private List<String> urlList;

	public Data() {
		urlList = new ArrayList<>();
	}

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getMore() {
		return more;
	}

	public void setMore(Boolean more) {
		this.more = more;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public Book getBooks() {
		return books;
	}

	public void setBooks(Book books) {
		this.books = books;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(String url) {
		this.urlList.add(url);
	}

}

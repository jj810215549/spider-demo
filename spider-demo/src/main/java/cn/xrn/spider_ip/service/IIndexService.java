package cn.xrn.spider_ip.service;

import java.util.List;

import cn.xrn.spider_ip.entity.Book;

public interface IIndexService {

	List<Book> serchBySort(Integer limit);
}

package cn.xrn.spider_ip.dao;

import java.util.List;

import cn.xrn.spider_ip.entity.Book;

/**
 * 任务管理DAO接口
 * 
 * @author Administrator
 *
 */
public interface IBookDAO {

	/**
	 * 插入单条记录
	 * @param book
	 */
	void insert(Book book);
	
	/**
	 * 查询排序后的结果
	 * @return
	 */
	List<Book> searchBySort(Integer limit);

}

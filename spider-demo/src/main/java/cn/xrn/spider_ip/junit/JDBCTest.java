package cn.xrn.spider_ip.junit;

import org.junit.Test;

import cn.xrn.spider_ip.dao.factory.DAOFactory;
import cn.xrn.spider_ip.dao.impl.BookDAOImpl;
import cn.xrn.spider_ip.entity.Book;

public class JDBCTest {

	@Test
	public void insertTest() {
		Book book = new Book();
		book.setId("1");
		book.setBookName("测试");
		BookDAOImpl insertBookDAO = DAOFactory.BookDAO();
		insertBookDAO.insert(book);
	}
	
	@Test
	public void serchBySortTest() {
		BookDAOImpl insertBookDAO = DAOFactory.BookDAO();
		insertBookDAO.searchBySort(300);
	}
}

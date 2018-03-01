package cn.xrn.spider_ip.service.impl;

import java.util.List;

import cn.xrn.spider_ip.dao.factory.DAOFactory;
import cn.xrn.spider_ip.dao.impl.BookDAOImpl;
import cn.xrn.spider_ip.entity.Book;
import cn.xrn.spider_ip.service.IIndexService;

public class IndexService implements IIndexService {

	@Override
	public List<Book> serchBySort(Integer limit) {
		BookDAOImpl bookDAO = DAOFactory.BookDAO();
		List<Book> searchBySort = bookDAO.searchBySort(limit);
		return searchBySort;
	}

}

package cn.xrn.spider_ip.dao.factory;

import cn.xrn.spider_ip.dao.impl.BookDAOImpl;

/**
 * DAO工厂类
 * @author 
 *
 */
public class DAOFactory {

	public static BookDAOImpl BookDAO() {
		return new BookDAOImpl();
	}

}

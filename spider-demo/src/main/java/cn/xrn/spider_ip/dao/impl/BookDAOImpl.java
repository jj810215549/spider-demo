package cn.xrn.spider_ip.dao.impl;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import cn.xrn.spider_ip.dao.IBookDAO;
import cn.xrn.spider_ip.entity.Book;
import cn.xrn.spider_ip.jdbc.JDBCHelper;

/**
 * 任务管理DAO实现类
 * 
 * @author Administrator
 *
 */
public class BookDAOImpl implements IBookDAO {
	
	@Override
	public void insert(Book book) {
		String sql = "insert into book(id,bookName,score,scoreNums,author,press,dateOfPublication,price) values(?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { book.getId(), book.getBookName(), book.getScore(), book.getScoreNums(),
				book.getAuthor(), book.getPress(), book.getDateOfPublication(), book.getPrice() };
		JDBCHelper jdbcHelper = JDBCHelper.getInstance();
		jdbcHelper.executeUpdate(sql, params);
	}

	@Override
	public List<Book> searchBySort(Integer limit) {
		String sql = "SELECT id,bookName,score,scoreNums,author,press,dateOfPublication,price from book ORDER BY score DESC LIMIT ?";
		List<Book> list = new LinkedList<>();
		JDBCHelper jdbcHelper = JDBCHelper.getInstance();
		jdbcHelper.executeQuery(sql,new Object[] { limit }, new JDBCHelper.QueryCallback() {
			
			@Override
			public void process(ResultSet rs) throws Exception {
				for (int i = 0; i < limit; i++) {
					if(rs.next()) {
						Book book = new Book();
						String id = rs.getString(1);
						String bookName = rs.getString(2);
						String score = rs.getString(3);
						String scoreNums = rs.getString(4);
						String author = rs.getString(5);
						String press = rs.getString(6);
						String dateOfPublication = rs.getString(7);
						String price = rs.getString(8);
						
						System.out.println(id);
						book.setId(id);
						System.out.println(bookName);
						book.setBookName(bookName);
						System.out.println(score);
						book.setScore(score);
						System.out.println(scoreNums);
						book.setScoreNums(scoreNums);
						System.out.println(author);
						book.setAuthor(author);
						System.out.println(press);
						book.setPress(press);
						System.out.println(dateOfPublication);
						book.setDateOfPublication(dateOfPublication);
						System.out.println(price);
						book.setPrice(price);
						list.add(book);
					}
				}
				
			}
		});
		return list;
	}

}

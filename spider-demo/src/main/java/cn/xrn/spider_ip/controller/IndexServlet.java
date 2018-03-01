package cn.xrn.spider_ip.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xrn.spider_ip.entity.Book;
import cn.xrn.spider_ip.service.impl.IndexService;

public class IndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IndexService service = new IndexService();
		String parameter = req.getParameter("limit");
		Integer limit = Integer.valueOf(parameter);
		List<Book> books = service.serchBySort(limit);
		req.setAttribute("books", books);
		req.getRequestDispatcher("/WEB-INF/books.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}

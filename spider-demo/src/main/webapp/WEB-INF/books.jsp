<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>爬虫后台管理系统</title>
<meta name="description" content="爬虫后台管理系统">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body data-type="index">
	<header class="am-topbar am-topbar-inverse admin-header"> </header>
	<div class="tpl-page-container tpl-page-header-fixed">
		<div class="tpl-content-wrapper">
			<div class="tpl-portlet-components">

				<div class="tpl-block">

					<div class="am-g">
						<div class="am-u-sm-12">
							<form class="am-form">
								<table
									class="am-table am-table-striped am-table-hover table-main">
									<thead>
										<tr>
											<th class="table-title">书名</th>
											<th class="table-type">评分</th>
											<th class="table-set">评价人数</th>
											<th class="table-set">作者</th>
											<th class="table-set">出版社</th>
											<th class="table-set">出版日期</th>
											<th class="table-set">价格</th>
											<th class="table-set">爬取日期</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="book" items="${books}">
											<tr>
												<td>${book.bookName}</td>
												<td>${book.score}</td>
												<td>${book.scoreNums}</td>
												<td>${book.author}</td>
												<td>${book.press}</td>
												<td>${book.dateOfPublication}</td>
												<td>${book.price}</td>
												<td>${book.createDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



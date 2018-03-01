package cn.xrn.spider_ip.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 书本数据载体
 * 
 * @author 徐仁杰
 *
 */
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer uuid;

	/**
	 * 书本序列号
	 */
	private String id;

	/**
	 * 书名
	 */
	private String bookName;

	/**
	 * 评分
	 */
	private String score;

	/**
	 * 评价人数
	 */
	private String scoreNums;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 出版社
	 */
	private String press;

	/**
	 * 出版日期
	 */
	private String dateOfPublication;

	/**
	 * 价格
	 */
	private String price;

	/**
	 * 爬取日期
	 */
	private Date createDate;

	public Book() {
		super();
	}

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getScoreNums() {
		return scoreNums;
	}

	public void setScoreNums(String scoreNums) {
		this.scoreNums = scoreNums;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getDateOfPublication() {
		return dateOfPublication;
	}

	public void setDateOfPublication(String dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

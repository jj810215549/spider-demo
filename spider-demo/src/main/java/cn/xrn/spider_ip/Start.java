package cn.xrn.spider_ip;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;

import cn.xrn.spider_ip.dao.factory.DAOFactory;
import cn.xrn.spider_ip.dao.impl.BookDAOImpl;
import cn.xrn.spider_ip.entity.Data;
import cn.xrn.spider_ip.queue.QueueService;
import cn.xrn.spider_ip.service.ICountValidate;
import cn.xrn.spider_ip.service.IDownLoadService;
import cn.xrn.spider_ip.service.IParse;
import cn.xrn.spider_ip.service.impl.CountValidate;
import cn.xrn.spider_ip.service.impl.Download;
import cn.xrn.spider_ip.service.impl.Parse;

/**
 * 爬虫入口
 * 
 * @author 徐仁杰
 *
 */
public class Start {

	private IDownLoadService downLoad;

	private IParse parse;

	private QueueService queue = QueueService.getInstatnce();

	private ICountValidate valid = CountValidate.getInstance();

	private ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(8);

	private Integer count = 0;

	public IDownLoadService getDownLoad() {
		return downLoad;
	}

	public void setDownLoad(IDownLoadService downLoad) {
		this.downLoad = downLoad;
	}

	public IParse getParse() {
		return parse;
	}

	public void setParse(IParse parse) {
		this.parse = parse;
	}

	/**
	 * 下载页面
	 * 
	 * @param url
	 * @return
	 */
	public Data download(String url) {
		return (Data) this.downLoad.download(url, new HashMap<>());
	}

	/**
	 * 解析页面
	 * 
	 * @param data
	 */
	public void parseHtml(Data data) {
		this.parse.bookParse(data);
	}

	public void start() {
		while (count < 8) {
			if (queue.getSize() > 0) {
				String url = queue.poll();
				newFixedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						System.err.println(url);
						try {
							Data data = Start.this.download(url);
							Start.this.parseHtml(data);
							String nextUrl = data.getNextUrl();
							if (StringUtils.isNotBlank(nextUrl)) {
								queue.addHighLevel(nextUrl);
							}
							List<String> urlList = data.getUrlList();
							for (String string : urlList) {
								queue.addLowLevel(string);
							}
							if (!Objects.isNull(data.getBooks())) {
								System.out.println("插入成功");
								BookDAOImpl insertBookDAO = DAOFactory.BookDAO();
								insertBookDAO.insert(data.getBooks());
							}
						} catch (Exception e) {
							valid.addFailUrl(url);
							Integer failCount = valid.serchFailCount(url);
							System.err.println("失败次数" + failCount);
							if (failCount >= 10) {
								System.err.println("失败五次以上,废弃");
							} else {
								System.err.println("页面解析出现错误,链接放回队列");
								queue.addHighLevel(url);
							}
						}
					}
				});

			} else {
				try {
					Thread.sleep(300000);
					System.err.println("没有更多链接了");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Start() {

	}

	public static void main(String[] args) {
		QueueService.getInstatnce().addHighLevel(
				"https://www.douban.com/j/search?q=%E4%BA%92%E8%81%94%E7%BD%91%EF%BC%8C%E7%BC%96%E7%A8%8B%EF%BC%8C%E7%AE%97%E6%B3%95&start=0&cat=1001");
		Start start = new Start();
		start.setDownLoad(new Download());
		start.setParse(new Parse());
		start.start();
	}

}

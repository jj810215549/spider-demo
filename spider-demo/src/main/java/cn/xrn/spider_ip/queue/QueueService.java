package cn.xrn.spider_ip.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.apache.commons.lang3.StringUtils;

public class QueueService {

	private static QueueService instance;

	// 高优先级
	private Queue<String> highLevelQueue = new ConcurrentLinkedDeque<String>();
	// 低优先级
	private Queue<String> lowLevelQueue = new ConcurrentLinkedDeque<String>();

	private QueueService() {

	}

	public static QueueService getInstatnce() {
		if (instance == null) {
			synchronized (QueueService.class) {
				if (instance == null) {
					instance = new QueueService();
				}
			}
		}
		return instance;
	}
	
	public String poll() {
		//先解析高优先级队列
		String url = highLevelQueue.poll();
		if(StringUtils.isBlank(url)){
			//然后在解析低优先级队列
			url = lowLevelQueue.poll();
		}
		return url;
	}

	public void addHighLevel(Object obj) {
		String url = (String) obj;
		this.highLevelQueue.add(url);
	}

	public void addLowLevel(Object obj) {
		String url = (String) obj;
		this.lowLevelQueue.add(url);
	}
	
	public int getSize(){
		int size = this.highLevelQueue.size();
		int size2 = this.lowLevelQueue.size();
		return size+size2;
	}
}

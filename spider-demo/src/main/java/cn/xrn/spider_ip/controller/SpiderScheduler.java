package cn.xrn.spider_ip.controller;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Description: 调度器
 * @author 徐仁杰
 * @date 2017年11月30日 上午9:54:21
 */

public class SpiderScheduler {

	public static void main(String[] args) throws Exception {
		// 获取默认调度器
		Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
		defaultScheduler.start();
		/********************添加代理IP筛选任务*******************/
		JobDetail proxyJob = new JobDetail("proxyJob", Scheduler.DEFAULT_GROUP,ProxyJob.class);
		CronTrigger proxyTrigger = new CronTrigger("proxyJob", Scheduler.DEFAULT_GROUP, "10/1 * * * * ?");
		defaultScheduler.scheduleJob(proxyJob, proxyTrigger);
		/********************添加代理IP筛选任务*******************/
	}
}

package cn.xrn.spider_ip.controller;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.xrn.spider_ip.Start;
import cn.xrn.spider_ip.service.impl.Download;
import cn.xrn.spider_ip.service.impl.Parse;

public class ProxyJob implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Start start = new Start();
		start.setDownLoad(new Download());
		start.setParse(new Parse());
		start.start();
	}

}

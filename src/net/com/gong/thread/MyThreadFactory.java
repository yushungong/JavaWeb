package net.com.gong.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadFactory;
//自定义线程工场，在线程创建时自定义线程名称（也可自定义线程创建时的日志等其他功能）
public class MyThreadFactory implements ThreadFactory {
	
	private SimpleDateFormat formit = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String name;
	
	public MyThreadFactory(String name) {
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable r) {
		Date d = new Date();
		String newname = name+"_"+formit.format(d);
		return new MyThread(r, newname);
	}

}

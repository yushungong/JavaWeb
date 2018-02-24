package net.com.gong.thread;
//自定义的线程类
public class MyThread extends Thread {
	
	public MyThread(Runnable r, String name){
		super(r, name);
	}
}

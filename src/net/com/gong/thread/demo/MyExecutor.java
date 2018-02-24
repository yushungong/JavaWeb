package net.com.gong.thread.demo;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//创建线程池
public class MyExecutor {
	public static void main(String[] args) {
		//创建一个固定大小的线程池，采用有界队列
		ThreadPoolExecutor executor = new ThreadPoolExecutor(
				5, 5, 5*60*1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(10));
		//使用“调用者运行”饱和策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		
		for (int i = 0; i < 100; i++) {
			System.out.println("试图启动第"+i+"个线程");
			MyRunnable myr = new MyRunnable();
			executor.execute(myr);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyRunnable implements Runnable{
	volatile boolean type = true;
	int count = 0;

	@Override
	public void run() {
		while(type){
			count++;
			System.out.println("My name is : "+Thread.currentThread().getName()+" count:"+count);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(count == 5){
				type = false;
			}
		}
		
	}
	
}
package net.com.gong.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyMain {

	public static void main(String[] args) {
		//初始化配置自定义线程池
		TimingThreadPool myPool = new TimingThreadPool(
				5, 10, 5*60*1000, TimeUnit.MILLISECONDS,
				new LinkedBlockingDeque<Runnable>(10), new MyThreadFactory("MyThread"));
		//使用“调用者运行”饱和策略
		myPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//调用测试
		for (int i = 0; i < 10; i++) {
			System.out.println("试图创建线程"+i);
			myPool.execute(new MyRunnable());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//关闭线程池
		myPool.shutdown();
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
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(count == 5){
				type = false;
			}
		}
		
	}
	
}

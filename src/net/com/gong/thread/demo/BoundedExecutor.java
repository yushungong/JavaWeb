package net.com.gong.thread.demo;

import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;
//使用Semaphore类来控制任务的提交速率
//Semaphore 通常用于限制可以访问某些资源（物理或逻辑的）的线程数目。
public class BoundedExecutor {
	private final Executor exec;
	private final Semaphore semaphore;
	
	public BoundedExecutor(Executor exec,int bound){
		this.exec = exec;
		this.semaphore = new Semaphore(bound);
	}
	
	public void sunmitTask(final Runnable command) throws InterruptedException{
		semaphore.acquire();
		try {
			exec.execute(new Runnable(){
				@Override
				public void run() {
					try {
						command.run();
					} finally{
						semaphore.release();
					}
				}
			});
		} catch (Exception e) {
			semaphore.release();
			e.printStackTrace();
		}
	}
}

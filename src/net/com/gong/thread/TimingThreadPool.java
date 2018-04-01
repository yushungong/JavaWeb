package net.com.gong.thread;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//增加了日志和计时功能的自定义线程池
public class TimingThreadPool extends ThreadPoolExecutor {

	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	private final Logger log = Logger.getLogger("TimingThreadPool");

	private final AtomicLong numTasks = new AtomicLong();

	private final AtomicLong totalTime = new AtomicLong();

	public TimingThreadPool(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory);
		// 日志处理器
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("E:\\logs\\test.txt");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 为处理器设置日志格式：Formatter
		SimpleFormatter sf = new SimpleFormatter();
		fileHandler.setFormatter(sf);
		log.addHandler(fileHandler);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		log.info(String.format("beforeExecute：Thread %s: start %s",
				t.getName(), r));
		startTime.set(System.nanoTime());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		try {
			super.afterExecute(r, t);
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			log.info(String.format(
					"afterExecute：Thread %s: end %s, 线程运行时间 = %dns", t, r,
					taskTime));
		} finally {
			super.afterExecute(r, t);
		}
	}

	@Override
	protected void terminated() {
		try {
			log.info(String.format("Terminated: avg time=%dns", totalTime.get()
					/ numTasks.get()));
		} finally {
			super.terminated();
		}
	}
}

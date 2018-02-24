package net.com.gong.thread.demo;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.com.gong.thread.demo.TestNewTaskFor.CancellableTask;

/*
 * 采用newTaskFor来封装非标准的取消（处理不可中断阻塞）
 * */
public class TestNewTaskFor {
	public interface CancellableTask<T> extends Callable<T> {
		void cancel();
		RunnableFuture<T> newTask();
	};
}

class CancellingExecutor extends ThreadPoolExecutor {
	public CancellingExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if (callable instanceof CancellableTask) {
			return ((CancellableTask<T>) callable).newTask();
		} else {
			return super.newTaskFor(callable);
		}
	}
}

class SocketUsingTask<T> implements CancellableTask<T> {

	private Socket socket;

	protected synchronized void setSocket(Socket s) {
		socket = s;
	}

	@Override
	public T call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void cancel() {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public RunnableFuture<T> newTask() {
		return new FutureTask<T>(this) {
			@SuppressWarnings("finally")
			public boolean cancel(boolean mayInterruptIfRunning) {
				try {
					SocketUsingTask.this.cancel();
				} finally {
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}

}
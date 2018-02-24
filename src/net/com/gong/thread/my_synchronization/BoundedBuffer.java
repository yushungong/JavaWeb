package net.com.gong.thread.my_synchronization;
//使用条件队列实现有界缓存的阻塞功能
//正确的案例示范
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

	protected BoundedBuffer(int capacity) {
		super(capacity);
	}
	
	public synchronized void put(V v) throws InterruptedException {
		while(isFull()){
			wait();			
		}
		doPut(v);
		notifyAll();
	}
	
	public synchronized V take() throws InterruptedException {
		while(isEnpty()){
			wait();
		}
		V v = doTake();
		notifyAll();
		return v;
	}
}

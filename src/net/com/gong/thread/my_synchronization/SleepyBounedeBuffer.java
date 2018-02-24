package net.com.gong.thread.my_synchronization;
//通过轮询与休眠来实现简单阻塞
//错误的案例示范
//需要在响应性与休眠时间间权衡，需要休眠中的取消机制
public class SleepyBounedeBuffer<V> extends BaseBoundedBuffer<V> {

	protected SleepyBounedeBuffer(int capacity) {
		super(capacity);
	}
	
	public void put(V v) throws InterruptedException{
		while(true){
			synchronized (this){
				if(!isFull()){
					doPut(v);
					return;
				}
			}
			Thread.sleep(1000);
		}
	}
	
	public V take() throws InterruptedException{
		while(true){
			synchronized (this){
				if(!isEnpty()){
					return doTake();
				}
			}
			Thread.sleep(1000);
		}
	}
}

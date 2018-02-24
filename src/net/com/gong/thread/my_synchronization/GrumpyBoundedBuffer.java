package net.com.gong.thread.my_synchronization;
//有界缓存实现，当不满足条件时，不会执行相应操作，抛出异常
//此为错误案例，不可以用抛异常来处理处理
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	protected GrumpyBoundedBuffer(int capacity) {
		super(capacity);
	}
	
	public synchronized void put(V v) throws BufferFullException{
		if(isFull()){
			throw new BufferFullException();
		}
		doPut(v);
	}
	
	public synchronized V take() throws BufferEmptyException{
		if(isEnpty()){
			throw new BufferEmptyException();
		}
		return doTake();
	}
}

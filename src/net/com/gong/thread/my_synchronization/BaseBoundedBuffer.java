package net.com.gong.thread.my_synchronization;
//有界缓存实现基类
//此为错误案例，不可以用抛异常来处理处理
public abstract class BaseBoundedBuffer<V> {
	private final V[] buf;//用于存储对象
	private int tail;//尾部索引
	private int head;//头部索引
	private int count;//记录已存储的对象个数
	
	@SuppressWarnings("unchecked")
	protected BaseBoundedBuffer(int capacity) {
		//初始化创建一个固定大小的数字用于存储对象
		this.buf = (V[]) new Object[capacity];
	}
	
	protected synchronized final void doPut(V v) {
		buf[tail] = v;
		if(++tail == buf.length){
			tail = 0;
		}
		++count;
	}
	
	protected synchronized final V doTake() {
		V v = buf[head];
		buf[head] = null;
		if(++head == buf.length){
			head = 0;
		}
		--count;
		return v;
	}
	
	public synchronized final boolean isFull(){
		return count == buf.length;
	}
	
	public synchronized final boolean isEnpty(){
		return count == 0;
	}
}

package net.com.gong.thread.my_synchronization;

public class BufferFullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BufferFullException(){
		super("缓存已满。。。");
	};
}

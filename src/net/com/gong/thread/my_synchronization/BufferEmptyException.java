package net.com.gong.thread.my_synchronization;

public class BufferEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BufferEmptyException(){
		super("缓存为空。。。");
	}

}

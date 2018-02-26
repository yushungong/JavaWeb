package net.gong.com.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TestUDPRceive {

	/**
	 * 
	 * @接收端
	 */
	public static void main(String[] args) {
		DatagramSocket ds = null;
		DatagramPacket pack = null;
		try {
			ds = new DatagramSocket(9090);
			byte[] b = new byte[1024];
			pack = new DatagramPacket(b,0,b.length);
			ds.receive(pack);
			String str = new String(pack.getData(),0,pack.getLength());
			System.out.println(str);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			ds.close();
		}
	}
}

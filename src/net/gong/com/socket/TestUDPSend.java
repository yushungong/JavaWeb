package net.gong.com.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TestUDPSend {

	/**
	 * 
	 * @发送端
	 */
	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket();
			byte[] b = "你好，我是要发送的数据".getBytes();
			//创建一个数据报，不能大于64K，每一个都记录着数据信息，发送端IP、端口号，以及要发送到的接收端的IP、端口号。
			DatagramPacket pack = new DatagramPacket(b,0,b.length,InetAddress.getByName("127.0.0.1"),9090);
			ds.send(pack);
			ds.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}

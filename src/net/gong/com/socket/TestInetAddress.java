package net.gong.com.socket;

import java.net.InetAddress;

public class TestInetAddress {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*InetAddress用来代表IP地址，一个InetAddress的对象就代表一个IP地址
		 * 如何创建InerAddress的对象，getByName(String host)
		 * */
		InetAddress inet = InetAddress.getByName("www.atguigu.com");
		System.out.println(inet);
		//getHostName(): 获取IP地址对应域名
		System.out.println(inet.getHostName());
		//getHostAddress(): 获取IP地址
		System.out.println(inet.getHostAddress());
		//获取本机IP
		InetAddress loca = InetAddress.getLocalHost();
		System.out.println(loca);
		System.out.println(loca.getHostAddress());
		System.out.println(loca.getHostName());
	}

}

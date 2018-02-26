package net.gong.com.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestTCPClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket so = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			so = new Socket(InetAddress.getLocalHost(), 9090);
			os = so.getOutputStream();
			os.write("我是客户端".getBytes());
			//执行此方法，显示的告诉另一端发送完毕
			so.shutdownOutput();
			is = so.getInputStream();
			byte[] b = new byte[20];
			int len;
			while((len = is.read(b))!=-1){
				String str = new String(b,0,len);
				System.out.println(str);
			}
			so.shutdownInput();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null!=os){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=so){
				try {
					so.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=is){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}

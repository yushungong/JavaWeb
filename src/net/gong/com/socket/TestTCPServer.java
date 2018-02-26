package net.gong.com.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTCPServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InputStream is = null;
		OutputStream os = null;
		ServerSocket ss = null;
		Socket s = null;
		try {
			ss = new ServerSocket(9090);
			s = ss.accept();
			is = s.getInputStream();
			byte[] b = new byte[20];
			int len;
			while((len = is.read(b)) != -1){
				String str = new String(b,0,len);
				System.out.println(str);
			}
			s.shutdownInput();
			os = s.getOutputStream();
			os.write("我是服务端".getBytes());
			s.shutdownOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null!=os){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!=is){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=s){
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null!= ss){
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

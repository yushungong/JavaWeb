package net.gong.com.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestURL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//创建一个URL对象
		URL url = null;
		try {
			url = new URL("http://127.0.0.1:8080/examples/aaa.txt");
		//openStream():把指定URL的资源读取到本地
			InputStream is = url.openStream();
			byte[] b = new byte[1024];
			int len;
			while((len=is.read(b))!=-1){
				String s = new String(b,0,len,"UTF-8");
				System.out.println(s);
			}
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		try {
		//如果既有数据的输入，又有数据的输出，则考虑openConnection();
			URLConnection urlc = url.openConnection();
			//写入
			InputStream is = urlc.getInputStream();
			byte[] b = new byte[1024];
			int len;
			while((len=is.read(b))!=-1){
				String s = new String(b,0,len,"UTF-8");
				System.out.println(s);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*//一些常用方法
		//获取协议
		System.out.println(url.getProtocol());
		//获取主机名
		System.out.println(url.getHost());
		//获取端口号
		System.out.println(url.getPort());
		//获取文件路径
		System.out.println(url.getPath());
		//获取文件名
		System.out.println(url.getFile());
		//获取该URL在文件中的相对位置
		System.out.println(url.getRef());
		//获取该URL的查询名
		System.out.println(url.getQuery());*/
		
	}

}

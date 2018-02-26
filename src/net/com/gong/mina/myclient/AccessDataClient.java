package net.com.gong.mina.myclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class AccessDataClient {
	private IoConnector connector;
	private InetSocketAddress isa;
	
	public AccessDataClient(IoConnector connector,InetSocketAddress isa){
		this.connector = connector;
		this.isa = isa;
	}

	static String sql = "SELECT top 2 * FROM Amf";  
	static String url = "jdbc:Access:///F://data//PDAUv.mdb";
	
	public static void main(String[] args) {
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ClientCodecFactory("UTF-8")));
		connector.setHandler(new MyClientHandler());
		//创建套接字对象
		InetSocketAddress isa = new InetSocketAddress("10.5.11.116", 12347);
		AccessDataClient mc = new AccessDataClient(connector,isa);
		
		//主线程
		mc.new MaindThread().start();
		//心跳线程
		//mc.new HeartbeatThread().start();
	}
	//数据传输线程
	class MaindThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					myODBC(sql,url);
					Thread.sleep(6000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	//心跳线程
	class HeartbeatThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					mySocket("##505##","心跳线程");
					Thread.sleep(3000);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//数据库查询
	private void myODBC(String sql, String url) throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection dbConn = null;
		ResultSet rs_Select = null;
		Statement stmt_Select = null;
		// 连接ACCESS数据库
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver");
			dbConn = DriverManager.getConnection(url);
			// 创建执行SQL的环境
			stmt_Select = dbConn.createStatement();
			// 执行SQL
			rs_Select = stmt_Select.executeQuery(sql);
			while (rs_Select.next()) { // 显示数据
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Date", rs_Select.getString("Date"));
				map.put("Name", rs_Select.getString("Name"));
				map.put("Avalue", rs_Select.getString("Avalue"));
				map.put("Zvalue", rs_Select.getString("Zvalue"));
				map.put("value", rs_Select.getString("value"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != rs_Select) {
					rs_Select.close();
				}
				if (null != stmt_Select) {
					stmt_Select.close();
				}
				if (null != dbConn) {
					dbConn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String key = "";
		Object value = null;
		for(Map<String,Object> dm : list){
			//数据包数据段字符串
			StringBuffer messageData = new StringBuffer("&&");
			Set<Entry<String,Object>> mtHourDataSets = dm.entrySet();
			String tableDate = null;
			for(Entry<String,Object> e : mtHourDataSets){
				key = e.getKey();
				value = e.getValue();
				
				if (value==null){
					continue;
				}
				if ("Date".equals(key)){
					tableDate = (String)value;
				}	
				messageData.append(key).append("=").append(e.getValue()).append(",");
			}
			//截去末尾多余逗号
			messageData.deleteCharAt(messageData.length()-1).append("&&");
			//数据包头字符串
			String head = "##";
			//数据段长度
			String length = getLength(messageData.toString());
			if("0".equals(length)){
				continue;
			}
			//数据包尾部字符串
			String end = "0000<CR><LF>";
			//数据包字符串
			String request = head+length+messageData.toString()+end;
			mySocket(request,tableDate);
		}	
	}
	
	//向服务端发送数据
	private synchronized void mySocket(String request,String type) throws Exception, IOException{	
		ConnectFuture future = connector.connect(isa);
		future.awaitUninterruptibly();
		IoSession session = future.getSession();
		try {
			session.write(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("已发送：" + type );
	}
	
	//格式化数据段长度字符串为子个字符，传入字符串长度超过4时返回"0"（例："250"格式化为 "0250"）
	private String getLength(String str){
		String length = str.length()+"";
		String result = length;
		if(4 > length.length()){
			for (int i = 0; i < 4 - length.length(); i++) {
				result = "0"+result;
			}
		}
		if(4 < length.length()){
			return "0";
		}
		return result;
	}
}

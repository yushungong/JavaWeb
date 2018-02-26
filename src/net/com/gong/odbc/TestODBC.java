package net.com.gong.odbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestODBC {

	public static void main(String[] args) {
		/*ReadDB read = new ReadDB();
		WriterDB write = new WriterDB();
		read.start();
		write.start();*/
		odbc();
	}
	private static void odbc(){
		Connection dbConn = null;
		ResultSet rs_Select = null;
		Statement stmt_Select = null;
		String sql = "SELECT top 1 * FROM Amf";
		//String url = "jdbc:odbc:myaccess";
		//String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=F://data//change.mdb";  
		String url = "jdbc:Access:///F://data//PDAUv.mdb";
		// ����ACCESS���ݿ�
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver"); 
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			dbConn = DriverManager.getConnection(url);
			// ����ִ��SQL�Ļ���
			stmt_Select = dbConn.createStatement();
			// ִ��SQL
			rs_Select = stmt_Select.executeQuery(sql);
			while (rs_Select.next()){ //��ʾ����
				System.out.println(rs_Select.getString("Name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=rs_Select){
					rs_Select.close();
				}
				if(null!=stmt_Select){
					stmt_Select.close();
				}
				if(null!=dbConn){
					dbConn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}				
		}

	}
}
class WriterDB extends Thread{
	public void run() {  
		Person[] person = {
			new Person("����", 18, "˾��"),
			new Person("����", 19, "����"),
			new Person("����", 12, "��ʦ"),
			new Person("����", 20, "����"),
			new Person("����", 21, "ҽ��"),
		};
		while (true) {
			Connection dbConn = null;
			Statement stmt_Select = null;
			for (int i = 0; i < person.length; i++) {
				String sql = "INSERT INTO test (name,age,job) VALUES ('"+person[i].getName()+"', '"+person[i].getAge()+"' ,'"+person[i].getJob()+"' )";
				String url = "jdbc:odbc:test";
				// ����ACCESS���ݿ�
				try {
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					dbConn = DriverManager.getConnection(url);
					dbConn.setAutoCommit(false);
					// ����ִ��SQL�Ļ���
					stmt_Select = dbConn.createStatement();
					// ִ��SQL
					int resule = stmt_Select.executeUpdate(sql);
					dbConn.commit();
					System.out.println(resule == 1 ? "����ɹ�" : "����ʧ��");
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(null!=stmt_Select){
							stmt_Select.close();
						}
						if(null!=dbConn){
							dbConn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}					
				}				
			}
		}
    }  
}
class ReadDB extends Thread{
	public void run() {  
		while(true){
			Connection dbConn = null;
			ResultSet rs_Select = null;
			Statement stmt_Select = null;
			String sql = "SELECT top 5 * FROM test order by ID desc";
			String url = "jdbc:odbc:test";
			// ����ACCESS���ݿ�
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				dbConn = DriverManager.getConnection(url);
				// ����ִ��SQL�Ļ���
				stmt_Select = dbConn.createStatement();
				// ִ��SQL
				rs_Select = stmt_Select.executeQuery(sql);
				while (rs_Select.next()){ //��ʾ����
					System.out.println(new Person(rs_Select.getString("name"),Integer.parseInt(rs_Select.getString("age")),rs_Select.getString("job")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(null!=rs_Select){
						rs_Select.close();
					}
					if(null!=stmt_Select){
						stmt_Select.close();
					}
					if(null!=dbConn){
						dbConn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		}
    }  
}
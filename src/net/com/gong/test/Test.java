package net.com.gong.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Test {

	public static void main(String[] args) {
		/*String direction = "N,NNE,NE,ENE,E,ESE,SE,SSE,S,SSW,SW,WSW,W,WNW,NW,NNW";
		String[] directions = direction.split(",");
		Float num = 11.26f;
		num = (float) (num + 11.25) % 360;
		System.out.println(num);
		int result = (int) (Math.ceil((num) / 22.5) - 1);
		System.out.println(directions[result]);*/
		/*File e = new File("\\\\10.5.10.48\\lidar_data");
		System.out.println(e.list().length);*/
		//String fileName = "NJ20161203124231.HPL";
		String fileName = "NJ2015090913.2616";
		//String f = "20140805160012";
		//char at = s.charAt(s.length()-5);
		String fileTime = fileName.substring(2,fileName.length()-5)+fileName.substring(fileName.length()-4, fileName.length());
		String a = "20000101";
		String b = "20170101";
		System.out.println(a.compareTo(b)<0);
		
		copyTo("F:\\data\\检测项符号和单位.txt", "F:\\data\\Picture\\ppp\\a.txt");
	}
	@SuppressWarnings("resource")
	public static void copyTo(String oldPath, String newPath) {
		File file = new File(newPath);
		createParentDir(file);
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				FileInputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}
	// 创建父目录
		private static void createParentDir(File file) {
			if (!file.getParentFile().exists()) {
				createParentDir(file.getParentFile());
				file.getParentFile().mkdir();
			}
		}
}

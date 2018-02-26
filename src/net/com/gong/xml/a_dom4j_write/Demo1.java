package net.com.gong.xml.a_dom4j_write;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 复制一个XML文件到指定位置
 *
 */
public class Demo1 {
	
	public static void main(String[] args) throws Exception{
		Document doc = new SAXReader().read(new File("./src/net/com/gong/xml/contact.xml"));
		
		FileOutputStream out = new FileOutputStream("./src/net/com/gong/xml/a_dom4j_write/contactWriter.xml");
		XMLWriter writer = new XMLWriter(out);
		
		writer.write(doc);
		writer.close();
	}

}

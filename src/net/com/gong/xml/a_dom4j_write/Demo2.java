package net.com.gong.xml.a_dom4j_write;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * 复制一个XML文件到指定位置，并指定文档内容格式和编码格式
 *
 */
public class Demo2 {
	public static void main(String[] args) throws Exception{
		Document doc = new SAXReader().read(new File("./src/net/com/gong/xml/contact.xml"));
		FileOutputStream out = new FileOutputStream("./src/net/com/gong/xml/a_dom4j_write/contactWriter.xml");
		//把文件生成为紧凑格式，程序上线时用
		OutputFormat format = OutputFormat.createCompactFormat();
		//生成格式化后的xml文档，程序开发调试时用
		//OutputFormat format = OutputFormat.createPrettyPrint();
		//指定生成的文档的编码
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(doc);
		writer.close();
	}

}

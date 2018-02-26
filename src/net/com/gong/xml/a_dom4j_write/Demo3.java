package net.com.gong.xml.a_dom4j_write;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
 * 创建一个新的xml文件
 * 修改文件内容
 */
public class Demo3 {

	@Test
	//新建
	public void test1() throws Exception{
		/**
		 * 1.创建一个document对象
		 */
		Document doc = DocumentHelper.createDocument();
		/**
		 * 2.创建根标签并添加
		 */
		Element rootElem = doc.addElement("contactList");
		//doc.addElement("contactList");
		Element contactElem = rootElem.addElement("contact");
		contactElem.addElement("name");
		/**
		 * 3.给标签添置属性，并赋值
		 */
		contactElem.addAttribute("id", "001");
		contactElem.addAttribute("name", "eric");
		
		//把修改后的doc对象写入到文件中
		FileOutputStream out = new FileOutputStream("./src/net/com/gong/xml/a_dom4j_write/newContactWriter.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 修改
	 */
	@Test
	public void test2()	throws Exception{
		Document doc = new SAXReader().read(new File("./src/contact.xml"));
		
		//得到标签对象
		Element contactElem = doc.getRootElement().element("contact");
		//得到属性对象
		Attribute idAttr = contactElem.attribute("id");
		//修改属性对象
		idAttr.setValue("003");
		
		//1.1  �õ���ǩ����
		
		//Element contactElem = doc.getRootElement().element("contact");
		//1.2 ͨ������ͬ�����Եķ������޸�����ֵ
		//contactElem.addAttribute("id", "004");
		
		/*Element nameElem = doc.getRootElement().
			element("contact").element("name");
		nameElem.setText("啊啊啊啊");*/
		
		
		
		FileOutputStream out = new FileOutputStream("e:/contact.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(doc);
		writer.close();
	}
	
	
	/**
	 * 删除标签
	 */
	@Test
	public void test3() throws Exception{
		Document doc = new SAXReader().read(new File("./src/contact.xml"));
		
		//删除标签
		
		Element ageElem = doc.getRootElement().element("contact")
					.element("age");
		
		//detach()：方法删除标签自身
		ageElem.detach();
		//remove(ageElem)：删除传入的子标签对象
		ageElem.getParent().remove(ageElem);
		
		/**
		 * 删除属性对象
		 */
		//�õ��ڶ���contact��ǩ
		Element contactElem = (Element)doc.getRootElement().
			elements().get(1);
		//2.2 �õ����Զ���
		Attribute idAttr = contactElem.attribute("id");
		
		idAttr.detach();
		idAttr.getParent().remove(idAttr);
		
		FileOutputStream out = new FileOutputStream("e:/contact.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(doc);
		writer.close();
	}
}

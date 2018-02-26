package net.com.gong.xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 读取xml文件内容
 * 节点
 * 标签
 * 属性
 * 文档
 * @author APPle
 *
 */
public class Demo2 {
	
	/**
	 * 获取节点信息
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test1() throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./src/net/com/gong/xml/contact.xml"));
		//得到当前节点下的所有子节点对象
		Iterator<Node> it = doc.nodeIterator();
		while(it.hasNext()){//判断是否有下一个元素
			Node node = it.next();//取出元素
			String name = node.getName();
			System.out.println(name);
			
			//System.out.println(node.getClass());
			//取出下面子节点
			//ֻ只有标签节点才有子节点
			//判断当前标签是否是标签节点
			if(node instanceof Element){
				Element elem = (Element)node;
				Iterator<Node> it2 = elem.nodeIterator();
				while(it2.hasNext()){
					Node n2 = it2.next();
					System.out.println(n2.getName());
				}
			}
		}
	}
	
	/**
	 * 遍历文件中所有节点
	 */
	@Test
	public void test2() throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./src/net/com/gong/xml/contact.xml"));
		//获取根标签
		Element rooElem = doc.getRootElement();
		getChildNodes(rooElem);

	}
	
	/**
	 * 获取传入的标签下的所有子节点
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void getChildNodes(Element elem){
		System.out.println(elem.getName());
		//得到子节点
		Iterator<Node> it = elem.nodeIterator();
		while(it.hasNext()){
			Node node = it.next();
			//判断是否只标签节点
			if(node instanceof Element){
				Element el = (Element)node;
				//是的话递归调用
				getChildNodes(el);
			}
		};
	}
	
	/**
	 * 获取标签节点
	 */
	@Test
	public void test3() throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./src/net/com/gong/xml/contact.xml"));
		
		//得到根标签
		Element  rootElem = doc.getRootElement();
		String name = rootElem.getName();
		//System.out.println(name);
		
		//得到当前标签下的第一个指定名称的标签
		Element contactElem = rootElem.element("contact");
		//System.out.println(contactElem.getName());
		
		
		//得到当前标签下的指定名称的所有子标签
		
		Iterator<Element> it = rootElem.elementIterator("contact");
		while(it.hasNext()){
			Element elem = it.next();
			//System.out.println(elem.getName());
		}
		
		
		//得到指定标签下的所有子标签
		List<Element> list = rootElem.elements();
		for(int i=0;i<list.size();i++){
			Element element = list.get(i);
			System.out.println(element.getName());
		}
		
	/*	for(Element e:list){
			System.out.println(e.getName());
		}*/
		/*
		Iterator<Element> it = list.iterator(); //ctrl+2 �ɿ� l
		while(it.hasNext()){
			Element elem = it.next();
			System.out.println(elem.getName());
		}*/
		
		Element nameElem = doc.getRootElement().
					element("contact").element("name");
		System.out.println(nameElem.getName());
		
	}
	
	/**
	 * 获取所有属性
	 */
	@Test
	public void test4() throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("./src/net/com/gong/xml/contact.xml"));
		
		//想要获取属性对象先要得到标签对象
		Element contactElem = doc.getRootElement().element("contact");
		//得到指定名称的属性值
		String idValue = contactElem.attributeValue("id");
		System.out.println(idValue);
		
		
		//得到指定名称的属性对象，对象中包含名称和值
		Attribute idAttr = contactElem.attribute("id");
		System.out.println(idAttr.getName() +"=" + idAttr.getValue());
		
		//获取当前标签对象的所有属性对象集合
		List<Attribute> list = contactElem.attributes();
		for (Attribute attr : list) {
			System.out.println(attr.getName()+"="+attr.getValue());
		}
		
		//获取当前标签对象的所有属性对象迭代器
		Iterator<Attribute> it = contactElem.attributeIterator();
		while(it.hasNext()){
			Attribute attr = it.next();
			System.out.println(attr.getName()+"="+attr.getValue());
		}
		
	}
	
	/**
	 * 获取文本
	 */
	@Test
	public void test5() throws Exception{
		SAXReader reader = new SAXReader();
					
		Document doc = reader.read(new File("./src/net/com/gong/xml/contact.xml"));
		
		
		/**
		 * 获取文本首先要获取标签对象
		 * 注意空格换行也是xml的内容
		 */
		String content = doc.getRootElement().getText();
		System.out.println(content);
		
		//获取当前标签的文本
		Element nameELem = 
			doc.getRootElement().element("contact").element("name");
		String text = nameELem.getText();
		System.out.println(text);
		
		//获取当前标签的指定子标签的文本
		String text2 = 
			doc.getRootElement().element("contact").elementText("phone");
		System.out.println(text2);
		
	}
	
	
}

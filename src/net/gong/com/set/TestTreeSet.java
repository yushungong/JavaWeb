package net.gong.com.set;

import java.util.Set;
import java.util.TreeSet;

public class TestTreeSet {

	/**
	 * @param yushun_gong
	 */
	public static void main(String[] args) {
		/*TreeSet:只能向集合中添加同一种类元素
		 * 可以按照添加进集合中 的元素的指定顺序遍历像String 包装类等默认按照从小到大的顺序遍历
		 * 当向集合中添加自定义类时，有两种排序方法：自然排序、定制排序
		 * 自然排序：要求自定义类实现Comparable接口并重写其compareTo(Object obj)方法
		 * 向集合中添加元素时，首先按照compareTo()进行比较，一旦返回0，虽然只是对象此属性相同，
		 * 但是程序会认为是两个对象相同，进而后一个对象就不能添加进来。
		 * compareTo()==hashCode()==equals(Object obj)三者保持一致。
		 * 
		 */
		Set<Person> t = new TreeSet<>();
		t.add(new Person("狗剩",12));
		t.add(new Person("狗蛋",13));
		t.add(new Person("铁锤",14));
		t.add(new Person("丧彪",15));
		for (Person object : t) {
			System.out.println(object.getName());
			
		}

	}

}

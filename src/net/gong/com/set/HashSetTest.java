package net.gong.com.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class HashSetTest {

	/**
	 * @param yushun_gong
	 */
	public static void main(String[] args) {
		/*
		 * Set 如果实现了set接口的集合类，具备的特点：无序，不可重复。
		 * 
		 * HashSet  底层是使用了哈希表来支持的，特点： 存取速度快.
		 * 存自定义类时，需要重写类的hashCode()方法和equals()方法，用来判断对象是否重复。
		 * hashSet的实现原理： 往Haset添加元素的时候，HashSet会先调用元素的hashCode方法得到元素的哈希值 ， 然后通过元素
		 * 的哈希值经过移位等运算，就可以算出该元素在哈希表中 的存储位置。
		 * 
		 * 情况1： 如果算出元素存储的位置目前没有任何元素存储，那么该元素可以直接存储到该位置上。
		 * 
		 * 情况2： 如果算出该元素的存储位置目前已经存在有其他的元素了，那么会调用该元素的equals方法与该位置的元素再比较一次
		 * ，如果equals返回的是true
		 * ，那么该元素与这个位置上的元素就视为重复元素，不允许添加，如果equals方法返回的是false，那么该元素运行 添加。
		 */
		/*Set set = new HashSet<>();
		set.add("张三");
		set.add("李四");
		set.add("王五");
		set.add("赵六");
		System.out.println(set);
		System.out.println(set.add("李四"));*/
		Set<Person> set = new HashSet<>();
		set.add(new Person("狗剩",12));
		set.add(new Person("狗蛋",13));
		set.add(new Person("铁锤",14));
		set.add(new Person("丧彪",15));
		for (Person object : set) {
			System.out.println(object.getName());
			
		}
	}
}

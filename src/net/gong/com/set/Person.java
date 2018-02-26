package net.gong.com.set;
/**TreeSet:****************************************
 * 只能向集合中添加同一种类元素
 * 可以按照添加进集合中 的元素的指定顺序遍历像String 包装类等默认按照从小到大的顺序遍历
 * 当向集合中添加自定义类时，有两种排序方法：自然排序、定制排序
 * 自然排序：要求自定义类实现Comparable接口并重写其compareTo(Object obj)方法
 * 向集合中添加元素时，首先按照compareTo()进行比较，一旦返回0，虽然只是对象此属性相同，
 * 但是程序会认为是两个对象相同，进而后一个对象就不能添加进来。
 * compareTo()==hashCode()==equals(Object obj)三者保持一致。
 * 
 */
public class Person implements Comparable<Object>{
	private String name;
	private int age;
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		Person other = (Person) obj;
		if(name.equals(other.getName())){
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Person){
			Person p = (Person)o;
			if(p.getAge()>this.getAge()){
				return -1;
			}else{
				return 1;
			}
		}
		return 0;
	}
}

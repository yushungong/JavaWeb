package net.com.gong.odbc;

public class Person {
	private String name;
	private Integer age;
	private String job;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String like) {
		this.job = like;
	}
	public Person(String name, Integer age, String like) {
		super();
		this.name = name;
		this.age = age;
		this.job = like;
	}
	@Override
	public String toString() {
		return "People [name=" + name + ", age=" + age + ", job=" + job + "]";
	}
}

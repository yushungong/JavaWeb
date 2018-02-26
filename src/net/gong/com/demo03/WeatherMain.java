package net.gong.com.demo03;

public class WeatherMain {

	public static void main(String[] args) throws Exception {
		Emp e = new Emp("老王");
		Emp e2 = new Emp("老李");

		Student s1 = new Student("二狗子");
		Student s2 = new Student("王伟");

		WeatherStation station = new WeatherStation();
		station.addListener(e);
		station.addListener(e2);
		station.addListener(s1);
		station.addListener(s2);

		station.startWork();

	}

}

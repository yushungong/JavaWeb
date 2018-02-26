package net.gong.com.demo03;

public class Emp implements Weather {

	String name;

	public Emp(String name) {
		this.name = name;
	}

	public void notifyWeather(String weather) {
		switch (weather) {
		case "晴天":
			System.out.println(name+"高高兴兴去上学。");
			break;
		case "下雨":
			System.out.println(name+"打伞去上学。");
			break;
		case "雾霾":
			System.out.println(name+"吸两口去上学。");
			break;
		case "刮风":
			System.out.println(name+"不去去上学。");
			break;
		case "冰雹":
			System.out.println(name+"还是不去上学。");
			break;
		default:
			break;
		}
	}

}

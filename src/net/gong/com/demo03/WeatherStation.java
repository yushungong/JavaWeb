package net.gong.com.demo03;

import java.util.ArrayList;
import java.util.Random;

public class WeatherStation {
	/**
	 * 观察者模式练习
	 * */
	String[] weathers = { "晴天", "下雨","雾霾", "刮风", "冰雹" };
	String weather;

	ArrayList<Weather> list = new ArrayList<Weather>();

	public void addListener(Weather e) {
		list.add(e);
	}

	public void startWork() {
		final Random random = new Random();

		new Thread() {
			@Override
			public void run() {
				while (true) {
					updateWeather();
					for (Weather e : list) {
						e.notifyWeather(weather);
					}

					int s = random.nextInt(501) + 1000; // 500
					try {
						Thread.sleep(s);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}.start();

	}

	public void updateWeather() {
		Random random = new Random();
		int index = random.nextInt(weathers.length);
		weather = weathers[index];
		System.out.println("现在的天气是" + weather);
	}

}

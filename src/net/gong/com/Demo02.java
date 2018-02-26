package net.gong.com;

public class Demo02 {

	/**
	 * @param args
	 */
	// 单例模式：懒汉模式
	//线程安全问题解决方法及性能优化方法
	public static void main(String[] args) {
		Demo02 s1 = Demo02.getInstance();
		Demo02 s2 = Demo02.getInstance();
		System.out.println(s1);
		System.out.println(s2);
	}

	private static Demo02 sing = null;

	private Demo02() {
		System.out.println("I am Singletion!");
	}

	public static Demo02 getInstance() {
		if (null == sing) {
			synchronized ("锁") {
				if (null == sing) {
					sing = new Demo02();
				}
			}
		}
		return sing;

	}

}

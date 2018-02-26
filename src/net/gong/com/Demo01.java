package net.gong.com;

/**
 * 模板设计模式步骤 
 * 1、先写出解决该类事情的一见到解决方案 
 * 2、分析代码，吧会发生变化的代码抽取出来独立成一个方法。法该方法描述成一个抽象方法。
 * 3、使用final修饰模板方法，防止别人重写，在继承类中重写模板方法。
 */

class Demo01 extends MyRuntime {
	public void code() {
		for (int i = 0; i < 100; i++) {
			System.out.println(i);
		}
	}

	public static void main(String[] args) {
		Demo01 d = new Demo01();
		d.getTime();
	}
}

abstract class MyRuntime {
	public final void getTime() {
		long startTime = System.currentTimeMillis();
		code();
		long endTime = System.currentTimeMillis();
		System.out.println("运行时间：" + (endTime - startTime));
	}

	public abstract void code();
}

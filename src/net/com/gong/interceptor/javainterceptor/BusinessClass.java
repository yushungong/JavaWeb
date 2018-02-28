package net.com.gong.interceptor.javainterceptor;

/**
 * 业务组件实现类
 * */
public class BusinessClass implements BusinessFacade {

	@Override
	public void doSomething() {
		System.out.println("在业务组件 BusinessClass 中调用方法: doSomething()");
	}

}

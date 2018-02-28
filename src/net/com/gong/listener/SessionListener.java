package net.com.gong.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听Session销毁的动作：
 *  当服务器销毁session的时候，从在线列表集合中移除当亲的登陆用户
 * @author Jie.Yuan
 *
 */
public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//1. 获取Session对象、ServletContext对象
		HttpSession session = se.getSession();
		ServletContext sc = session.getServletContext();
		
		//2. 获取Session中存储的当前登陆用户
		Object obj = session.getAttribute("loginInfo");//?
		
		//3. 获取ServletContext中存储的在线用户列表集合
		List<Object> list = (List<Object>) sc.getAttribute("onlineList");
		// 先判断
		if (obj != null){
			//4. 把“当前登陆用户”从在线列表集合中移除
			list.remove(obj);
		}
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
	}

}

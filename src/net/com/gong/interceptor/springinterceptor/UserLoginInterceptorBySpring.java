package net.com.gong.interceptor.springinterceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserLoginInterceptorBySpring extends HandlerInterceptorAdapter {
	// 在业务处理器处理请求之前被调用
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// equalsIgnoreCase 与 equals的区别？
		if ("GET".equalsIgnoreCase(request.getMethod())) {
			// RequestUtil.saveRequest();
		}
		System.out.println("preHandle...");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		System.out.println("requestUri" + requestUri);
		System.out.println("contextPath" + contextPath);
		System.out.println("url" + url);
		String username = (String) request.getSession()
				.getAttribute("username");
		if (null == username) {
			// 跳转到登录页面
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,
					response);
			return false;
		} else {
			return true;
		}
	}

	// 在业务处理器处理请求完成之后，生成视图之前执行
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle...");
		if (modelAndView != null) {
			Map<String, String> map = new HashMap<String, String>();
			modelAndView.addAllObjects(map);
		}
	}

	// 在DispatcherServlet完全处理完请求之后被调用，可用于清理资源
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion...");
	}
}

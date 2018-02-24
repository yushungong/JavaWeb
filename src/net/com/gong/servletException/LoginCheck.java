package net.com.gong.servletException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/test")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginCheck() {
        super();
    }
    //利用cookie实现自动登陆
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			String userName = (String) request.getParameter("userName");
			String password = (String) request.getParameter("password");
			if(userName != null && password != null){
				if(userName.equals("test") && password.equals("123456")){
					HttpSession session = request.getSession();
					session.setAttribute("userName", userName);
					String string = "userName="+userName+"&password="+password;
					
					//创建一个Cookie对象用于保存当前会话ID
					//注意：构造器的第一个参数必须是JSESSIONID，才能保存住当前会话ID
					//当下一次程序读到这个cookie时，会把这个JSESSIONID发送会服务器
					Cookie cookie = new Cookie("JSESSIONID", session.getId());
					//设置cookie最大有效时间，只要该值为正数，就会保存到硬盘上
					cookie.setMaxAge(1800);
					response.addCookie(cookie);
					//创建一个Cookie保存用户名和密码
					Cookie cookie2 = new Cookie("userInfo", string);
					cookie2.setMaxAge(1800);
					response.addCookie(cookie2);
					
					response.sendRedirect("loginCookie");
				}else{
					response.sendRedirect("login.jsp");				
				}
			}else{
				//用以测试servlet java异常处理
				String string = null;
				string.equals("是");
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			//错误页面显示指定HTTP状态代码及错误信息
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"异常提示内容");
			
			//也可以使用请求转发显示指定页面
			request.getRequestDispatcher("/错误页面路径").forward(request, response);
			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

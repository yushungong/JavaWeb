package net.com.gong.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginCookie")
public class LoginCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0){
			String name = null;
			String password = null;
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("userInfo")){
					String value = cookies[i].getValue();
					String[] cookieinif = value.split("&");
					for (int j = 0; j < cookieinif.length; j++) {
						String[] user = cookieinif[j].split("=");
						if(user[0].equals("userName")){
							name = user[1];
						}
						if(user[0].equals("password")){
							password = user[1];
						}
					}
				}
			}
			if(name.equals("test") && password.equals("123456")){
				response.sendRedirect("welcome.jsp");
			}else{
				response.sendRedirect("login.jsp");
			}
		}else{
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

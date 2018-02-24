package net.com.gong.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/firstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//使用servlet上下文记录网页访问次数
		ServletContext sContext = this.getServletContext();
		Integer counter = (Integer) sContext.getAttribute("counter");
		if(null == counter){
			counter = 1;
		}else{
			counter += 1;
		}
		sContext.setAttribute("counter", counter);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println("<html>该页面被访问了："+counter+"次</html>");
		printWriter.flush();
		printWriter.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

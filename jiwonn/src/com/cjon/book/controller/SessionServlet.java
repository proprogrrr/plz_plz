package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.cjon.book.service.BookService;

/**
 * Servlet implementation class BookUpdateServlet
 */
@WebServlet("/session")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String sessionid = (String) session.getAttribute("id");
		String callback = request.getParameter("callback");
		String result = null;
		boolean result1 = false;
			
		
		JSONObject obj = null;
		
		
		if(sessionid==null){
		
			obj = new JSONObject();
			obj.put("result", true);
			System.out.println("세션이 없어요. 로그인하세요!!");
			
			
		}else{
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			
			System.out.println(id);
			System.out.println(login);
			
			obj = new JSONObject();
			obj.put("result", false);
			
		}
		
		result = obj.toJSONString();

		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");
		out.flush();
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

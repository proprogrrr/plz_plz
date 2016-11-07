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
		
		String callback = request.getParameter("callback");
		String check = request.getParameter("quit");
		
		System.out.println("세션 체크 시작");
		
		HttpSession session = request.getSession(true);
		String sessionid = (String) session.getAttribute("id");
		
		
		if(check!=null){
			if(check.equals("t")){
				session.invalidate();
				System.out.println("세션을 만료시킵니다.");
			}
		}
	
	
		boolean result = true;
			
		if(sessionid!=null){
			result = true;
			System.out.println("세션이 존재합니다.");
		}else{
			result = false;
			System.out.println("세션이 존재하지 않습니다!!");
		}
		
		
	
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

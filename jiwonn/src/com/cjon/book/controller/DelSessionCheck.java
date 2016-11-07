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
 * Servlet implementation class BookListServlet
 */
@WebServlet("/delSessionCheck")
public class DelSessionCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DelSessionCheck() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 입력받고
		String callback = request.getParameter("callback");
		String check = request.getParameter("quit");
		String id = null;
		System.out.println("Del세션체크");
		String result = null;
		HttpSession session = request.getSession(true);
		
		
		if(session!=null){
			 id = (String) session.getAttribute("id");
			 JSONObject obj = new JSONObject();
			 obj.put("id", id);
			 result = obj.toJSONString();
		}else{
			
			JSONObject obj = new JSONObject();
			 obj.put("id", null);
			 result = obj.toJSONString();
		}
		
	
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback+"("+result+")");
		out.flush();
		out.close();
		
	}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

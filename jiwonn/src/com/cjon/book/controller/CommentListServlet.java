package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cjon.book.service.BookService;

/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/commentList")
public class CommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public CommentListServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reviewisbn = request.getParameter("reviewisbn");
		

		String callback = request.getParameter("callback");
	
		System.out.println("서평 리스트");
	
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("id");
		
		BookService service = new BookService();
		System.out.println("reviewisbn는"+reviewisbn);
		String result = service.commentList(reviewisbn);
		
		
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

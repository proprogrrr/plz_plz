package com.cjon.book.service;

import com.cjon.book.dao.BookDAO;

public class BookService {

	// 리스트를 가져오는 일을 하는 method
	public String getList(String keyword) {
		
		BookDAO dao = new BookDAO();
		String result = dao.select(keyword);	
		
		return result;
	}

	public String updateBook(String isbn, String title, String author, String price) {
		// TODO Auto-generated method stub
		BookDAO dao = new BookDAO();
		String result = dao.update(isbn,title,author,price);	
		return result;
	}
	public boolean deleteBook(String isbn) {
		// TODO Auto-generated method stub
		BookDAO dao = new BookDAO();
		boolean result = dao.deleteBook(isbn);	
		return result;
	}
	
	public String insertBook(String isbn, String img, String title, String author, String price) {
		// TODO Auto-generated method stub
		BookDAO dao = new BookDAO();
		String result = dao.insertBook(isbn,img,title,author,price);	
		return result;
	}
	
	public String bookDetail(String isbn) {
		// TODO Auto-generated method stub
		System.out.println("in service : "+isbn);
		BookDAO dao = new BookDAO();
		String result = dao.info(isbn);	
		return result;
	}

    public Boolean insertMember(String id, String password, String email) {
		
		BookDAO dao = new BookDAO();
		boolean result = dao.insertMem(id,password,email);
		return result;
	}
	
    
	public String selectOneMember(String id){
		
		BookDAO dao = new BookDAO();
		String result = dao.selectOneMember(id);
		return result;
		
	}
    
    
    
    public Boolean login(String id, String password) {
		
		BookDAO dao = new BookDAO();
		boolean result = dao.login(id,password);
		return result;
	}

	public Boolean session(String id, String password) {
	
		BookDAO dao = new BookDAO();
		boolean result = dao.session(id,password);
		return result;
	}

	
    public boolean commentInsert(String id, String isbn, String title, String author, String password, String text, String content,String img){
		
		BookDAO dao = new BookDAO();
		boolean result = dao.commentInsert(id,isbn,title,author,password,text,content,img);
		return result;
		
	}
	
	public String commentList(String reviewisbn){
		
		BookDAO dao = new BookDAO();
		String result = dao.commentList(reviewisbn);
		return result;
		
	}
	
public String commentContent(String cid){
		
		BookDAO dao = new BookDAO();
		String result = dao.commentContent(cid);
		return result;
		
	}
	
	public String commentKeywordList(String search){
		
		BookDAO dao = new BookDAO();
		String result = dao.commentKeywordList(search);
		return result;
		
	}
	
	public boolean deleteComment(int seq){
		
		BookDAO dao = new BookDAO();
		boolean result = dao.deleteComment(seq);
		return result;
		
	}
	
	

		public String userRentStatusList(String id, String userId){
			
			
			BookDAO dao = new BookDAO();
			String result = dao.userRentStatusList(id,userId);
			
			return result;
			
		}
		
		//도서 대여 관리를 위한 전체 도서 리스트 출력
		public String getrbookList(String keyword, String userId){
			
			
			BookDAO dao = new BookDAO();
			String result = dao.getrbookList(keyword,userId);
			
			return result;
			
		}
		
		//도서 대여 관리를 위한 도서 대여여부 업데이트
		public boolean updaterentbook(String keyword, String id){
				
				
			BookDAO dao = new BookDAO();
			boolean result = dao.updaterentbook(keyword,id);
			
			return result;
				
		}
		
		// 도서반납 하기  업데이트
			public boolean returnbookupdate(String keyword, String id){
					
					
				BookDAO dao = new BookDAO();
				boolean result = dao.returnbookupdate(keyword,id);
				
				return result;
					
			}
			
	
	
	
	
	
	
	
}













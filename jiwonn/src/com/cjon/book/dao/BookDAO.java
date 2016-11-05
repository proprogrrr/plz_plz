package com.cjon.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.book.common.DBTemplate;

public class BookDAO {

	public String select(String keyword) {
		// Database처리가 나와요!
		// 일반적으로 Database처리를 쉽게 하기 위해서
		// Tomcat같은 경우는 DBCP라는걸 제공해 줘요!
		// 추가적으로 간단한 라이브러리를 이용해서 DB처리를 해 볼꺼예요!!
		// 1. Driver Loading ( 설정에 있네.. )
		// 2. Connection 생성
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			String sql = "select bisbn, bimgbase64, btitle, bauthor, bprice "
					   + "from book where btitle like ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("img", rs.getString("bimgbase64"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
				obj.put("price", rs.getString("bprice"));
				arr.add(obj);
			}
			result = arr.toJSONString();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public String update(String isbn, String title, String author, String price) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		String result = null;
		try {
			System.out.println(isbn);
			System.out.println(title);
			System.out.println(author);
			System.out.println(price);
			
			String sql = "update book set btitle=?, bauthor=?, bprice=? where bisbn=?";
		
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setInt(3, Integer.parseInt(price));
			pstmt.setString(4, isbn);
			
			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
		
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
				
				String sql2 = "select bisbn, bimgurl, btitle, bauthor, bprice " + "from book where bisbn = ?";
				
				PreparedStatement pstmt2 = null;
				ResultSet rs = null;

				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, isbn);
				rs = pstmt2.executeQuery();

				rs.next();
				JSONObject obj = new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("img", rs.getString("bimgurl"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
				obj.put("price", rs.getString("bprice"));

				result = obj.toJSONString();
				
				DBTemplate.close(rs);
				DBTemplate.close(pstmt2);

			} else {
				DBTemplate.rollback(con);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean deleteBook(String isbn) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result=false;
		try {
			System.out.println(isbn);
			
			
			String sql = "delete from book where bisbn=?";
		
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			
			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			
			if( count == 1 ) {
				result = true;
				System.out.println("삭제완료");
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
				result= false;
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}
	
	
	
	public String insertBook(String isbn, String img, String title, String author, String price) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		String result = null;
		
		System.out.println(isbn+":"+img+":"+title+":"+author+":"+price);
		
		
		try {

			String sql = "insert into book(bisbn,bimgbase64,btitle, bauthor, bprice) values(?,?,?,?,?)";
		
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.setString(2, img);
			pstmt.setString(3, title);
			pstmt.setString(4, author);
			pstmt.setInt(5, Integer.parseInt(price));
			
			int rs=pstmt.executeUpdate();
			 if(rs==1){
				    result = "tre";
				    DBTemplate.commit(con);
				   System.out.println("도서 입력이 완료되었습니다."); 
				   }else{
				    DBTemplate.rollback(con);
				   }

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public String info(String isbn) {
		System.out.println("in DAO : "+isbn);
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			String sql = "select bdate, bpage, bsupplement, bpublisher from book where bisbn = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			
	        JSONObject obj = null;
			
			while (rs.next()) {
				obj = new JSONObject();
				obj.put("date", rs.getString("bdate"));
				obj.put("page", rs.getString("bpage"));
				obj.put("supplement", rs.getString("bsupplement"));
				obj.put("publisher", rs.getString("bpublisher"));	
			}
			result = obj.toJSONString();
		} catch (Exception e) {
			System.out.println("뭔가 이상합니다!");
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}
		System.out.println(result);
		return result;
	}

	
    public boolean insertMem(String id, String password, String email) {
		
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;

		boolean result = false;
		try {

			System.out.println(id);
			System.out.println(password);
			System.out.println(email);
			
			String sql = "insert into member (mid, mpassword, memail) value(?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
		
			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if (count == 1) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}
		return result;
	}

    public boolean login(String id, String password) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {

			System.out.println(id);
			System.out.println(password);
		
			
			String sql = "select * from member where mid=? and mpassword=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, password);
		
		
			rs = pstmt.executeQuery();
			// 결과값은 영향을 받은 레코드의 수
			
			if(rs.next()){
				System.out.println("로그인 ok");
				result = true;
			}else{
				result = false;
			}
	

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}
		return result;
		
	}

	public boolean session(String id, String password) {
		
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {

			System.out.println(id);
			System.out.println(password);
		
			
			String sql = "select * from member where mid=? and mpassword=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, password);
		
		
			rs = pstmt.executeQuery();
			// 결과값은 영향을 받은 레코드의 수
	

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		}
		return result;
	}

	
    
    
    
    
	
}

















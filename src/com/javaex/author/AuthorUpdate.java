package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorUpdate {

	public static void main(String[] args) {
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
		 // 1. JDBC 드라이버(Oracle) 로딩
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 2. Connection 얻어오기
		String url = "jdbc:mysql://localhost:3306/book_db";
		conn = DriverManager.getConnection(url, "book", "book");
		
		// 3. SQL문준비/ 바인딩/ 실행
		//- SQL문준비
		String query ="";
		query += " update author";
		query += " set author_name = ? ";
		query += " ,author_desc = ? ";
		query += " where author_id = ? ";
		
		//- 바인딩
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, "조앤롤링");
		pstmt.setString(2, "해리포터작가");
		pstmt.setInt(3,1);

		//- 실행
		int count = pstmt.executeUpdate();
		
		
		// 4.결과처리
		System.out.println(count + "건 수정되었습니다.");
		
		// 리스트를 이용해서 출력
		for(int i =0; i<authorList.size(); i++) {
			int no = authorList.get(i).getAuthorId() ;
			String name = authorList.get(i).getAuthorName();
			String desc = authorList.get(i).getAuthorDesc();
			
			System.out.println(no + ".\t " + name + "\t"+ desc);
		}
		
		} catch (ClassNotFoundException e) {
		 System.out.println("error: 드라이버로딩실패-"+ e);
		 } catch (SQLException e) {
		 System.out.println("error:" + e);
		 } finally {
		 // 5. 자원정리
		try {
		 if (rs!= null) {
		 rs.close();
		 }                
		if (pstmt != null) {
		 pstmt.close();
		 }
		 if (conn != null) {
		 conn.close();
		 }
		 } catch (SQLException e) {
		 System.out.println("error:" + e);
		 }
		 }

	}

}

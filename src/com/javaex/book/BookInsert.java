package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		 Connection conn = null;
		 PreparedStatement pstmt= null;
		 ResultSet rs= null;
		 
		 try {
		 // 1. JDBC 드라이버(Oracle) 로딩
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 
		// 2. Connection 얻어오기
			 String url = "jdbc:mysql://localhost:3306/book_db"; // 얘는 그냥 문자열로 나열한것. 어느 디비의 어디 ip주소의 어디 포트번호에서!
			 conn = DriverManager.getConnection(url, "book", "book");
			 
		// 3. SQL문준비/ 바인딩/ 실행
			 //- SQL문 준비
			 String query = "";
			 query += " insert into book ";
			 query += " values(null, ?, ?, ?, ?) ";
			 
			 //- 바인딩
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, "대학일기");
			 pstmt.setString(2, "RHK");
			 pstmt.setString(3, "2018-08-13");
			 pstmt.setString(4, "7");
			 
			 //- 실행
			 int count = pstmt.executeUpdate();
			 
		// 4.결과처리
			 System.out.println(count + "건 등록되었습니다.");
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

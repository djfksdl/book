package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDelete {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null; - select문에 쓰이는거라 생략가능
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";

			conn = DriverManager.getConnection(url, "book", "book");

			// 3. SQL문 준비 / 바인딩 / 실행 - 실제로 짜는곳
			// - SQL문 준비
			String query = "";
			query += " delete from author";
			query += " where author_id = ?"; // 값이 계속 바뀌는 영역은 ?로 바꾸기

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 8);// ?중에 첫번째꺼중에 0 그리고 0자리는 ?값이다. 숫자 바꿔도됨

			// - 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리

			System.out.println(count + "건이 삭제 되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				/*
				 * if (rs != null) { rs.close(); } -- select문에 쓰이는거라 생략 가능
				 */
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

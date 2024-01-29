package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db"; //어디 ip주소(localhost)의 어디 포트번호에서(:3306) 어디 디비(book_db)에서!
			conn = DriverManager.getConnection(url, "book", "book"); // 경로, 아이디, 비번으로 연결. 
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//sql문 준비 -- 줄 바꿈 되어있는 모습을 유지하기 위해서 이렇게 씀. 줄바꿈 안해놓으면 가독성 떨어지고 나중에 수정할때 힘듦.
			String query = "";
			query += " select author_id "; 
			query += " ,author_name ";
			query += " ,author_desc ";
			query += " from author ";
			
			//바인딩 - 물음표에 실제 값을 매칭시켜준다.
			pstmt = conn.prepareStatement(query);
			// pstmt.setInt(0, 0);는 안해도됨. ?가 없으니까!
			
			//실행
			rs = pstmt.executeQuery(); //나머지 3개와 다르게 select는 executeQuery로 불러온다.
			//resultSet이 rs따라가면 있다. 그안에 db값 넣어놨음. 우리는 그래서 메소드 잘 쓰면 된다.
			//보관은 리스트에 담는것
			
			// 4.결과처리
			/*rs.next();
			rs.next();// 하나 더 붙이면 2번째것 꺼내옴 
			
			int id = rs.getInt("author_id"); // 숫자 꺼내는거라 Int이다.
			System.out.println(id);
			
			String name =rs.getString("author_name");
			System.out.println(name);
			//한줄 통으로 못꺼내고 한땀한땀 꺼내야함. 
			
			String desc = rs.getString("author_desc");
			System.out.println(desc);*/
			// 실제로 꺼낼땐 while문으로 쓴다. 
			while(rs.next()) { // true 값이 들어있으면 계속 돈다. 얼만큼 도는지 알 수 없기 때문에 while씀
				/*if() {
					break;
				} 저렇게 쓰면 이건 크게 신경 안써도 됨. */
				/*
				 * int id = rs.getInt(1);
				 * String name = rs.getString(2);
				 * String desc = rs.getString(3);
				 */
				
				int id = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");
						
				System.out.println(id +", " +name +", "+desc);
				
				
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
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

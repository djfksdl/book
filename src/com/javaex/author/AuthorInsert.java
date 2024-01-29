package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorInsert {
	public static void main(String[] args) {
		
		// 번호는 알아서(auto increment) 황일영 학원강사 - 추가
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");  // 이 클래스를 메모리에 new로 올리겠다.


		    // 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db"; // 얘는 그냥 문자열로 나열한것. 어느 디비의 어디 ip주소의 어디 포트번호에서!
			conn = DriverManager.getConnection(url, "book", "book"); // DriverManager는 static으로 올라가있음. (url , id , pw) url은 길어서 한눈에 보기 힘들어서 따로 빼둔것.id랑 pw도 따로 빼놔도 됨


		    // 3. SQL문 준비 / 바인딩 / 실행 - 쿼리문 복사해온거 줄바꿈 없이 그대로 옮기는게 나음
			//sql문 준비
			String query = "";
			query += " insert into author"; // 앞에 한칸 안띄워주면 authorvalues가 되어서 안된다.근데 뒤에 띄우면 빠질 수 있으니까 앞에 다 띄워놓는다
			query += " values(null,? ,?)"; // 원래 쿼리문에 있는 마지막 ;는 빼기. 값이 뭐가 올지 모르면 ?를 넣어줘야함. 
			
			//바인딩 - 물음표에 실제 값을 매칭시켜준다.
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"정우성");
			pstmt.setString(2, "영화배우");
			
			//실행
			int count = pstmt.executeUpdate();
			
			
		    // 4.결과처리
			System.out.println(count +"건 등록되었습니다.");
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

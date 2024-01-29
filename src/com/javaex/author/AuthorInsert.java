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
//		ResultSet rs = null; 이건 검색할때쓰는거라 여기선 쓰이지않음

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩 : 드라이버 메인이 되는애를 띄우라. 그래야 우리가 쓸 수 있다. 생성자로 쓰라는 얘기
			Class.forName("com.mysql.cj.jdbc.Driver");  // 이 클래스를 메모리에 new로 올리겠다.(new랑 class.forname으로 메모리에 올리는 방법이 있다.)- mySql쓰는 이상 이거 그대로 씀. 오라클이나 다른거면 달라질 수 있음.


		    // 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db"; // 얘는 그냥 문자열로 나열한것. 어느 디비의 어디 ip주소의 어디 포트번호에서!
			conn = DriverManager.getConnection(url, "book", "book"); // DriverManager는 static으로 올라가있음(대문자로시작). (url , id , pw) url은 길어서 한눈에 보기 힘들어서 따로 빼둔것.id랑 pw도 따로 빼놔도 됨
			// mysql에서 연결할때 쓰라고 DrivateManager만들어줌. 


		    // 3. SQL문 준비 / 바인딩 / 실행 - 쿼리문 복사해온거 줄바꿈 없이 그대로 옮기는게 나음/ insert, update, delete는 같은 패턴임
			//sql문 준비
			String query = "";
			query += " insert into author"; // 앞에 한칸 안띄워주면 authorvalues가 되어서 안된다.근데 뒤에 띄우면 빠질 수 있으니까 앞에 다 띄워놓는다
			query += " values(null,? ,?)"; // 원래 쿼리문에 있는 마지막 ;는 빼기. 값이 뭐가 올지 모르면 ?를 넣어줘야함. 
			
			//바인딩 - 물음표에 실제 값을 매칭시켜준다.
			pstmt = conn.prepareStatement(query); // 물음표를 매칭시킬 수 있는 도구?로 바꿔주는것 위에 query를 바로 쓸수가 없기 때문에 변환을 시켜주는 역할
			pstmt.setString(1,"정우성"); // 첫번째 물음표에 값 매칭
			pstmt.setString(2, "영화배우");
			
			//실행
			int count = pstmt.executeUpdate(); // 굳이 안알려줘도되는데 알려주는것. insert는 보통 1개씩되는데 delete는 범위때문에 10개도 넘게 지워질 수 있음. 
			// count는 3개나 1개 등등 갯수 알려줌. 그래서 insert,update,delete는 이걸 써야 쿼리문대로 실행하고 갯수 알려줌. 
			// select는 숫자가 아닌 데이터를 알려줘야하기때문에 executeUpdate를 쓰지않고 다른걸 씀. 
			
			
		    // 4.결과처리
			System.out.println(count +"건 등록되었습니다.");
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리 - 다 닫아주는거임. 
		    try {
//		        if (rs != null) {
//		            rs.close();
//		        }                
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

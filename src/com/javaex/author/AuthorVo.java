package com.javaex.author;

public class AuthorVo {

		//필드
		private int authorId; // 자바에서는 _를 잘안씀
		private String authorName;
		private String authorDesc;

		
		//생성자
		public AuthorVo() {
			super();
		}
		
		public AuthorVo(int authorId, String authorName, String authorDesc) {
			super();
			this.authorId = authorId;
			this.authorName = authorName;
			this.authorDesc = authorDesc;
		}


		
		//메소드 - gs
		public int getAuthorId() {
			return authorId;
		}

		public void setAuthorId(int authorId) {
			this.authorId = authorId;
		}

		public String getAuthorName() {
			return authorName;
		}

		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}

		public String getAuthorDesc() {
			return authorDesc;
		}

		public void setAuthorDesc(String authorDesc) {
			this.authorDesc = authorDesc;
		}

		
		//메소드 - 일반
		@Override
		public String toString() {
			return "AuthorVo [authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
		}
		
		
	

}
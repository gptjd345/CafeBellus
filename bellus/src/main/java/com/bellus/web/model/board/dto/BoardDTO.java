package com.bellus.web.model.board.dto;

import java.util.Arrays;
import java.util.Date;

//회원 게시판 관련 dto 클래스
public class BoardDTO {
	
	    private int bno; // 게시물 번호
	    private String title; // 제목
	    private String content; // 내용
	    private String writer; // 작성자 아이디
	    private Date regdate; // 날짜
	    private int viewcnt; // 조회수
	    private Date updatedate; //글 수정날짜
		private String name; // 작성자 이름 (member 테이블과 조인할것을 고려해서 만들었음)
	    private int cnt; // 댓글 수
	    private String show; // 화면 표시 여부
	    private String[] files; // 첨부파일 배열
	    
	    public Date getUpdatedate() {
	    	return updatedate;
	    }
	    public void setUpdatedate(Date updatedate) {
	    	this.updatedate = updatedate;
	    }
		public int getBno() {
			return bno;
		}
		public void setBno(int bno) {
			this.bno = bno;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getWriter() {
			return writer;
		}
		public void setWriter(String writer) {
			this.writer = writer;
		}
		public Date getRegdate() {
			return regdate;
		}
		public void setRegdate(Date regdate) {
			this.regdate = regdate;
		}
		public int getViewcnt() {
			return viewcnt;
		}
		public void setViewcnt(int viewcnt) {
			this.viewcnt = viewcnt;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getCnt() {
			return cnt;
		}
		public void setCnt(int cnt) {
			this.cnt = cnt;
		}
		public String getShow() {
			return show;
		}
		public void setShow(String show) {
			this.show = show;
		}
		public String[] getFiles() {
			return files;
		}
		public void setFiles(String[] files) {
			this.files = files;
		}
		@Override
		public String toString() {
			return "BoardDTO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer
					+ ", regdate=" + regdate + ",updatedate= "+updatedate+" , viewcnt=" + viewcnt + ", name=" + name + ", cnt=" + cnt
					+ ", show=" + show + ", files=" + Arrays.toString(files) + "]";
		}
		    
		    
		    
}

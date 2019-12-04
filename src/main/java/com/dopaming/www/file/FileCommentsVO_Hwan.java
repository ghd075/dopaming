package com.dopaming.www.file;

import java.sql.Date;

public class FileCommentsVO_Hwan {
	//댓글번호
	private int comment_no;
	//댓글내용
	private String comment_content;
	//회원아이디
	private String member_id;
	//등록날짜
	private Date reg_date;
	//게시판번호
	private int board_no;
	
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}	
}

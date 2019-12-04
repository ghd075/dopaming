package com.dopaming.www.admin.complain;

import java.sql.Date;

public class ComplainVO {
	//테이블
	private int complain_no;
	private String complain_type;
	private String complain_title;
	private String complain_content;
	private Date complain_date;
	private String complain_check;
	private String member_id;
	private int board_no;
	//로우넘
	private int rn;
	//페이징
	private int first;
	private int last;
	
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getComplain_no() {
		return complain_no;
	}
	public void setComplain_no(int complain_no) {
		this.complain_no = complain_no;
	}
	public String getComplain_type() {
		return complain_type;
	}
	public void setComplain_type(String complain_type) {
		this.complain_type = complain_type;
	}
	public String getComplain_title() {
		return complain_title;
	}
	public void setComplain_title(String complain_title) {
		this.complain_title = complain_title;
	}
	public String getComplain_content() {
		return complain_content;
	}
	public void setComplain_content(String complain_content) {
		this.complain_content = complain_content;
	}
	public Date getComplain_date() {
		return complain_date;
	}
	public void setComplain_date(Date complain_date) {
		this.complain_date = complain_date;
	}
	public String getComplain_check() {
		return complain_check;
	}
	public void setComplain_check(String complain_check) {
		this.complain_check = complain_check;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
}

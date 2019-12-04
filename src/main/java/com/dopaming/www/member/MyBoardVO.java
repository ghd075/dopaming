package com.dopaming.www.member;

import java.sql.Date;

public class MyBoardVO {
	private Integer download_no;
	private Integer board_no;
	private String board_title;
	private String member_id;
	private String sal_id;
	private String category_big;
	private Date download_date;
	private Integer first;
	private Integer last;

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getLast() {
		return last;
	}

	public void setLast(Integer last) {
		this.last = last;
	}

	public Integer getDownload_no() {
		return download_no;
	}

	public void setDownload_no(Integer download_no) {
		this.download_no = download_no;
	}

	public Integer getBoard_no() {
		return board_no;
	}

	public void setBoard_no(Integer board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getSal_id() {
		return sal_id;
	}

	public void setSal_id(String sal_id) {
		this.sal_id = sal_id;
	}

	public String getCategory_big() {
		return category_big;
	}

	public void setCategory_big(String category_big) {
		this.category_big = category_big;
	}

	public Date getDownload_date() {
		return download_date;
	}

	public void setDownload_date(Date download_date) {
		this.download_date = download_date;
	}
}

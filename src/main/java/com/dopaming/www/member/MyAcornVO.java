package com.dopaming.www.member;

import java.sql.Date;

public class MyAcornVO {
	private Integer acorn_no;
	private String[] seqs;
	private String member_id;
	private Integer acorn_point;
	private String acorn_content;
	private Integer acorn_stock;
	private Date acorn_date;
	private Integer first;
	private Integer last;
	private String searchKeyword;
	private String searchCondition;
	
	public Integer getAcorn_no() {
		return acorn_no;
	}
	
	public void setAcorn_no(Integer acorn_no) {
		this.acorn_no = acorn_no;
	}
	
	public String[] getSeqs() {
		return seqs;
	}

	public void setSeqs(String[] seqs) {
		this.seqs = seqs;
	}

	public String getMember_id() {
		return member_id;
	}
	
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public Integer getAcorn_point() {
		return acorn_point;
	}
	
	public void setAcorn_point(Integer acorn_point) {
		this.acorn_point = acorn_point;
	}
	
	public String getAcorn_content() {
		return acorn_content;
	}
	
	public void setAcorn_content(String acorn_content) {
		this.acorn_content = acorn_content;
	}
	
	public Integer getAcorn_stock() {
		return acorn_stock;
	}

	public void setAcorn_stock(Integer acorn_stock) {
		this.acorn_stock = acorn_stock;
	}

	public Date getAcorn_date() {
		return acorn_date;
	}
	
	public void setAcorn_date(Date acorn_date) {
		this.acorn_date = acorn_date;
	}
	
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

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}	
}

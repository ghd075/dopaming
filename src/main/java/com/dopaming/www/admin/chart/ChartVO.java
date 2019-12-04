package com.dopaming.www.admin.chart;

import java.util.Date;

public class ChartVO {
	private String member_id;
	private String member_password;
	private String member_email;
	private Date member_date;
	private String member_grade;
	private float member_upload_storage;
	private int rn;
	private int member_upload_count;
	private int down_count;
	private String grade_kor;
	//페이징하기위한 첫,마지막번호
	private int first;
	private int last;
	
	private String searchKeyword;
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public Date getMember_date() {
		return member_date;
	}
	public void setMember_date(Date member_date) {
		this.member_date = member_date;
	}
	public String getMember_grade() {
		return member_grade;
	}
	public void setMember_grade(String member_grade) {
		this.member_grade = member_grade;
	}
	public float getMember_upload_storage() {
		return member_upload_storage;
	}
	public void setMember_upload_storage(float member_upload_storage) {
		this.member_upload_storage = member_upload_storage;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getMember_upload_count() {
		return member_upload_count;
	}
	public void setMember_upload_count(int member_upload_count) {
		this.member_upload_count = member_upload_count;
	}
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
	
	public int getDown_count() {
		return down_count;
	}
	public void setDown_count(int down_count) {
		this.down_count = down_count;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getGrade_kor() {
		return grade_kor;
	}
	public void setGrade_kor(String grade_kor) {
		this.grade_kor = grade_kor;
	}
	@Override
	public String toString() {
		return "ChartVO [member_id=" + member_id + ", member_password=" + member_password + ", member_email="
				+ member_email + ", member_date=" + member_date + ", member_grade=" + member_grade
				+ ", member_upload_storage=" + member_upload_storage + ", rn=" + rn + ", member_upload_count="
				+ member_upload_count + ", down_count=" + down_count + ", grade_kor=" + grade_kor + ", first=" + first
				+ ", last=" + last + ", searchKeyword=" + searchKeyword + "]";
	}
	
	
	
	
}

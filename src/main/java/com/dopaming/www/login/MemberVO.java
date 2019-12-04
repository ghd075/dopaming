package com.dopaming.www.login;

import java.sql.Date;

public class MemberVO {
	private String member_id;
	private String member_password;
	private String checkPassword;
	private String member_email;
	private Date member_date;
	private String member_grade;
	private Integer acorn_stock;
	private Integer upload_storage;
	private Integer upload_count;
	private String member_type;
	private String member_code;
	private String member_auth;
	private String grade_kor;
	private String black_id;
	
	//비밀번호 확인
	public boolean isPwEqualToCheckPw() {
		return member_password.equals(checkPassword);
	}
	
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

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
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

	public Integer getAcorn_stock() {
		return acorn_stock;
	}

	public void setAcorn_stock(Integer acorn_stock) {
		this.acorn_stock = acorn_stock;
	}

	public Integer getUpload_storage() {
		return upload_storage;
	}

	public void setUpload_storage(Integer upload_storage) {
		this.upload_storage = upload_storage;
	}

	public Integer getUpload_count() {
		return upload_count;
	}

	public void setUpload_count(Integer upload_count) {
		this.upload_count = upload_count;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	
	public String getMember_code() {
		return member_code;
	}

	public void setMember_code(String member_code) {
		this.member_code = member_code;
	}

	public String getMember_auth() {
		return member_auth;
	}

	public void setMember_auth(String member_auth) {
		this.member_auth = member_auth;
	}

	public String getGrade_kor() {
		return grade_kor;
	}

	public void setGrade_kor(String grade_kor) {
		this.grade_kor = grade_kor;
	}
	
	public String getBlack_id() {
		return black_id;
	}

	public void setBlack_id(String black_id) {
		this.black_id = black_id;
	}

	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", member_password=" + member_password + ", member_email="
				+ member_email + ", member_date=" + member_date + ", member_grade=" + member_grade + ", acorn_stock="
				+ acorn_stock + ", upload_storage=" + upload_storage + ", member_type=" + member_type + ", member_code="
				+ member_code + ", member_auth=" + member_auth + "]";
	}	
}

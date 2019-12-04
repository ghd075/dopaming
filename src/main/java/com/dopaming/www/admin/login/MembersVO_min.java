package com.dopaming.www.admin.login;

import java.util.Date;

public class MembersVO_min {
	private String member_id;
	private String member_password;
	private String member_email;
	private Date member_date;
	private String member_grade;
	private float member_upload_storage;
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
	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", member_password=" + member_password + ", member_email="
				+ member_email + ", member_date=" + member_date + ", member_grade=" + member_grade
				+ ", member_upload_storage=" + member_upload_storage + "]";
	}
}

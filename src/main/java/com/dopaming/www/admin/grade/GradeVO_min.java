package com.dopaming.www.admin.grade;

public class GradeVO_min {
	private String member_grade;
	private float grade_rate;
	private int max_upload;
	private String member_grade_kor; //듣급한글(ex.다이아몬드 도토리)
	
	//등급관리를 위한 id, 총결제액, ,현재 등급, 총 업로드수
	private String member_id;
	private int acron;
	private String grade_kor;
	private int upload_count;
	
	//페이징하기위한 첫,마지막번호
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
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getAcron() {
		return acron;
	}
	public void setAcron(int acron) {
		this.acron = acron;
	}
	public String getGrade_kor() {
		return grade_kor;
	}
	public void setGrade_kor(String grade_kor) {
		this.grade_kor = grade_kor;
	}
	public int getUpload_count() {
		return upload_count;
	}
	public void setUpload_count(int upload_count) {
		this.upload_count = upload_count;
	}
	public String getMember_grade() {
		return member_grade;
	}
	public void setMember_grade(String member_grade) {
		this.member_grade = member_grade;
	}
	public float getGrade_rate() {
		return grade_rate;
	}
	public void setGrade_rate(float grade_rate) {
		this.grade_rate = grade_rate;
	}
	public int getMax_upload() {
		return max_upload;
	}
	public void setMax_upload(int max_upload) {
		this.max_upload = max_upload;
	}
	public String getMember_grade_kor() {
		return member_grade_kor;
	}
	public void setMember_grade_kor(String member_grade_kor) {
		this.member_grade_kor = member_grade_kor;
	}
}

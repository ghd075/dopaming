package com.dopaming.www.acorn;

import java.sql.Date;

public class AcornVO {
	private int acorn_no;
	private String member_id;
	private int acorn_charge;
	private int acorn_pay;
	private String user_cache;
	private Date acorn_date;
	public int getAcorn_no() {
		return acorn_no;
	}
	public void setAcorn_no(int acorn_no) {
		this.acorn_no = acorn_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getAcorn_charge() {
		return acorn_charge;
	}
	public void setAcorn_charge(int acorn_charge) {
		this.acorn_charge = acorn_charge;
	}
	public int getAcorn_pay() {
		return acorn_pay;
	}
	public void setAcorn_pay(int acorn_pay) {
		this.acorn_pay = acorn_pay;
	}
	public String getUser_cache() {
		return user_cache;
	}
	public void setUser_cache(String user_cache) {
		this.user_cache = user_cache;
	}
	public Date getAcorn_date() {
		return acorn_date;
	}
	public void setAcorn_date(Date acorn_date) {
		this.acorn_date = acorn_date;
	}
	@Override
	public String toString() {
		return "AcornVO [acorn_no=" + acorn_no + ", member_id=" + member_id + ", acorn_charge=" + acorn_charge
				+ ", acorn_pay=" + acorn_pay + ", user_cache=" + user_cache + ", acorn_date=" + acorn_date + "]";
	}
}

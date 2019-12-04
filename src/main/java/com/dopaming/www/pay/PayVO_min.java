package com.dopaming.www.pay;

import java.sql.Date;

public class PayVO_min {
	private int acorn_no;
	private String member_id;
	private int acorn_charge;
	private int acorn_pay;
	private String acron_content;
	private String acorn_date;
	private String pay_code;
	private String shop_code;
	private String approval_code;
	
	//페이징하기위한 첫,마지막번호
	private int first;
	private int last;
	
	
	public int getAcorn_charge() {
		return acorn_charge;
	}
	public void setAcorn_charge(int acorn_charge) {
		this.acorn_charge = acorn_charge;
	}
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
	public int getAcorn_pay() {
		return acorn_pay;
	}
	public void setAcorn_pay(int acorn_pay) {
		this.acorn_pay = acorn_pay;
	}
	public String getAcron_content() {
		return acron_content;
	}
	public void setAcron_content(String acron_content) {
		this.acron_content = acron_content;
	}
	
	
	public String getAcorn_date() {
		return acorn_date;
	}
	public void setAcorn_date(String acorn_date) {
		this.acorn_date = acorn_date;
	}
	public String getPay_code() {
		return pay_code;
	}
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	public String getShop_code() {
		return shop_code;
	}
	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}
	public String getApproval_code() {
		return approval_code;
	}
	public void setApproval_code(String approval_code) {
		this.approval_code = approval_code;
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
	
	
}

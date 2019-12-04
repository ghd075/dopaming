package com.dopaming.www.admin.recash;

public class ReCashVO {
	
	private String reg_no;
	private String member_id;
	private String bank;
	private String account_no;
	private String account_own;
	private String reg_recash;
	private String reg_date;
	private String recash_date;
	private String state;
	private int first;
	private int last;

		
	//first
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	//last
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	//reg_no
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	//member_id
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	//bank
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	//account_no
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	//account_own
	public String getAccount_own() {
		return account_own;
	}
	public void setAccount_own(String account_own) {
		this.account_own = account_own;
	}
	//reg_recash
	public String getReg_recash() {
		return reg_recash;
	}
	public void setReg_recash(String reg_recash) {
		this.reg_recash = reg_recash;
	}
	//reg_date
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	//recash_date
	public String getRecash_date() {
		return recash_date;
	}
	public void setRecash_date(String recash_date) {
		this.recash_date = recash_date;
	}
	//state
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	@Override
	public String toString() {
		return "ReCashVO [reg_no=" + reg_no + ", member_id=" + member_id + ", bank=" + bank + ", account_no="
				+ account_no + ", account_own=" + account_own + ", reg_recash=" + reg_recash + ", reg_date=" + reg_date
				+ ", recash_date=" + recash_date + ", state=" + state + ", first=" + first + ", last=" + last + "]";
	}		
}










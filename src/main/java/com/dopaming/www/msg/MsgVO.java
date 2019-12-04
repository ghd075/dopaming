package com.dopaming.www.msg;

public class MsgVO {
	
	private String message_no;
	private String sender_id;
	private String message_title;
	private String message_date;
	private String message_content;
	private String receiver_id;
	private String message_check;
	private String [] ck;
	private int cnt;
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
	//cnt
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	//ck[]
	public String[] getCk() {
		return ck;
	}
	public void setCk(String[] ck) {
		this.ck = ck;
	}
	//message_no
	public String getMessage_no() {
		return message_no;
	}
	public void setMessage_no(String message_no) {
		this.message_no = message_no;
	}
	//sender_id
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	//message_title
	public String getMessage_title() {
		return message_title;
	}
	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}
	//message_date
	public String getMessage_date() {
		return message_date;
	}
	public void setMessage_date(String message_date) {
		this.message_date = message_date;
	}
	//message_content
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	//receiver_id
	public String getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	//message_check
	public String getMessage_check() {
		return message_check;
	}
	public void setMessage_check(String message_check) {
		this.message_check = message_check;
	}
	
	
	@Override
	public String toString() {
		return "MsgVO [message_no=" + message_no + ", sender_id=" + sender_id + ", message_title=" + message_title
				+ ", message_date=" + message_date + ", message_content=" + message_content + ", receiver_id="
				+ receiver_id + ", message_check=" + message_check + "]";
	}
}








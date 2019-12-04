package com.dopaming.www.file;

import java.sql.Date;

public class FileVO {
	private String board_title;
	private String file_name;
	private String board_name;
	private String board_no;
	private int file_no;
	private double file_storage;
	private Date upload_date;
	private String member_id;
	private String category_small;
	private String category_big;
	private String boardImg;
	private int upload_storage;
	private int rn;
		
	//페이징하기위한 첫,마지막번호
	private int first;
	private int last;
	
	//검색위한 String
	String searchKeyword;
	
	
	public int getUpload_storage() {
		return upload_storage;
	}
	public void setUpload_storage(int upload_storage) {
		this.upload_storage = upload_storage;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public String getBoard_no() {
		return board_no;
	}
	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
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
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public String getCategory_small() {
		return category_small;
	}
	public void setCategory_small(String category_small) {
		this.category_small = category_small;
	}
	public String getCategory_big() {
		return category_big;
	}
	public void setCategory_big(String category_big) {
		this.category_big = category_big;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoardTitle(String board_title) {
		this.board_title = board_title;
	}
	
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	
	
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	public double getFile_storage() {
		return file_storage;
	}
	public void setFile_storage(double file_storage) {
		this.file_storage = file_storage;
	}
	public String getBoardImg() {
		return boardImg;
	}
	public void setBoardImg(String boardImg) {
		this.boardImg = boardImg;
	}
	@Override
	public String toString() {
		return "FileVO [board_title=" + board_title + ", file_name=" + file_name + ", board_name=" + board_name
				+ ", board_no=" + board_no + ", file_no=" + file_no + ", file_storage=" + file_storage
				+ ", upload_date=" + upload_date + ", member_id=" + member_id + ", category_small=" + category_small
				+ ", category_big=" + category_big + ", boardImg=" + boardImg + ", upload_storage=" + upload_storage
				+ ", rn=" + rn + ", first=" + first + ", last=" + last + ", searchKeyword=" + searchKeyword + "]";
	}

	
	
	
	


	
	
}

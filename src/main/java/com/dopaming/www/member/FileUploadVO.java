package com.dopaming.www.member;

import java.sql.Date;

public class FileUploadVO {
	private Integer board_no;
	private String[] seqs;
	private String board_title;
	private String category_big;
	private String member_id;
	private String file_storage;
	private Date upload_date;
	private String path;
	private Integer first;
	private Integer last;
	private int down_count;
		
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

	public Integer getBoard_no() {
		return board_no;
	}

	public void setBoard_no(Integer board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getCategory_big() {
		return category_big;
	}

	public void setCategory_big(String category_big) {
		this.category_big = category_big;
	}

	public String getMember_id() {
		return member_id;
	}
	
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getFile_storage() {
		return file_storage;
	}
	
	public void setFile_storage(String file_storage) {
		this.file_storage = file_storage;
	}
	
	public Date getUpload_date() {
		return upload_date;
	}
	
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	public String[] getSeqs() {
		return seqs;
	}

	public void setSeqs(String[] seqs) {
		this.seqs = seqs;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getDown_count() {
		return down_count;
	}

	public void setDown_count(int down_count) {
		this.down_count = down_count;
	}
}

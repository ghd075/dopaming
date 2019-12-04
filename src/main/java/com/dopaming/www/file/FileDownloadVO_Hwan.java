package com.dopaming.www.file;

import java.sql.Date;

// 다운로드 게시글 열람
public class FileDownloadVO_Hwan {
	
	//다운로드 넘버
	private int download_no;
	//다운로드 아콘
	private int download_acorn;
	//멤버 아이디
	private String member_id;
	//게시글 아콘
	private int board_acorn;
	//파일 번호
	private int file_no;
	//그룹 번호
	private int group_no;
	//각 파일 용량
	private double file_storage;
	//파일명
	private String file_name;
	//판매자 아이디
	private String seller;	
	
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getDownload_acorn() {
		return download_acorn;
	}
	public void setDownload_acorn(int download_acorn) {
		this.download_acorn = download_acorn;
	}
	public int getDownload_no() {
		return download_no;
	}
	public void setDownload_no(int download_no) {
		this.download_no = download_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getBoard_acorn() {
		return board_acorn;
	}
	public void setBoard_acorn(int board_acorn) {
		this.board_acorn = board_acorn;
	}
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public double getFile_storage() {
		return file_storage;
	}
	public void setFile_storage(double file_storage) {
		this.file_storage = file_storage;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}	
}

package com.dopaming.www.file;

public class FilePostVO_Hwan {
	private int board_no;
	private String board_title;
	private String category_big;
	private String category_small;
	private int board_acorn;
	private String member_id;
	private String board_content;
	private String board_img;	
	private double board_file_storage;
	private String fileName_List;
	private int down_count;
		
	//페이징하기위한 첫,마지막 번호
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
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getFileName_List() {
		return fileName_List;
	}
	public void setFileName_List(String fileName_List) {
		this.fileName_List = fileName_List;
	}
	public double getBoard_file_storage() {
		return board_file_storage;
	}
	public void setBoard_file_storage(double board_file_storage) {
		this.board_file_storage = board_file_storage;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getCategory_big() {
		return category_big;
	}
	public void setCategory_big(String category_big) {
		this.category_big = category_big;
	}
	public String getCategory_small() {
		return category_small;
	}
	public void setCategory_small(String category_small) {
		this.category_small = category_small;
	}
	public int getBoard_acorn() {
		return board_acorn;
	}
	public void setBoard_acorn(int board_acorn) {
		this.board_acorn = board_acorn;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_img() {
		return board_img;
	}
	public void setBoard_img(String board_img) {
		this.board_img = board_img;
	}
	public int getDown_count() {
		return down_count;
	}
	public void setDown_count(int down_count) {
		this.down_count = down_count;
	}
}

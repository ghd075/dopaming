package com.dopaming.www.file;

public class FileBoardVO_Hwan {
	
	//게시글 수정 번호
	private int board_no;

	//게시글제목
	private String board_title;
	//회원아이디
	private String member_id;
	//카테고리 대제목
	private String category_big;
	//카테고리 소제목
	private String category_small;
	//게시글 내용
	private String board_content;
	//게시글 이미지
	private String board_img;
	//게시글 아콘 => null값 허용
	private String board_acorn;
	
	
	
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
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
	public String getBoard_acorn() {
		return board_acorn;
	}
	public void setBoard_acorn(String board_acorn) {
		this.board_acorn = board_acorn;
	}
	//게시글번호
	private int boardNo;
	//게시글제목
	private String boardTitle;
	//회원아이디
	private String memberId;
	//카테고리 대제목
	private String categoryBig;
	//카테고리 소제목
	private String categorySmall;
	//게시글 내용
	private String boardContent;
	//게시글 이미지
	private String boardImg;
	//게시글 아콘 => null값 허용
	private String boardAcorn;	
	
	
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCategoryBig() {
		return categoryBig;
	}
	public void setCategoryBig(String categoryBig) {
		this.categoryBig = categoryBig;
	}
	public String getCategorySmall() {
		return categorySmall;
	}
	public void setCategorySmall(String categorySmall) {
		this.categorySmall = categorySmall;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardImg() {
		return boardImg;
	}
	public void setBoardImg(String boardImg) {
		this.boardImg = boardImg;
	}
	public String getBoardAcorn() {
		return boardAcorn;
	}
	public void setBoardAcorn(String boardAcorn) {
		this.boardAcorn = boardAcorn;
	}	
}

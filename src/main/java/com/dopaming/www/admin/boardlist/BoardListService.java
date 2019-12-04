package com.dopaming.www.admin.boardlist;

import java.util.List;

import com.dopaming.www.common.Paging;

public interface BoardListService {

	// 게시판 목록 출력
	List<BoardListVO> boardList_select(BoardListVO vo);
	
	// 공지 페이징 건수 출력
	int boardList_select_cnt(BoardListVO vo);
}
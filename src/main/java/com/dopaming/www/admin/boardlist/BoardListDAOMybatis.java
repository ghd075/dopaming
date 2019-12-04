package com.dopaming.www.admin.boardlist;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dopaming.www.common.Paging;

@Repository
public class BoardListDAOMybatis {

	@Autowired
	SqlSessionTemplate mybatis;
	
	//공지사항 목록 출력
	public List<BoardListVO> boardList_select(BoardListVO vo) {
		System.out.println("게시판 목록 실행");
		// 매핑실행과 동시에 결과값을 List<BoardListVO>에 받아온다.
		return mybatis.selectList("BoardListDAO.boardList_select", vo);
	}
	
	//페이징 건수 
	public int boardList_select_cnt(BoardListVO vo) {
		return mybatis.selectOne("BoardListDAO.boardList_select_cnt", vo);
	}
	


}

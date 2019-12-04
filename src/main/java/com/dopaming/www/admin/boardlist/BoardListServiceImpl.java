package com.dopaming.www.admin.boardlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dopaming.www.common.Paging;


@Service //서비스 등록
public class BoardListServiceImpl implements BoardListService {

	//인젝션//NoticeDAOMybatis(DAO)부르는것 
	@Autowired BoardListDAOMybatis dao;
	
	//공지 목록 조회
	@Override//상속받는것
	public List<BoardListVO> boardList_select(BoardListVO vo) {
		//mybatis를 실행하고 받은 결과값을 List<BoardListVO>
		return dao.boardList_select(vo);
	}
	
	//페이징 건수
	@Override 
	public int boardList_select_cnt(BoardListVO vo) {
		//mybatis를 실행하고 받은 결과값을 int boardList_select_cnt에 담는다.
		return dao.boardList_select_cnt(vo);
	}
	
	
}

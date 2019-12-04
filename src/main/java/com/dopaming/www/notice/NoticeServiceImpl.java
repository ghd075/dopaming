package com.dopaming.www.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dopaming.www.common.Paging;


@Service("NoticeService") //서비스 등록
public class NoticeServiceImpl implements NoticeService {

	//인젝션//NoticeDAOMybatis(DAO)부르는것 
	@Autowired NoticeDAOMybatis dao;
	
	//공지 입력
	@Override//상속받는것
	public void notice_insert(NoticeVO vo) {
		//around AOP 트랜잭션처리
		dao.notice_insert(vo);
		//commit
	}
	
	//공지 단건 조회
	@Override//상속받는것
	public NoticeVO notice_select(NoticeVO vo) {
		return dao.notice_select(vo);
	}
	
	//공지 목록 조회
	@Override//상속받는것
	public List<NoticeVO> notice_selectlist(NoticeVO vo) {
		return dao.notice_selectlist(vo);
	}
	
	//공지 단건 삭제
	@Override
	public void notice_delete(NoticeVO vo) {
		dao.notice_delete(vo);
	}
	
	//페이징 건수
	@Override 
	public int notice_selectlist_cnt() {
		return dao.notice_selectlist_cnt();
	}
	
	//공지 수정
	@Override
	public void notice_update(NoticeVO vo) {
		dao.notice_update(vo);
	}
	
}

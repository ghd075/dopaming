package com.dopaming.www.notice;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dopaming.www.common.Paging;

@Repository
public class NoticeDAOMybatis {

	@Autowired
	SqlSessionTemplate mybatis;
	
	//공지사항 입력 실행
	public void notice_insert(NoticeVO vo) {
		System.out.println("공지사항 입력 실행");
		mybatis.insert("NoticeDAO.notice_insert",vo);
	                                           //DTO(VO)에 순서대로 인서트 시킨다.
	}
	
	//공지사항 단건 출력
	public NoticeVO notice_select(NoticeVO vo) {
		System.out.println("공지사항 단건 출력");
		return (NoticeVO)mybatis.selectOne("NoticeDAO.notice_select",vo);
		                          //DTO(VO)에 순서대로 인서트 시킨다.
	}
	
	//공지사항 목록 출력
	public List<NoticeVO> notice_selectlist(NoticeVO vo) {
		System.out.println("공지사항 목록 실행");
		return mybatis.selectList("NoticeDAO.notice_selectlist",vo);
	}
	
	//페이징 건수 
	public int notice_selectlist_cnt() {
		return mybatis.selectOne("NoticeDAO.notice_selectlist_cnt");
	}
	
	//공지 단건 삭제
	public void notice_delete(NoticeVO vo) {
		System.out.println("공지사항 단건 삭제");
		mybatis.delete("NoticeDAO.notice_delete",vo);
		                          //DTO(VO)에 순서대로 인서트 시킨다.
	}
	
	//공지 수정
	public void notice_update(NoticeVO vo) {
		System.out.println("공지사항 수정");
		//매퍼를 동작시킨다.
		mybatis.update("NoticeDAO.notice_update",vo);
	}
}

package com.dopaming.www.notice;

import java.util.List;

import com.dopaming.www.common.Paging;

public interface NoticeService {

	// 공지 등록
	void notice_insert(NoticeVO vo);
	
	// 공지 단건 출력
	NoticeVO notice_select(NoticeVO vo);

	// 공지 목록 출력
	List<NoticeVO> notice_selectlist(NoticeVO vo);
	
	// 공지 단건 삭제
	void notice_delete(NoticeVO vo);
	
	// 공지 페이징 건수 출력
	int notice_selectlist_cnt();
	
	// 공지 수정
	void notice_update(NoticeVO vo);
}
package com.dopaming.www.admin.complain;

import java.util.List;

import com.dopaming.www.admin.boardlist.BoardListVO;
import com.dopaming.www.common.Paging;
import com.dopaming.www.notice.NoticeVO;

public interface ComplainService {

	// 공지 등록
	void complain_insert(ComplainVO vo);
	
	// 게시판 목록 출력
	List<ComplainVO> complain_selectlist(ComplainVO vo);
	
	// 공지 페이징 건수 출력
	int complain_selectlist_cnt(ComplainVO vo);
	
	// 답변 수정
	void complain_check_update(ComplainVO vo);
	
	// 고객센터 단건 출력
	ComplainVO complain_select(ComplainVO vo);
	
	// 답변 등록
	void answer_insert(AnswerVO avo, ComplainVO vo);

}
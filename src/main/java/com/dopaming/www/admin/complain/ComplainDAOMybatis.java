package com.dopaming.www.admin.complain;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dopaming.www.admin.boardlist.BoardListVO;
import com.dopaming.www.common.Paging;
import com.dopaming.www.notice.NoticeVO;

@Repository
public class ComplainDAOMybatis {

	@Autowired
	SqlSessionTemplate mybatis;
	
	//신고 입력
	public void complain_insert(ComplainVO vo) { //조건 값이 들어오면
		System.out.println("고객센터 입력 실행");
		//매퍼실행
		mybatis.insert("ComplainDAO.complain_insert",vo);
	}
	
	//고객센터 목록 출력
	public List<ComplainVO> complain_selectlist(ComplainVO vo) {
		System.out.println("고객센터 목록 실행");
		// 매핑실행과 동시에 결과값을 List<ComplainVO>에 받아온다.
		return mybatis.selectList("ComplainDAO.complain_selectlist", vo);
	}
	
	//페이징 건수 
	public int complain_selectlist_cnt(ComplainVO vo) {
		return mybatis.selectOne("ComplainDAO.complain_selectlist_cnt", vo);
	}
	
	//답변 수정
	public void complain_check_update(ComplainVO vo) {
		System.out.println("답변 수정");
		//매퍼를 동작시킨다.
		mybatis.update("ComplainDAO.complain_check_update",vo);
	}

	//고객센터 단건 출력
	public ComplainVO complain_select(ComplainVO vo) {
		System.out.println("고객센터 단건 출력");
		return (ComplainVO)mybatis.selectOne("ComplainDAO.complain_select",vo);
			//DTO(VO)에 순서대로 인서트 시킨다.
	}

	//고객센터 답변하기
	public void answer_insert(AnswerVO avo) { //조건 값이 들어오면
		System.out.println("고객센터 답변등록");
		//매퍼실행
		mybatis.insert("ComplainDAO.answer_insert", avo);
	}
	
}

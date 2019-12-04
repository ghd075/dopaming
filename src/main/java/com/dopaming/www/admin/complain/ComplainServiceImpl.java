package com.dopaming.www.admin.complain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dopaming.www.admin.boardlist.BoardListVO;
import com.dopaming.www.common.Paging;


@Service("ComplainService") //서비스 등록
public class ComplainServiceImpl implements ComplainService {

	//인젝션//NoticeDAOMybatis(DAO)부르는것 
	@Autowired ComplainDAOMybatis dao;
	
	//고객센터 입력
	@Override//상속받는것
	public void complain_insert(ComplainVO vo) {
		//around AOP 트랜잭션처리
		dao.complain_insert(vo); 
		//commit
	}
	
	//고객센터 리스트
	@Override
	public List<ComplainVO> complain_selectlist(ComplainVO vo) {
		//dao를 실행하고 받은 결과값을 List<ComplainVO>에 담는다.
		return dao.complain_selectlist(vo);
	}
	
	//페이징 건수
	@Override 
	public int complain_selectlist_cnt(ComplainVO vo) {
		return dao.complain_selectlist_cnt(vo);
	}
	
	//답변 수정
	@Override
	public void complain_check_update(ComplainVO vo) {
		//매퍼실행
		dao.complain_check_update(vo);
	}
	
	//고객센터 단건 출력
	@Override
	public ComplainVO complain_select(ComplainVO vo) {
		//dao를 실행하고 받은 결과값을 ComplainVO에 담는다.
		return dao.complain_select(vo);
	}
	
	//고객센터 답변 등록//고객센터 답변 상태 수정
	@Override
	public void answer_insert(AnswerVO avo,ComplainVO vo) {
		dao.answer_insert(avo); 
		vo.setComplain_check("Y");
		dao.complain_check_update(vo);
	}

}

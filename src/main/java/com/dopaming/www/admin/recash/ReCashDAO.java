package com.dopaming.www.admin.recash;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReCashDAO {

	@Autowired
	SqlSessionTemplate mybatis;
	
	//회원별 환급조회(관리자) 날자별 건 수
	public int recashCount_admin(ReCashVO vo) {
		return mybatis.selectOne("ReCashDAO.recashCount_admin", vo);
	}
	
	//환급신청 목록(관리자)_날짜별
	public List<ReCashVO> recashList_admin(ReCashVO vo) {
		return mybatis.selectList("ReCashDAO.recashList_admin", vo);
	}
	
	//환급금액 송금
	public void recashing() {
		mybatis.insert("ReCashDAO.recashing");
	}
}

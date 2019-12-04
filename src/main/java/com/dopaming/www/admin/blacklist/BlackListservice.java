package com.dopaming.www.admin.blacklist;

import java.util.List;

public interface BlackListservice {
	//블랙회원 출력 + 검색
	List<BlackListVO> getBlackList(BlackListVO vo);
	
	//블랙회원카운트
	public int blackListCount(BlackListVO vo);
	
	//블랙회원에서 삭제
	void blackListDelete(BlackListVO vo);
	
	//일반회원 출력
	List<BlackListVO> getNormalList(BlackListVO vo);
	
	//일반회원카운트
	public int normalListCount(BlackListVO vo);
	
	//일반회원 상세조회(단건조회)
	BlackListVO getNormal(BlackListVO vo);
	
	//일반->블랙 등록
	void normalInsert(BlackListVO vo);
}

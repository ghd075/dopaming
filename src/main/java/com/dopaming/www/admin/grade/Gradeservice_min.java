package com.dopaming.www.admin.grade;

import java.util.List;

public interface Gradeservice_min {

	//관리자 - 회원관리 - 등급관리 - 등급List
	List<GradeVO_min> getClassList(GradeVO_min vo);
	
	//관리자 - 회원관리 - 등급관리 - 등급카운터
	public int classListCount(GradeVO_min vo);
	
	//관리자 - 회원관리 - 등급관리 - 등급수정
	void gradeupdate(GradeVO_min vo);
}

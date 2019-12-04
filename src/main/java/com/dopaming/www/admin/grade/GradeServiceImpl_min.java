package com.dopaming.www.admin.grade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gradeService")
public class GradeServiceImpl_min implements Gradeservice_min {
	
	//Autowired가 UserDAO 자체를 가져오는것(DAO에 repository로 연결해줬다)
	//@Autowired private UserDAOSpring userDAO;
	@Autowired private GradeDAOMybatis_min mambersDAO;

	//관리자 - 회원관리 - 등급관리 - 등급List
	@Override
	public List<GradeVO_min> getClassList(GradeVO_min vo){
		System.out.println("getClassList");
		return mambersDAO.getClassList(vo);
	}

	@Override
	public int classListCount(GradeVO_min vo) {
		return mambersDAO.classListCount(vo);
	}

	@Override
	public void gradeupdate(GradeVO_min vo) {
		 mambersDAO.grade_update(vo);
	}
}

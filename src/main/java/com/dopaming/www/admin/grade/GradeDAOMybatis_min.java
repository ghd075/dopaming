package com.dopaming.www.admin.grade;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dopaming.www.admin.grade.GradeVO_min;

@Repository
public class GradeDAOMybatis_min {
	@Autowired SqlSessionTemplate mybatis;

	//관리자 - 회원관리 - 등급관리 - 등급List
	public List<GradeVO_min> getClassList(GradeVO_min vo){
		System.out.println("GradeMinDAO");
		return mybatis.selectList("GradeMinDAO.classList",vo);
	}
		
	//관리자 - 회원관리 - 등급관리 - 등급카운터
	public int classListCount(GradeVO_min vo) {
		return mybatis.selectOne("GradeMinDAO.classListCount",vo);
	}
	
	//관리자 - 회원관리 - 등급관리 - 등급수정
	public void grade_update(GradeVO_min vo) {
		mybatis.update("GradeMinDAO.gradeupdate",vo);
	}
}

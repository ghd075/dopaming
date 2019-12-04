package com.dopaming.www.admin.file;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dopaming.www.common.Paging;

@Repository
public class uploadListDAOMybatis_min {

	@Autowired
	SqlSessionTemplate mybatis;
	
	// 관리자 -  회원관리  - 업로드만 리스트 뷰
	public List<uploadListVO_min> getuploadList(uploadListVO_min vo) {
		return mybatis.selectList("uploadListMinDAO.getuploadList", vo);
	}
	
	// 관리자 -  회원관리  - 업로드만 리스트 뷰 건수 
	public int uploadListCount(uploadListVO_min vo) {
		return mybatis.selectOne("uploadListMinDAO.uploadListCount", vo);
	}
	


}

package com.dopaming.www.admin.login;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOMybatis_min {
	@Autowired SqlSessionTemplate mybatis;
	
	//로그인
	public MembersVO_min getMembers(MembersVO_min vo) {
		return mybatis.selectOne("LoginMinDAO.getMembers", vo);
	}
}

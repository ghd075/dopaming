package com.dopaming.www.main;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MainDAOMybatis_Hun {
	@Autowired
	SqlSessionTemplate mybatis;

	public List<Map<String, Object>> getMainList(MainVO_hun vo) {
		return mybatis.selectList("MainDAO.mainRank", vo);
	}
	public List<Map<String, Object>> getMainSlide(MainVO_hun vo) {
		return mybatis.selectList("MainDAO.mainSlide", vo);
	}

}

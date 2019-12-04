package com.dopaming.www.admin.chart;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dopaming.www.common.Paging;

@Repository
public class ChartDAOMybatis {

	@Autowired
	SqlSessionTemplate mybatis;
	
	// 관리자 -  회원관리  - 업로드만 리스트 뷰 건수 
	public int uploadListCount(ChartVO vo) {
		return mybatis.selectOne("chartDAO.ChartCount", vo);
	}
	
	public List<ChartVO> getChartList(ChartVO vo) {
		return mybatis.selectList("chartDAO.chartList",vo);
	}
	//페이징 건수
	public int chartList_cnt(ChartVO vo) {
		return mybatis.selectOne("chartDAO.chartList_cnt",vo);
	}
	
	public List<Map<String, Object>> getChartMember(ChartVO vo){
		return mybatis.selectList("chartDAO.chartMember",vo);
		
	}


}

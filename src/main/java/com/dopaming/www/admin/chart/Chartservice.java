package com.dopaming.www.admin.chart;

import java.util.List;
import java.util.Map;

public interface Chartservice {
	
	//CRUD 기능의 메소드 구현
	//차트 상세 조회
	ChartVO getChart(ChartVO vo);
	
	//차트 목록 조회
	List<ChartVO> getChartList(ChartVO vo);
	
	public int chartList_cnt(ChartVO vo);
	
	
	public List<Map<String, Object>> getChartMember(ChartVO vo);

}

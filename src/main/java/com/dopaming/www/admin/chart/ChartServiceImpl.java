package com.dopaming.www.admin.chart;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dopaming.www.file.FileVO;


@Service("chartService")
public class ChartServiceImpl implements Chartservice {
	
	@Autowired 
	private ChartDAOMybatis dao;
	
	@Override
	public ChartVO getChart(ChartVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChartVO> getChartList(ChartVO vo) {
		// TODO Auto-generated method stub
		System.out.println("getChartList");
		return dao.getChartList(vo);
	}

	@Override
	public int chartList_cnt(ChartVO vo) {
		// TODO Auto-generated method stub
		return dao.chartList_cnt(vo);
	}

	
	@Override
	public List<Map<String, Object>> getChartMember(ChartVO vo){
		return dao.getChartMember(vo);
	}
	
}

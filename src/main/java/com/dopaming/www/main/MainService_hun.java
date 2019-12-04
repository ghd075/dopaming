package com.dopaming.www.main;

import java.util.List;
import java.util.Map;

import com.dopaming.www.login.MemberVO;

public interface MainService_hun {
	
	
	//랭크 조회
	List<Map<String,Object>> getMainList(MainVO_hun vo);
	
	//slide
	List<Map<String,Object>> getMainSlide(MainVO_hun vo);
	
	//멤버 조회
	public Integer login(MemberVO vo);
	 
	
}

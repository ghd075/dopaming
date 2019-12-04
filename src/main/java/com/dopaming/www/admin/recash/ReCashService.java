package com.dopaming.www.admin.recash;

import java.util.List;

public interface ReCashService {
	
	//recashCount_admin
	public int recashCount_admin(ReCashVO vo);
	
	//recashList_admin
	public List<ReCashVO> recashList_admin(ReCashVO vo);
	
	//recashing
	public void recashing();
}

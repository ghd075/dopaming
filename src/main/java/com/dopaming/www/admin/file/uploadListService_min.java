package com.dopaming.www.admin.file;

import java.util.List;

import com.dopaming.www.common.Paging;

public interface uploadListService_min {

	// 관리자 -  회원관리  - 업로드만 리스트 뷰
	List<uploadListVO_min> getuploadList(uploadListVO_min vo);
	
	// 관리자 -  회원관리  - 업로드만 리스트 뷰 건수
	public int uploadListCount(uploadListVO_min vo);
}
package com.dopaming.www.admin.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dopaming.www.common.Paging;


@Service //서비스 등록
public class uploadListServiceImpl_min implements uploadListService_min {

	@Autowired uploadListDAOMybatis_min dao;
	
	@Override
	public List<uploadListVO_min> getuploadList(uploadListVO_min vo) {
		return dao.getuploadList(vo);
	}

	@Override
	public int uploadListCount(uploadListVO_min vo) {
		return dao.uploadListCount(vo);
	}
	
	
}

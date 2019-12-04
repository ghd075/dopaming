package com.dopaming.www.main;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dopaming.www.login.MemberDAO;
import com.dopaming.www.login.MemberVO;

@Service("MainService_hun")
public class MainServiceImpl_hun implements MainService_hun {

	@Autowired
	MainDAOMybatis_Hun dao;
	@Autowired
	private MemberDAO dao1;

	@Override
	public List<Map<String, Object>> getMainList(MainVO_hun vo) {
		return dao.getMainList(vo);
	}
	@Override
	public List<Map<String, Object>> getMainSlide(MainVO_hun vo) {
		return dao.getMainSlide(vo);
	}
	@Override
	public Integer login(MemberVO vo) {
		return dao1.login(vo).getAcorn_stock();
	}

	/*
	 * @Override public List<FileVO> getFileList_k() {
	 * System.out.println("getFileList_k"); return dao.getFileList_k(); }
	 * 
	 * @Override public List<FileVO> getFileList_f() {
	 * System.out.println("getFileList_f"); return dao.getFileList_f(); }
	 */

}

package com.dopaming.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dopaming.www.admin.recash.ReCashServiceImpl;
import com.dopaming.www.admin.recash.ReCashVO;
import com.dopaming.www.common.Paging;

@Controller
public class ReCashController {
	
	@Autowired
	ReCashServiceImpl service;
	
	@RequestMapping(value ="/admin/recashSelect", method = RequestMethod.GET )
	public String test() {
		return "admin/admin_ho/recashSelect";
	}
	
	@RequestMapping(value ="/admin/myReCash", method = RequestMethod.GET)
	public String recashList(ReCashVO vo, Paging paging, Model model, @RequestParam String reg_date) {
		
		vo.setReg_date(reg_date);
				
		paging.setPageUnit(10);
		
		if( paging.getPage() == 0) {
			paging.setPage(1); 
		}
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(service.recashCount_admin(vo));
		
		model.addAttribute("paging",paging);
		model.addAttribute("list",service.recashList_admin(vo));
		
		return "admin/admin_ho/recashList.empty";
	}
}

package com.dopaming.www.member;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.dopaming.www.admin.recash.ReCashVO;
import com.dopaming.www.common.Paging;


@Controller
@RequestMapping(value="/mypage")
public class MyBoardCotroller {
	
	@Autowired
	private MyBoardService service;
	
	//마이페이지(내가 받은 자료)
	@RequestMapping(value ="/myDown", method = RequestMethod.GET )
	public String getDownList(MyBoardVO vo, Paging paging, Model model, HttpSession session) {
		//세션에서 아이디정보값 가져오기
		String id = (String)session.getAttribute("Id");
		vo.setMember_id(id);
		
		//페이징 처리
		//페이지번호 파라미터
		if(paging.getPage() == 0) {
			paging.setPage(1);
		}
		
		//시작,마지막 레코드 번호
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		//전체건수
		paging.setTotalRecord(service.getDownCount(vo));
		
		model.addAttribute("paging",paging);
		model.addAttribute("list",service.getDownList(vo));
		return "mypage_hong/mydown";
	}
	
	//마이페이지(업로드관리)
	@RequestMapping(value ="/myUpload", method = RequestMethod.GET )
	public String getUploadList(FileUploadVO vo, Paging paging, Model model, HttpSession session) {
		//세션에서 아이디정보값 가져오기
		String id = (String)session.getAttribute("Id");
		vo.setMember_id(id);
		
		//페이징 처리
		//페이지번호 파라미터
		if(paging.getPage() == 0) {
			paging.setPage(1);
		}
		
		//시작, 마지막 레코드 번호
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		//전체건수
		paging.setTotalRecord(service.getUploadCount(vo));
		
		model.addAttribute("paging",paging);
		model.addAttribute("list",service.getUploadList(vo));
		return "mypage_hong/myupload";
	}
	
	//게시물 삭제
	@RequestMapping(value ="/upload_delete", method = RequestMethod.GET )
	public String deleteBoard(FileUploadVO vo, HttpServletRequest request) {
		String filePath = request.getSession().getServletContext().getRealPath("./resources/upload");
		vo.setPath(filePath);
		service.deleteBoard(vo);
		return "redirect:/mypage/myUpload";
	}
	
	//마이페이지(캐시 사용 내역)
	@RequestMapping(value ="/myAcorn", method = RequestMethod.GET )
	public String getAcornList(MyAcornVO vo, Paging paging, Model model, HttpSession session) {
		//세션에서 아이디정보값 가져오기
		String id = (String)session.getAttribute("Id");
		vo.setMember_id(id);
		
		//페이징 처리
		//페이지번호 파라미터
		if(paging.getPage() == 0) {
			paging.setPage(1);
		}
		
		//시작, 마지막 레코드 번호
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		//전체건수
		paging.setTotalRecord(service.getAcornCount(vo));
		
		model.addAttribute("paging",paging);
		model.addAttribute("acorn",service.getAcorn(vo));
		model.addAttribute("list",service.getAcornList(vo));
		
		return "mypage_hong/myacorn";
	}
	
	//내 캐시 내역 삭제
	@RequestMapping(value ="/acorn_delete", method = RequestMethod.GET )
	public String deleteAcorn(MyAcornVO vo, HttpServletRequest request) {
		service.deleteAcorn(vo);
		return "redirect:/mypage/myAcorn";
	}
	
	//환급조회
	@RequestMapping(value ="/myReCash", method = RequestMethod.GET )
	public String recashList(ReCashVO vo, Paging paging, Model model, HttpSession session) {
		//세션에서 아이디정보값 가져오기
		String id = (String)session.getAttribute("Id");
		vo.setMember_id(id);
		
		//페이징 처리
		//페이지번호 파라미터
		if(paging.getPage() == 0) {
			paging.setPage(1);
		}
		
		//시작,마지막 레코드 번호
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		//전체건수
		paging.setTotalRecord(service.recashCount(vo));
		model.addAttribute("paging",paging);
		model.addAttribute("list",service.recashList(vo));
		return "mypage_hong/myrecash";
	}
	
	@RequestMapping(value ="/myReCashIns", method = RequestMethod.GET )
	public String insertPop(HttpSession session) {
		return "mypage_hong/empty/myrecashIns";
	}
	
	//환급신청
	@RequestMapping(value="/myReCashIns.do", method=RequestMethod.POST)
	public void recashIns(ReCashVO vo, HttpSession session, HttpServletResponse response) throws IOException {	
		//세션에서 아이디정보값 가져오기
		String id = (String)session.getAttribute("Id");
		vo.setMember_id(id);
		
		//환급신청 번호 부여
		String no = String.valueOf(service.recashNo(vo));
		vo.setReg_no(no);
		
		service.recashIns(vo, response);		
	}	
}

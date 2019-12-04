package com.dopaming.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dopaming.www.admin.boardlist.BoardListService;
import com.dopaming.www.admin.boardlist.BoardListVO;
import com.dopaming.www.admin.complain.AnswerVO;
import com.dopaming.www.admin.complain.ComplainService;
import com.dopaming.www.admin.complain.ComplainVO;
import com.dopaming.www.admin.login.MembersVO_min;
import com.dopaming.www.common.Paging;
import com.dopaming.www.login.MemberVO;
import com.dopaming.www.notice.NoticeService;
import com.dopaming.www.notice.NoticeVO;

@Controller
public class JoonController {

	private static final Logger logger = LoggerFactory.getLogger(JoonController.class);

	@Autowired NoticeService service;
	@Autowired BoardListService BoardList_service;
	@Autowired ComplainService ComplainService;
	
	//공지 등록폼(관리자)
	@RequestMapping(value = "/admin/notice_insert_form")
	public String notice_insert_form() { 
		return "admin/admin_joon/notice_insert_joon"; 
			}
	
	// 공지 등록 입력값 받아와서 넘겨주기(관리자)
	@RequestMapping(value = "/admin/notice_insert") // 뷰에서 notice_insert의 값이 보내어지면
	public String notice_insert(NoticeVO vo, Model model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws IOException {
		
		//vo를 불러온다
		service.notice_insert(vo);
		
		if (vo == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('id error');");
			out.println("history.go(-1);"); // 이전페이지로
			out.println("</script>");
			return "admin/admin_joon/notice_insert_joon";
		} else {
		
			// 돌아갈 화면
			return "redirect:/admin/notice_selectlist";
		}
	}

	// 공지 목록 (관리자)
	@RequestMapping("/admin/notice_selectlist")
	public String notice_selectlist(Model model, Paging paging, NoticeVO vo) {

		// 페이징 처리
		paging.setPageUnit(5); // 개당 출력건수
		// 시작페이지 설정
		if (paging.getPage() == 0) {
			paging.setPage(1);
		}
		// 돌려주는 값(전체레코드)이 페이징vo에 셋팅이된다.
		paging.setTotalRecord(service.notice_selectlist_cnt());
		// db에서 받은 정보로 페이지마다 시작/마지막 레코드 번호
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		// 페이징 VO의 데이터를 paging으로 담아둔다.
		model.addAttribute("paging", paging);
		// 돌려 받은 값들을 list에 받아둔다.
		model.addAttribute("list", service.notice_selectlist(vo));
		
		return "admin/admin_joon/notice_selectlist_joon";
	}
	
	// 공지 목록 (일반)
		@RequestMapping("/notice_selectlist_nomal")
		public String notice_selectlist_nomal(Model model, Paging paging, NoticeVO vo) {

			// 페이징 처리
			paging.setPageUnit(10); // 개당 출력건수
			// 시작페이지 설정
			if (paging.getPage() == 0) {
				paging.setPage(1);
			}
			// 돌려주는 값(전체레코드)이 페이징vo에 셋팅이된다.
			paging.setTotalRecord(service.notice_selectlist_cnt());
			// db에서 받은 정보로 페이지마다 시작/마지막 레코드 번호
			vo.setFirst(paging.getFirst());
			vo.setLast(paging.getLast());
			// 페이징 VO의 데이터를 paging으로 담아둔다.
			model.addAttribute("paging", paging);
			// 돌려 받은 값들을 list에 받아둔다.
			model.addAttribute("list", service.notice_selectlist(vo));
			
			//돌려주는 타일즈
			return "jon/notice_selectlist_joon_nomal";
		}

	// 공지 사항 뷰 (최신)
	@RequestMapping("/notice_select_new")
	public String notice_selectlist_nomal(Model model, NoticeVO vo) {
		
		//페이징값을 무조건 줘야하기 때문에 한건만 출력하면 되기에 1에서 1까지 지정한다.
		vo.setFirst(1);
		vo.setLast(1);
		// 돌려 받은 값들을 list에 받아둔다.
		model.addAttribute("list", service.notice_selectlist(vo));
					
		//돌려주는 타일즈
		return "jon/notice_select_new";
	}		
		
	// 공지사항 뷰(관리자)
	@RequestMapping(value = "/admin/notice_select") // 뷰에서 notice_select의 값이 보내어지면
	public String notice_select(NoticeVO vo, Model model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws IOException {
		
		//서비스 실행
		service.notice_select(vo);

		// 돌려 받은 값들을 notice에 받아둔다.
		model.addAttribute("notice", service.notice_select(vo));
		// 돌아가는 페이지
		return "admin/admin_joon/notice_select_joon";
	}

	// 공지사항 뷰(일반)
		@RequestMapping(value = "/notice_select_nomal") // 뷰에서 notice_select의 값이 보내어지면
		public String notice_select_namal(NoticeVO vo, Model model, HttpServletRequest request, HttpSession session,
				HttpServletResponse response) throws IOException {
			
			//서비스 실행
			service.notice_select(vo);

			// 돌려 받은 값들을 notice에 받아둔다.
			model.addAttribute("notice", service.notice_select(vo));
			// 돌아가는 페이지
			return "jon/notice_select_joon";
		}
	
	// 공지 단건 삭제(관리자)
	@RequestMapping("admin/notice_delete")
	public String notice_delete(NoticeVO vo) {
		service.notice_delete(vo);
		return "redirect:/admin/notice_selectlist";
	}

	// 공지 선택 삭제(관리자)
	@RequestMapping("admin/notice_deletelist")
	public String notice_deletelist(NoticeVO vo, HttpServletRequest request) throws ServletException, IOException {
		// jsp에서 배열값 받는 함수
		String[] td_checkbox = request.getParameterValues("td_checkbox");
		// 받은 배열을 푼다
		for (String n : td_checkbox) {
			try {
				// string를 int로 바꾼뒤 dao로 하나씩 넘긴다.
				vo.setNotice_no(Integer.parseInt(n));
				service.notice_delete(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/admin/notice_selectlist";
	}

	//공지 수정폼(관리자)
	@RequestMapping(value = "/admin/notice_update_form") 
	public String notice_update(NoticeVO vo, Model model) { 
		
		//JSP에서 넘어온값(VO)를 다시 수정폼화면에 뿌리기위해서 모델에 notice로 담아둔다.
		model.addAttribute("notice", vo);
		//돌아갈 페이지
		return "admin/admin_joon/notice_update_joon"; 
		}
	
	//공지사항 수정하기
		@RequestMapping(value = "admin/notice_update", method = RequestMethod.POST) // 뷰에서 notice_update의 값이 보내어지면
		public String notice_update(NoticeVO vo, Model model,   
				HttpServletResponse response) throws IOException {
			
			System.out.println(vo);

			//service의 notice_update를 실행시킨다.
			service.notice_update(vo);
			if (vo == null) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('id error');");
				out.println("history.go(-1);"); // 이전페이지로
				out.println("</script>");
				return "admin/admin_joon/notice_update_joon";
			} else {
				// 돌아갈 화면
				return "redirect:/admin/notice_selectlist";
			}
		}
		
	//게시판 목록 연결(관리자)
		@RequestMapping("/admin/boardList")
		public String boardList(Model model, Paging paging, BoardListVO vo, HttpServletRequest request) {
			
			// 페이징 처리
			paging.setPageUnit(5); // 개당 출력건수
			// 시작페이지 설정
			if (paging.getPage() == 0) {
				paging.setPage(1);
			}
			
			// 게시판 검색값을 넘겨주고, 돌려받는 값(전체레코드)이 페이징vo에 셋팅이된다.
			paging.setTotalRecord(BoardList_service.boardList_select_cnt(vo));
		
			// db에서 받은 정보로 페이지마다 시작/마지막 레코드 번호
			vo.setFirst(paging.getFirst());
			vo.setLast(paging.getLast());
			// 페이징 VO의 데이터를 paging으로 담아둔다.
			model.addAttribute("paging", paging);
			// 돌려 받은 값들을 list에 받아둔다.
			model.addAttribute("list", BoardList_service.boardList_select(vo));
			//타일즈 동작
			return "admin/admin_joon/boardList_joon";
		}
		
	//고객센터 등록폼(게시판의 넘버가 넘어오면 vo에 자동으로 담긴다.)
	@RequestMapping(value = "/complain_insert_form")
	public String complain_insert_form(ComplainVO vo) { 
		return "jon/complain_insert_form_joon"; 
	}
	
	//고객센터 등록
	@RequestMapping(value = "/complain_insert")
	public String complain_insert(MemberVO mvo, ComplainVO vo, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
		
		//세션에서 받은 아이디 값을 vo에 담는다.
		mvo = (MemberVO) session.getAttribute("memberSession");
		vo.setMember_id(mvo.getMember_id());
		
		//서비스를 실행시킨다.
		ComplainService.complain_insert(vo);
		
		//redirect의 경우에는 값이 지워지기 때문에 다시 complain_type값을 보내고 돌아갈뷰 지정, 한글값이 깨져서 직접 인코딩해서 보낸다. 
		return "redirect:complain_selectlist_nomal?complain_type="+ URLEncoder.encode(vo.getComplain_type(),"utf-8");
	}
	
	//고객센터 리스트(관리자)
	@RequestMapping(value = "/admin/complain_selectlist")
	public String complain_selectlist(ComplainVO vo, Model model, Paging paging, HttpServletRequest request) { 
		
		// 페이징 처리
		paging.setPageUnit(10); // 개당 출력건수
		// 시작페이지 설정
		if (paging.getPage() == 0) {
			paging.setPage(1);
		}
					 
		// 처음+끝 페이지 값을 넣어서 작업을 실행시킨뒤 돌아온값(전체레코드)을 페이징vo에 셋팅한다.
		paging.setTotalRecord(ComplainService.complain_selectlist_cnt(vo));
		
		// db에서 받은 정보로 페이지마다 시작/마지막 레코드 번호
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		// 페이징 VO의 데이터를 paging으로 담아둔다.
		model.addAttribute("paging", paging);
		
		//작업 실행과 함게 돌려 받은 값들을 list에 받아둔다.
		model.addAttribute("list", ComplainService.complain_selectlist(vo));
		return "admin/admin_joon/complain_selectlist_joon"; 
	}
	
	//고객센터 리스트(일반)
		@RequestMapping(value = "/complain_selectlist_nomal")
		public String complain_selectlist_namal(HttpSession session, MemberVO mvo, ComplainVO vo, Model model, Paging paging, HttpServletRequest request) { 
			
			//세션에서 받은 아이디 값을 vo에 담는다.
			mvo = (MemberVO) session.getAttribute("memberSession");
			vo.setMember_id(mvo.getMember_id());
			
			// 페이징 처리
			paging.setPageUnit(10); // 개당 출력건수
			// 시작페이지 설정
			if (paging.getPage() == 0) {
				paging.setPage(1);
			}
						 
			// 처음+끝 페이지 값을 넣어서 작업을 실행시킨뒤 돌아온값(전체레코드)을 페이징vo에 셋팅한다.
			paging.setTotalRecord(ComplainService.complain_selectlist_cnt(vo));
			
			// db에서 받은 정보로 페이지마다 시작/마지막 레코드 번호
			vo.setFirst(paging.getFirst());
			vo.setLast(paging.getLast());
			// 페이징 VO의 데이터를 paging으로 담아둔다.
			model.addAttribute("paging", paging);
			
			//작업 실행과 함게 돌려 받은 값들을 list에 받아둔다.
			model.addAttribute("list", ComplainService.complain_selectlist(vo));
			return "jon/complain_selectlist_joon_nomal"; 
		}
	
	//고객센터 답변수정(관리자)
	@RequestMapping(value = "/admin/complain_check_update")
	public String complain_check_update(ComplainVO vo, HttpServletRequest request) throws UnsupportedEncodingException {
		
		//페이지 고정값
		String page = request.getParameter("page");
		
		//서비스를 실행시킨다.
		ComplainService.complain_check_update(vo);	
		
		//redirect의 경우에는 값이 지워지기 때문에 다시 complain_type값을 보내고 돌아갈뷰 지정, 한글값이 깨져서 직접 인코딩해서 보낸다. 
		return "redirect:/admin/complain_selectlist?page="+page+"&complain_type="+ URLEncoder.encode(vo.getComplain_type(),"utf-8");
	}
	
	// 고객센터 뷰(관리자)
	@RequestMapping(value = "admin/complain_select") // 뷰에서 complain_select의 값이 보내어지면
	public String complain_select(ComplainVO vo, Model model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws IOException {
		// 돌려 받은 값들을 ComplainVO에 받아둔다.
		model.addAttribute("ComplainVO", ComplainService.complain_select(vo));
		// 돌아가는 페이지
		return "admin/admin_joon/complain_select_joon";
	}
	
	// 고객센터 뷰(일반)
		@RequestMapping(value = "/complain_select_nomal") // 뷰에서 notice_select의 값이 보내어지면
		public String complain_select_nomal(ComplainVO vo, Model model, HttpServletRequest request, HttpSession session,
				HttpServletResponse response) throws IOException {
			// 돌려 받은 값들을 ComplainVO에 받아둔다.
			model.addAttribute("ComplainVO", ComplainService.complain_select(vo));
			// 돌아가는 페이지
			return "jon/complain_select_joon";
		}

	//고객센터 답변 등록 폼(관리자)(게시판의 넘버가 넘어오면 vo에 자동으로 담긴다.)
	@RequestMapping(value = "/admin/complain_answer_form")
	public String complain_answer_form(ComplainVO vo, Model model) { 
		
		// 돌려 받은 값들을 ComplainVO에 받아둔다.
		model.addAttribute("ComplainVO", ComplainService.complain_select(vo));
		
		return "admin/admin_joon/complain_answer_form_joon"; 
	}
	
	//고객센터 답변 등록(관리자)
	@RequestMapping(value = "/admin/answer_insert")
	public String answer_insert(AnswerVO avo, ComplainVO vo) throws UnsupportedEncodingException {
			
	//값들을 지정vo에 넣어준다
	avo.setReceiver_id(vo.getMember_id());
			
	//서비스를 실행시킨다(답변값avo 을 넘기고, 수정을 바로 시켜주기 위해서 수정값이 담긴 vo도 넘겨준다)
	ComplainService.answer_insert(avo,vo);
			
	//redirect의 경우에는 값이 지워지기 때문에 다시 complain_type값을 보내고 돌아갈뷰 지정, 한글값이 깨져서 직접 인코딩해서 보낸다. 
	return "redirect:/admin/complain_selectlist?complain_type="+ URLEncoder.encode(vo.getComplain_type(),"utf-8");
	}

}

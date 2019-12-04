package com.dopaming.www;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.dopaming.www.admin.blacklist.BlackListVO;
import com.dopaming.www.admin.blacklist.BlackListservice;
import com.dopaming.www.admin.boardlist.BoardListService;
import com.dopaming.www.admin.file.uploadListService_min;
import com.dopaming.www.admin.file.uploadListVO_min;
import com.dopaming.www.admin.grade.GradeVO_min;
import com.dopaming.www.admin.grade.Gradeservice_min;
import com.dopaming.www.admin.login.Loginservice_min;
import com.dopaming.www.admin.login.MembersVO_min;
import com.dopaming.www.common.Paging;
import com.dopaming.www.notice.NoticeVO;
import com.dopaming.www.pay.PayVO_min;
import com.dopaming.www.pay.Payservice_min;
import com.dopaming.www.pay.payinterface;


@Controller
public class MinController {

	private static final Logger logger = LoggerFactory.getLogger(MinController.class);

	@Autowired
	Loginservice_min service;
	@Autowired
	Gradeservice_min service2;
	@Autowired
	BlackListservice service3;
	@Autowired
	uploadListService_min service4;
	@Autowired
	Payservice_min service5;
	@Autowired
	payinterface payreturn;
	

	// 관리자 - 로그인 폼
	@RequestMapping(value = { "/loginForm" }, method = RequestMethod.GET)
	public String loginFrom() {
		return "admin/admin_min/adminlogin_min.empty";
	}

	// 관리자 - 로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(@ModelAttribute("members") MembersVO_min vo, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();		
		MembersVO_min member = null;
		try {
			//관리자(admin)을 위한 암호화, 암호화는 서비스에서 처리했음
			member = service.getMembers(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//DB에 없는 값이거나(DB에 없는값도 Null, 빈공백도 Null) DB에 admin,admin2,admin3아닌 값(=즉 admin,admin2,admin3제외한 모든것)
		if(member==null || !member.getMember_id().equals("admin") &&!member.getMember_id().equals("admin2")&&!member.getMember_id().equals("admin3")) {
			out.println("<script>");
			out.println("alert('관리자만 접근가능합니다.');");
			out.println("history.go(-1);"); // 이전페이지로
			out.println("</script>");
		} else {
			//admin,admin2,admin3만 접속가능
			session.setAttribute("member_id", member.getMember_id());
			session.setAttribute("member_password", member.getMember_password());
			session.setAttribute("member", member);
			out.println("<script>");
			out.println("location='admin/classForm';"); // 이전페이지로
			out.println("</script>");
		}
	}

	// 관리자 - 로그아웃 처리
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션무효화
		return "redirect:loginForm";
	}

	// 관리자 - 회원관리 - 등급관리 - 등급List
	@RequestMapping(value = { "/admin/classForm" }, method = RequestMethod.GET)
	public String getClass(Model model, GradeVO_min vo, Paging paging) {
		paging.setPageUnit(10);
		// 페이지번호 파라미터
		if (paging.getPage() == 0) {
			paging.setPage(1);
		}

		// 시작/마지막 레코드 번호
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		// 전체 건수
		paging.setTotalRecord(service2.classListCount(vo));

		// System.out.println("requestMapping"); //해당 메소드 이름 호출(getClassList가 적혀나옴)
		model.addAttribute("paging", paging);
		model.addAttribute("classList", service2.getClassList(vo));
		return "admin/admin_min/adminclass_min";
	}

	// 관리자 - 회원관리 - 등급관리 - 등급수정
	@RequestMapping("/admin/grade_update")
	public String grade_update(GradeVO_min vo, HttpServletRequest request) {
		String[] td_checkbox = request.getParameterValues("td_checkbox");
		String member_grade = request.getParameter("member_grade2");

		for (String n : td_checkbox) {
			try {
				vo.setMember_id(n);
				vo.setMember_grade(member_grade);
				service2.gradeupdate(vo);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:./classForm";
	}

	// 관리자 - 회원관리 - 블랙회원list + 검색 + 페이징
	@RequestMapping(value = { "/admin/blackListForm" }, method = RequestMethod.GET)
	public String getBlackList(Model model, BlackListVO vo, Paging paging) {

		paging.setPageUnit(10);
		// 페이지번호 파라미터
		if (paging.getPage() == 0) {
			paging.setPage(1);
		}

		// 시작/마지막 레코드 번호
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		// 전체 건수
		paging.setTotalRecord(service3.blackListCount(vo));
		service3.getBlackList(vo);


		model.addAttribute("blackList", service3.getBlackList(vo));
		model.addAttribute("paging", paging);
		return "admin/admin_min/adminblacklist_min";
	}

	// 관리자 - 회원관리 - 블랙회원에서 삭제
	@RequestMapping("/admin/blackList_delete")
	public String blackListDelete(BlackListVO vo, HttpServletRequest request) throws ServletException, IOException {
		// jsp에서 배열값 받는 함수
		String[] td_checkbox = request.getParameterValues("td_checkbox");
		// 받은 배열을 푼다
		for (String n : td_checkbox) {
			try {
				vo.setMember_id(n);
				service3.blackListDelete(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/admin/blackListForm";
	}
	
	// 관리자 - 회원관리 - 일반회원list + 검색 + 페이징
		@RequestMapping(value = { "/admin/NormalListForm" }, method = RequestMethod.GET)
		public String getNormalList(Model model, BlackListVO vo, Paging paging) {

			paging.setPageUnit(10);
			// 페이지번호 파라미터
			if (paging.getPage() == 0) {
				paging.setPage(1);
			}

			// 시작/마지막 레코드 번호
			vo.setFirst(paging.getFirst());
			vo.setLast(paging.getLast());

			// 전체 건수
			paging.setTotalRecord(service3.normalListCount(vo));
			service3.getBlackList(vo);


			model.addAttribute("normalList", service3.getNormalList(vo));
			model.addAttribute("paging", paging);
			return "admin/admin_min/adminnormallist_min";
		}
	
	// 관리자 - 회원관리 - 일반회원에서 블랙리스트로 바꾸기위한 단건조회
		@RequestMapping("/admin/blackInsert/{member_id}")
		public String normalInsertForm(Model model,BlackListVO vo,@PathVariable String member_id) {
			vo.setMember_id(member_id);
			//단건조회
			model.addAttribute("normal",service3.getNormal(vo));
			return "admin/admin_min/adminnormalInsert";
		}
		
	// 관리자 - 회원관리 - 일반회원에서 블랙리스트로 바꾸기위한 처리구간
		@RequestMapping(value="/admin/blackInsert", method=RequestMethod.POST)
		public String normalInsert(@ModelAttribute("blacklist")BlackListVO vo,
								  SessionStatus st) {
			System.out.println("=================" + vo);
			service3.normalInsert(vo);
			st.setComplete(); //세션값 전부 clear
			return "redirect:./blackListForm";
		}
		
	// 관리자 - 회원관리 - 업로드한 리스트 뷰
		@RequestMapping(value = { "/admin/uploadlistForm" }, method = RequestMethod.GET)
		public String getuploadList(Model model, uploadListVO_min vo, Paging paging, HttpServletRequest request) {
			
			paging.setPageUnit(10);
			// 페이지번호 파라미터
			if (paging.getPage() == 0) {
				paging.setPage(1);
			}
			
			// 시작/마지막 레코드 번호
			vo.setFirst(paging.getFirst());
			vo.setLast(paging.getLast());
			
			// 전체 건수
			paging.setTotalRecord(service4.uploadListCount(vo));
			
			model.addAttribute("paging", paging);
			model.addAttribute("uploadList", service4.getuploadList(vo));
			return "admin/admin_min/adminuploadlist_min";
		}	
		
	
	// 이용자 - 아콘결제처리
	@RequestMapping(value="/acornForm", method = RequestMethod.POST)
	public String acornFrom(HttpSession session, PayVO_min vo) {
		//login패키지에 있는 접속한 id세션 받아서 저장
		String Id = (String)session.getAttribute("Id");
		vo.setMember_id(Id);//id값이 있어야 결제에서 이용자확인가능 or insert의 1번째로 받는 member_id  
		
		//결제후 해당 아이디값이 있는지 확인
		System.out.println("==============="+Id);
		
		//매개변수2개받아서 insert에 2번째~3번째값에 적용
		service5.insertPay(vo);
		return "redirect:/"; 
	}
	
	// 이용자 - 결제페이지
	@RequestMapping(value = { "/acornForm" }, method = RequestMethod.GET)
	public String acorn() {
		return "min/useracorn_min";
	}

	// 결제한사람 (아이디만)리스트(중복제거)
	@RequestMapping(value = { "/admin/acornlist" }, method = RequestMethod.GET)
	public String acornlist(Model model, Paging paging,PayVO_min vo) {
		paging.setPageUnit(5);
		// 페이지번호 파라미터
		if (paging.getPage() == 0) {
			paging.setPage(1);
		}

		// 시작/마지막 레코드 번호
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		// 전체 건수
		paging.setTotalRecord(service5.returnPayCount(vo));
		
		model.addAttribute("paging", paging);		
		model.addAttribute("acornlist",service5.returnPay(vo));
		return "admin/admin_min/acornlist_min";
	}

	
	// 결제했는 사람(단건)에 대한 세부내용(아이디,결제금액,충전날짜,결제고유코드)
	@RequestMapping(value = { "/admin/acorndetaillist" }, method = RequestMethod.GET)
	public String acorndetaillist(Model md,PayVO_min vo) {
		md.addAttribute("acorndetail",service5.detailPay(vo));
		return "admin/admin_min/acorndetail_min";
	}
	
	// 실질적으로 결제가 환불처리되는 과정
	@RequestMapping(value = { "/admin/acornreturn" }, method = RequestMethod.GET)
	public String acornreturn(Model md,PayVO_min vo) {
		//결제코드 누를시(a태그) 아임포트 서버에서 환불처리가 완료됨
		payreturn.CancelPaymentAlreadyCancelledImpUid(vo);
		//아임포트에선 환불처리되었지만 DB는 남아있으므로 환불처리한 데이터 역시 DB에서 삭제
		service5.deletePay(vo);
		return "redirect:/admin/acornlist";
	}
}

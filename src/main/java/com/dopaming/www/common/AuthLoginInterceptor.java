package com.dopaming.www.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dopaming.www.login.MemberDAO;
import com.dopaming.www.login.MemberVO;

//로그인처리를 담당하는 인터셉터
public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private MemberDAO dao;
	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request
							, HttpServletResponse response
							, Object handler) throws Exception {
		// session 객체를 가져옴
		HttpSession session = request.getSession();
		
		// login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져옴

		if (request.getSession().getAttribute("memberSession") == null) {
			session.setAttribute("error", "로그인 정보가 없습니다.");
			response.sendRedirect(request.getContextPath() + "/");
			return false; // 더이상 컨트롤러 요청으로 가지 않도록 false로 반환함
		}else {
			MemberVO memberVO =(MemberVO)request.getSession().getAttribute("memberSession");
			// preHandle의 return은 컨트롤러 요청 uri로 가도 되냐 안되냐를 허가하는 의미임
			// 따라서 true로하면 컨트롤러 uri로 가게 됨.
			memberVO = dao.login(memberVO);
			request.getSession().setAttribute("memberSession",memberVO);
			return true;
		}		
	}

	// 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}

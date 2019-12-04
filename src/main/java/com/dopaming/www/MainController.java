package com.dopaming.www;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dopaming.www.common.ImgExtract;
import com.dopaming.www.login.MemberService;
import com.dopaming.www.login.MemberVO;
import com.dopaming.www.main.MainService_hun;
import com.dopaming.www.main.MainVO_hun;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	MainService_hun service;
	
	
	
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String home(Locale locale, Model model, MainVO_hun vo) {
			logger.info("Welcome home! The client locale is {}.", locale);
			
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			
			String formattedDate = dateFormat.format(date);
			
			vo.setCategoryBig("영화");
			List<Map<String, Object>> slide1 = service.getMainSlide(vo);
			for(int i =0 ; i<slide1.size(); i++) {
				String text = (String)slide1.get(i).get("BOARD_IMG");
				String result = ImgExtract.getfirstimage(text);
				slide1.get(i).put("BOARD_IMG",result);
			}
			
			vo.setCategoryBig("드라마");
			List<Map<String, Object>> slide2 = service.getMainSlide(vo);
			for(int i =0 ; i<slide2.size(); i++) {
				String text = (String)slide2.get(i).get("BOARD_IMG");
				String result = ImgExtract.getfirstimage(text);
				slide2.get(i).put("BOARD_IMG",result);
			}
			
			vo.setCategoryBig("동영상");
			List<Map<String, Object>> slide3 = service.getMainSlide(vo);
			for(int i =0 ; i<slide3.size(); i++) {
				String text = (String)slide3.get(i).get("BOARD_IMG");
				String result = ImgExtract.getfirstimage(text);
				slide3.get(i).put("BOARD_IMG",result);
			}
			
			vo.setCategoryBig("음악");
			List<Map<String, Object>> slide4 = service.getMainSlide(vo);
			for(int i =0 ; i<slide4.size(); i++) {
				String text = (String)slide4.get(i).get("BOARD_IMG");
				String result = ImgExtract.getfirstimage(text);
				slide4.get(i).put("BOARD_IMG",result);
			}
			
			model.addAttribute("serverTime", formattedDate );
			model.addAttribute("list",service.getMainList(vo));
			model.addAttribute("slide1",slide1);
			model.addAttribute("slide2",slide2);
			model.addAttribute("slide3",slide3);
			model.addAttribute("slide4",slide4);
			
			return "main/main";
		}
		
		
		@RequestMapping(value = "/acornview", method = RequestMethod.GET)
		@ResponseBody
		public Integer head(HttpSession session, MemberVO vo) {
			
			
			String Id = (String)session.getAttribute("Id");
			vo.setMember_id(Id);//id값이 있어야 결제에서 이용자확인가능 or insert의 1번째로 받는 member_id
			
			return service.login(vo);
			
		}

}

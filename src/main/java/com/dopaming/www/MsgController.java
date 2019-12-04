package com.dopaming.www;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dopaming.www.common.Paging;
import com.dopaming.www.login.MemberVO;
import com.dopaming.www.msg.MsgService;
import com.dopaming.www.msg.MsgVO;

@Controller
public class MsgController {
	
	@Autowired
	MsgService service;

//Receive
	
	//받은 쪽지 전체 목록
	@RequestMapping(value="/msg_receive", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> receiveAll(Paging paging, HttpSession session, MemberVO memberVO, MsgVO vo) {
		
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setReceiver_id(memberVO.getMember_id());	
		
		paging.setPageUnit(5);
		
		if( paging.getPage() == 0) {
			paging.setPage(1); 
		}
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(service.receive_cnt(vo));
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("paging", paging );
		map.put("list", service.all_receive_list(vo));
		
		return map;
	}
	
	//받는쪽지 보낸이 그룹
	@RequestMapping(value="/msg_sender_r", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> receiveSender(Paging paging, HttpSession session, MemberVO memberVO, MsgVO vo) {
		
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setReceiver_id(memberVO.getMember_id());
		String id = service.sender_receive(vo);
		vo.setSender_id(id);
		
		paging.setPageUnit(5);
		
		if( paging.getPage() == 0) {
			paging.setPage(1); 
		}
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
				
		paging.setTotalRecord(service.sender_receive_cnt(vo));
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("paging", paging );
		map.put("list", service.sender_receive_list(vo));
				
		return map;
	}
		
	//읽지않은 쪽지 목록
	@RequestMapping(value="/msg_unselect", method=RequestMethod.GET)
	@ResponseBody
		public Map<String, Object> unSelect(Paging paging, HttpSession session, MemberVO memberVO, MsgVO vo) {
		
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setReceiver_id(memberVO.getMember_id());	
		
		paging.setPageUnit(5);
		
		if( paging.getPage() == 0) {
			paging.setPage(1); 
		}
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(service.unselect_receive_cnt(vo));
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("paging", paging );
		map.put("list", service.unselect_receive_list(vo));
		
		return map;
	}
		
	//읽지않은 쪽지 수
	@RequestMapping(value="/cnt", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO receiveCnt(Model model, HttpSession session, MemberVO memberVO, MsgVO vo) {
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setReceiver_id(memberVO.getMember_id());
		vo.setCnt(service.unselect_receive_cnt(vo));
		return vo;
	}
	
	//받은쪽지함에서 보관함으로 이동 전 상태 체크
	@RequestMapping(value="/msg_checking", method=RequestMethod.GET)
	@ResponseBody
	public List<MsgVO> checking(Model model, HttpSession session, MsgVO vo) {
		return service.check_keeping(vo);
	}
		
	//받은쪽지함에서 보관함으로 이동 
	@RequestMapping(value="/msg_keeping", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO receive_keeping(MsgVO vo) {
		service.receive_keeping(vo);
		return vo;
	}
	
	//받은 쪽지 확인상태 변경
	@RequestMapping(value="/msg_changing", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO changing(MsgVO vo) {
		service.changing(vo);
		return vo;
	}

	//받은 쪽지 내용 확인하기
	@RequestMapping(value="/msg_select", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO receiveSelect(MsgVO vo) {
		return service.select_receive(vo);
	}
	
	//받은 쪽지함에서 휴지통으로 이동
	@RequestMapping(value="/msg_traching_r", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO receiveTrashing(MsgVO vo) {
		service.receive_trashing(vo);
		return vo;
	}
	
	
//Sent
	
	//보낸 쪽지 전체 목록
	@RequestMapping(value="/msg_sent", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> sentAll(Paging paging, HttpSession session, MemberVO memberVO, MsgVO vo) {
		
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setSender_id(memberVO.getMember_id());
		
		paging.setPageUnit(5);
		
		if( paging.getPage() == 0) {
			paging.setPage(1); 
		}
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(service.sent_cnt(vo));
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("paging", paging );
		map.put("list", service.all_sent_list(vo));
				
		return map;
	}
	
	//보낸쪽지 받은이 그룹
	@RequestMapping(value="/msg_receiver", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> receiverSent(Paging paging, HttpSession session, MemberVO memberVO, MsgVO vo) {
		
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setSender_id(memberVO.getMember_id());
		String id = service.receiver_sent(vo);
		vo.setReceiver_id(id);
		
		paging.setPageUnit(5);
		
		if( paging.getPage() == 0) {
			paging.setPage(1); 
		}
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(service.receiver_sent_cnt(vo));
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("paging", paging );
		map.put("list", service.receiver_sent_list(vo));
				
		return map;
	}
	
	//받은 쪽지 내용 확인하기
	@RequestMapping(value="/msg_select_s", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO sentSelect(MsgVO vo) {
		return service.select_sent(vo);
	}
	
	//보낸 쪽지함에서 휴지통으로 이동
	@RequestMapping(value="msg_traching_s", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO sentTrashing(MsgVO vo) {
		service.sent_trashing(vo);
		return vo;
	}
	

	
//Write
	
	//쪽지 보내기
	@RequestMapping(value="/msg_write", method=RequestMethod.POST, headers = {"Content-type=application/json"})
	@ResponseBody
	public MsgVO sending(Model model, HttpSession session, MemberVO memberVO, @RequestBody MsgVO vo) {
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setSender_id(memberVO.getMember_id());
		service.sending_write(vo);
		return vo;
	}
	
	
	
//Keep
	
	//쪽지 보관함 전체 목록
	@RequestMapping(value="/msg_keep", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> keepAll(Paging paging, HttpSession session, MemberVO memberVO, MsgVO vo) {
		
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setReceiver_id(memberVO.getMember_id());
		
		paging.setPageUnit(5);

		if( paging.getPage() == 0) {
			paging.setPage(1); 
		}
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(service.keep_cnt(vo));
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("paging", paging );
		map.put("list", service.all_keep_list(vo));
			
		return map;
	}
	
	//보관함 보낸이 그룹
	@RequestMapping(value="/msg_sender_k", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> keepSender(Paging paging, HttpSession session, MemberVO memberVO, MsgVO vo) {
		
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setReceiver_id(memberVO.getMember_id());
		String id = service.sender_receive(vo);
		vo.setSender_id(id);
		
		paging.setPageUnit(5);
		
		if( paging.getPage() == 0) {
			paging.setPage(1); 
		}
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
				
		paging.setTotalRecord(service.sender_keep_cnt(vo));
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("paging", paging );
		map.put("list", service.sender_keep_list(vo));
				
		return map;
	}
	
	//보관함 이전으로 복원
	@RequestMapping(value="/msg_returning_k", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO keepReturning(MsgVO vo) {
		service.keep_returning(vo);
		return vo;
	}
	
	//보관함에서 휴지통으로 이동
	@RequestMapping(value="msg_traching_k", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO keepTrashing(MsgVO vo) {
		service.keep_trashing(vo);
		return vo;
	}
	
	

//Trash
	
	//휴지통 전체 목록
	//쪽지 보관함 전체 목록
	@RequestMapping(value="/msg_trash", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> trashAll(Paging paging, HttpSession session, MemberVO memberVO, MsgVO vo) {
		
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setReceiver_id(memberVO.getMember_id());
		
		paging.setPageUnit(5);

		if( paging.getPage() == 0) {
			paging.setPage(1); 
		}
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(service.trash_cnt(vo));
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("paging", paging );
		map.put("list", service.all_trash_list(vo));
			
		return map;
	}
	
	//휴지통 보낸이 그룹
	@RequestMapping(value="/msg_sender_t", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> trashSender(Paging paging, HttpSession session, MemberVO memberVO, MsgVO vo) {
		
		memberVO = (MemberVO) session.getAttribute("memberSession");
		vo.setReceiver_id(memberVO.getMember_id());
		String id = service.sender_receive(vo);
		vo.setSender_id(id);
		
		paging.setPageUnit(5);
		
		if( paging.getPage() == 0) {
			paging.setPage(1); 
		}
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
				
		paging.setTotalRecord(service.sender_trash_cnt(vo));
		
		Map<String, Object> map = new HashMap<String, Object>();		
		map.put("paging", paging );
		map.put("list", service.sender_trash_list(vo));
				
		return map;
	}
	
	//보관함 이전으로 복원
	@RequestMapping(value="/msg_returning_t", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO trashReturning(MsgVO vo) {
		service.trash_returning(vo);
		return vo;
	}
	
	//휴지통비우기
	@RequestMapping(value="/msg_delete", method=RequestMethod.GET)
	@ResponseBody
	public MsgVO delete(MsgVO vo) {
		service.delete(vo);
		return vo;
	}
}

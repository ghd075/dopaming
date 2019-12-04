package com.dopaming.www.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	/**
	 * 회원로그인
	 * @param memberVO
	 * @return 로그인절차에 따른 상태메세지
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping(value = "/loginA", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> normalLogin(@RequestBody MemberVO memberVO) throws Exception {
	        String stateCode = service.normalLogin(memberVO);
	        System.out.println("회원로그인상태메세지 : " + stateCode);
	        if (stateCode.toUpperCase().equals("ID_STATE_WAITAPPROVAL"))
	            return new ResponseEntity<>(stateCode, HttpStatus.OK);
	        return stateCode.equals("LOGIN_STATE_SUCCESS") ? new ResponseEntity<>(stateCode, HttpStatus.OK) : new ResponseEntity<>(stateCode, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * 회원로그아웃
	 * @param id
	 * @param currentUrl
	 * @return 현재 있었던 회원로그아웃 경로 반환
	 */
	@GetMapping(value = "/logoutA/{member_id}")
	public String logout(@PathVariable("member_id") String member_id, @RequestParam(required = false) String currentUrl, HttpServletRequest request) {			
        currentUrl = currentUrl.substring(request.getContextPath().length());
		System.out.println(currentUrl);
        
        MemberVO member = new MemberVO();
        member.setMember_id(member_id);
		String stateCode = service.logout(member);
        System.out.println("로그아웃상태 : " + stateCode);
        return "redirect:/"; //+currentUrl; 
	}
	
	/**
	 * 회원초대장전송
	 * @param id
	 * @return 초대장발송여부
	 */
    @RequestMapping(value = "/mail/invite/{member_id}", produces = {MediaType.TEXT_PLAIN_VALUE}, method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> inviteMember(@PathVariable("member_id") String member_id, HttpServletRequest request) {
        boolean inviteState = false;
        String context = request.getContextPath();
        try {
            inviteState = service.inviteUser(member_id, context);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("INVITE_STATE_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println("초대장발송여부 : " + inviteState);
        return inviteState == true ? new ResponseEntity<>("INVITE_STATE_SUCCESS", HttpStatus.OK) : new ResponseEntity<>("INVITE_STATE_ERROR", HttpStatus.OK);
    }
	
    /**
     * 회원인증
     * @param id
     * @param code
     * @return 회원인증여부
     */
	@SuppressWarnings("finally")
	@GetMapping(value = "/mail/invite/{member_id}/{member_code}")
    public String emailAuthentication(@PathVariable("member_id") String member_id, @PathVariable("member_code") String member_code) {
        String authState = "";
        try {
            authState = service.emailAuthentication(member_id, member_code);
        } catch (Exception e) {
        	System.out.println(e);
        } finally {
        	System.out.println("인증여부 : " + authState);
            return "redirect:/";
        }
    }
    
	/**
	 * 
	 * 회원가입
	 * @param MemberVO
	 * @return 회원가입절차에 따른 상태메시지
	 * @throws Exception
	 */
	//회원가입 처리(비밀번호 암호화는 Impl에서 했음)
    @RequestMapping(value = "/register", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE}, method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> register(@RequestBody MemberVO memberVO) throws Exception {
        System.out.println(memberVO);
        String stateCode = service.register(memberVO);
        System.out.println("회원가입상태메세지 : " + stateCode);
        return stateCode.equals("MEMBER_STATE_SUCCESS") ? new ResponseEntity<>(stateCode, HttpStatus.OK) : new ResponseEntity<>(stateCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
    /**
     *  회원비밀번호변경인증
     *  @Param 아이디, 인증코드
     *  @Return 비밀번호메일인증여부
     */
    @GetMapping(value = "/mail/changepass/{member_id}/{member_code}")
    public String recoveryPassword(@PathVariable("member_id") String member_id, @PathVariable("member_code") String member_code) {
        String authState = "";
        authState = service.recoveryPassword(member_id, member_code);
        System.out.println("패스워드변경여부 : " + authState);
        return "redirect:/";
    }
    
    /**
     *  미승인 회원정보삭제
     *  @Param MemberVO
     *  @Return 회원삭제절차에따른상태메세지
     */
    @ResponseBody
    @RequestMapping(value = "/deleteinfo", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE}, method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> deleteInformation(@RequestBody MemberVO memberVO) throws Exception {
    	System.out.println("아이디 : " + memberVO.getMember_id());
    	String stateCode = service.deleteInformation(memberVO);
        System.out.println("미승인회원정보삭제 : " + stateCode);
        return stateCode.equals("INFODEL_STATE_SUCCESS") ? new ResponseEntity<>(stateCode, HttpStatus.OK) : new ResponseEntity<>(stateCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     *  회원 비밀번호 변경 메일전송
     *  @Param 이메일
     *  @Return 메일발송여부
     */

    @RequestMapping(value = "/mail/changepass/{member_email:.+}", method = RequestMethod.GET)
    public ResponseEntity<String> sendMailPasswordChanger(@PathVariable("member_email") String member_email, HttpServletRequest request) throws Exception {
    	HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/plain; charset=UTF-8");
	    String context = request.getContextPath();
    	boolean sendState = false;
        sendState = service.sendMailPasswordChanger(member_email, context);
        System.out.println("패스워드메일발송여부 " + sendState);
        return sendState == true ? new ResponseEntity<>("PASSMAIL_STATE_SUCCESS", responseHeaders, HttpStatus.OK) : new ResponseEntity<>("PASSMAIL_STATE_ERROR",responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *  이메일 인증을 통한 비밀번호변경
     *  @Param MemberVO
     *  @Return 비밀번호변경여부상태코드
     */
    @PutMapping(value = "/mail/changepass/mailpassword", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> changePasswordEmailAuth(@RequestBody MemberVO memberVO) {
        String changePasswordState = service.changePasswordEmailAuth(memberVO);
        System.out.println("인증패스워드변경상태 : " + changePasswordState);
        return changePasswordState.equals("PASS_CHANGE_SUCCESS") ?
                new ResponseEntity<>(changePasswordState, HttpStatus.OK) : new ResponseEntity<>(changePasswordState, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

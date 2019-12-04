package com.dopaming.www.login;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO dao;
	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;	//스프링 암호화
	
	@Autowired
	HttpSession httpSession;	//세션 객체
	
	/**
	 *  회원로그인
	 *  @Param MemberVO
	 *  @Return 회원로그인 따른 상태메세지
	 */
	@Override
	public String normalLogin(MemberVO vo) {
		String stateCode = "LOGIN_STATE_ERROR";
		// 회원정보데이터 조회
        MemberVO memberData = dao.login(vo);
        if (memberData != null) {
            //차단된 아이디인지 검사
        	if(memberData.getBlack_id() != null)
             	return "LOGIN_BLACK_ERROR";
        	// 입력된 비밀번호와 회원정보데이터와 매칭
            if (passwordEncoder.matches(vo.getMember_password(), memberData.getMember_password())) {
                // 회원상태정보가 승인검토중일경우 ID_STATE_WAITAPPROVAL 반환
                if (memberData.getMember_type().toUpperCase().equals("NONE")) return "ID_STATE_WAITAPPROVAL";
                stateCode = "LOGIN_STATE_SUCCESS";

                // 세션등록
                httpSession.setAttribute("memberSession", memberData);
                httpSession.setAttribute("auth", memberData.getMember_auth());
                httpSession.setAttribute("type", memberData.getMember_type());
                httpSession.setAttribute("Id", memberData.getMember_id());
                httpSession.setAttribute("grand", memberData.getMember_grade());
                httpSession.setAttribute("email", memberData.getMember_email());
                httpSession.setAttribute("storage", memberData.getUpload_storage());
                httpSession.setAttribute("uploadCount", memberData.getUpload_count());
                httpSession.setAttribute("Acorn_stock", memberData.getAcorn_stock());
                httpSession.setAttribute("stateCode", stateCode);
                httpSession.setAttribute("alarm", "on");
            }
        }
        return stateCode;
	}
	
	/**
	 * 회원이메일 전송
	 * @Param 아이디
	 * @Retrun 이메일발송여부
	 */
	@Override
	public boolean inviteUser(String id, String context) throws Exception {
		
		//초대 여부 반환값
		boolean inviteState = false;
		
		MemberVO member = new MemberVO();
		member.setMember_id(id);
		
		int registStatus = dao.registStatus(member);
		System.out.println("가입상태확인 : " + registStatus);
		
		// 회원등록되어있을경우 ( 0:ERROR , 1:진행 )
        if (registStatus == 1) {
            String checkStatus = dao.getType(member);
            System.out.println("회원타입확인 : " + checkStatus);
            
            // 회원가입 대기상태일경우 ( NONE )
            if (checkStatus.toUpperCase().equals("NONE")) {
                send_mail(member, "register", context);
                inviteState = true;
            }
        }
		
		return inviteState;
	}
	
	/**
	 * 메일전송
	 * @Param MemberVO
	 */
	@Override
	public void send_mail(MemberVO vo, String type, String context) throws Exception {
		// Mail Server 설정
		String hostSMTP = "smtp.gmail.com";
		final String username = "rlfls54@gmail.com"; 
		final String password = "bqocuxhcktjkwlyp"; 

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", hostSMTP);
		props.put("mail.smtp.port", "587");
		
		// Get the Session object.
		Session session = Session.getInstance(props, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}			
		});
		
		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "rlfls54@gmail.com";
		String subject = "";
		StringBuffer msg = new StringBuffer();
		String siteUrl = "dopaming.com";
		
		//이메일 & 코드 조회
		vo.setMember_email(dao.getEmail(vo));
		vo.setMember_code(dao.getCode(vo));
	    System.out.println("회원이메일확인 : " + vo.getMember_email());
	    System.out.println("회원코드확인 : " + vo.getMember_code());
		
		switch(type) {
		case "register":
			// 회원가입 메일 내용
			subject = "[Dopaming] 회원가입 인증 메일 - " + vo.getMember_id() + "님에게";
			msg.append("<div style='width:100%; text-align:center; margin-top:50px;'>");
			msg.append("<a href='http://" + siteUrl + context + "/mail/invite");
			msg.append("/" + vo.getMember_id());
			msg.append("/" + vo.getMember_code());
			msg.append("'>");
			msg.append("<img src='https://i.imgur.com/NwQn0fF.png' style='width:60%;max-width=400px'></a>");
			msg.append("</div>");
			System.out.println(msg.toString());
			break;
			
		case "password":
			//비밀번호 재설정 메일 내용
			subject = "[Dopaming] 비밀번호 재 설정 - " + vo.getMember_id() + "님에게";
			msg.append("<div style='width:100%; text-align:center; margin-top:50px;'>");
			msg.append("<a href='http://" + siteUrl + context + "/mail/changepass");
			msg.append("/" + vo.getMember_id());
			msg.append("/" + vo.getMember_code());
			msg.append("'>");
			msg.append("<img src='https://i.imgur.com/qbV2JBU.png' style='width:60%;max-width=400px'></a>");
			msg.append("</div>");
	}
		
		// 받는 사람 E-Mail 주소
		String mail = vo.getMember_email();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			message.setSubject(subject);
			message.setText(msg.toString(),"utf-8", "html");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
			throw new RuntimeException(e);
		}	
	}

	/**
	 * 회원가입
	 * @return 회원가입절차에 따른 상태메시지
	 */
	@Override
	public String register(MemberVO member) throws Exception {
		String stateCode = "";
		String[] variableNames = {"member_id", "member_password", "member_email"};
		
		// 입력 된 데이터 NULL 및 공백확인 [ 모든 데이터가 있을경우 VALUES_STATE_GOOD ]
        stateCode = MemberValidation.findEmptyValue(member, variableNames);
		if(stateCode.equals("VALUES_STATE_GOOD")) {
		    // 아이디 검증 [ 영어 또는 숫자 포함 / 문자 길이 12까지 ]
            if (!MemberValidation.checkEngAndNum(member.getMember_id()) || !MemberValidation.textLengthComparison(12, member.getMember_id())) {
                stateCode = "ID_STATE_ERROR";
                // 비밀번호 검증 [ 영어, 숫자 포함 / 문자 길이 8-16까지 ]
            } else if (!MemberValidation.checkPassword(member.getMember_password())) {
                stateCode = "PASS_STATE_ERROR";
                // 비밀번호 일치하는지 검사
            } else if(!member.isPwEqualToCheckPw()) {
            	stateCode = "PASS_STATE_SAME";
                // 이메일 검증
            } else if (!MemberValidation.checkEmail(member.getMember_email())) {
                stateCode = "EMAIL_STATE_ERROR";
            // 회원가입 진행
            } else {
            	//아이디가 이미 등록되어져 있는 경우
            	if(dao.check_id(member) == 1) {
            		stateCode = "ID_STATE_USED";
            		//아이디가 이메일 승인 대기중인 경우
            		 if (stateCode.equals("ID_STATE_USED")) {
                         stateCode = dao.check_email(member).toUpperCase().equals("NONE") ? "ID_STATE_WAITAPPROVAL" : "ID_STATE_USED";
                     }
            	}else {
            		if(dao.emailReduplicationCheck(member) == 1) {
            			stateCode = "EMAIL_STATE_USED";
        			}
            		else {
            			// 회원정보가 등록이 된 경우 => 패스워드 암호화 및 인증코드 발급
                		member.setMember_password(passwordEncoder.encode(member.getMember_password()));
                		member.setMember_code(RamdomPassword.getNewCode());
                		stateCode = dao.register(member) == 1 ? "MEMBER_STATE_SUCCESS" : "SYSTEM_STATE_ERROR";	
            		}
            	}
            }
		}else {
            // 데이터가 NULL 및 공백이 있을경우 상태코드로 변환
            stateCode = stateCode.toUpperCase().replace("MEMBER", "").concat("_STATE_EMPTY");
        }
		return stateCode;
	
	}

    /**
     *  회원인증확인
     *  @Param 아이디, 코드
     *  @Return 인증확인여부
     */
	@Override
	public String emailAuthentication(String id, String code) {
        MemberVO member = new MemberVO();
        member.setMember_id(id);
        member.setMember_code(code);
        // 회원인증확인
        int informationCheckStatus = dao.informationCheckStatus(member);
        if (informationCheckStatus == 1) {
            // 회원 활성화 및 코드갱신
            member.setMember_code(RamdomPassword.getNewCode());
            try {
                dao.chageStatud(member);
                httpSession.setAttribute("stateCode", "AUTH_STATE_SUCCESS");
            } catch (Exception e) {
                e.printStackTrace();
                httpSession.setAttribute("stateCode", "AUTH_STATE_ERROR");
                return "AUTH_STATE_ERROR";
            }
        } else {
            httpSession.setAttribute("stateCode", "AUTH_STATE_ERROR");
        }
        return informationCheckStatus == 1 ? "AUTH_STATE_SUCCESS" : "AUTH_STATE_ERROR";				
	}
    /**
     *  회원로그아웃
     *  @Param memberVO
     *  @Return 회원로그아웃 상태코드 반환
     */	
	@Override
	public String logout(MemberVO memberVO)
	{
        if (httpSession.getAttribute("Id") != null && !httpSession.getAttribute("Id").equals(memberVO.getMember_id()) ) return "LOGOUT_STATE_ERROR";
        httpSession.invalidate(); //모든 세션 제게
        httpSession.setAttribute("stateCode", "LOGOUT_STATE_SUCCESS");
        return "LOGOUT_STATE_SUCCESS";
	}
	
    /**
     *  미승인회원정보삭제
     *  @Param MemberVO
     *  @Return 회원로그인 따른 상태메세지
     */
	@Override
    public String deleteInformation(MemberVO memberVO) {
		int deleteState = 0;
		if(dao.deleteMemberAcon(memberVO) == 1)
			deleteState = dao.deleteInformation(memberVO);
        return deleteState == 1 ? "INFODEL_STATE_SUCCESS" : "INFODEL_STATE_ERROR";
    }
	
    /**
     *  패스워드변경메일인증확인
     *  @Param 아이디, 코드
     *  @Return 인증확인여부
     */
    @Override
    public String recoveryPassword(String member_id, String member_code) {
        MemberVO memberVO = new MemberVO();
        memberVO.setMember_id(member_id);
        memberVO.setMember_code(member_code);
        // 회원인증확인
        int passwordMailState = dao.informationCheckStatus(memberVO);
        if (passwordMailState == 1) {
            // 코드갱신
            memberVO.setMember_code(RamdomPassword.getNewCode());
            dao.renewCode(memberVO);
            httpSession.setAttribute("stateCode", "PASSAUTH_STATE_SUCCESS");
            httpSession.setAttribute("authId", memberVO.getMember_id());
        } else {
            httpSession.setAttribute("stateCode", "PASSAUTH_STATE_ERROR");
        }
        return passwordMailState == 1 ? "PASSAUTH_STATE_SUCCESS" : "PASSAUTH_STATE_ERROR";
    }


	 /**
     *  이메일인증을통한비밀번호변경
     *  @Param MemberVO
     *  @Return 비밀번호변경여부에 따른 상태메세지
     */
	@Override
	public String changePasswordEmailAuth(MemberVO memberVO) {
        String changePasswordState = "PASS_CHANGE_ERROR";
        // 비밀번호 유효성 검사 실패시 
        if (!MemberValidation.checkPassword(memberVO.getMember_password())) return changePasswordState;
        String encodePassword = passwordEncoder.encode(memberVO.getMember_password());
        memberVO.setMember_password(encodePassword);
        int changeState = dao.changePasswordEmailAuth(memberVO);
        if (changeState == 1) {
            changePasswordState = "PASS_CHANGE_SUCCESS";
            httpSession.removeAttribute("authId");
            // 인증코드 갱신
            memberVO.setMember_code(RamdomPassword.getNewCode());
            dao.renewCode(memberVO);
        }
        return changePasswordState;
	}
	
    /**
     *  회원비밀번호변경메일발송
     *  @Param 이메일
     *  @Return 메일발송여부
     */
    @Override
    public boolean sendMailPasswordChanger(String member_email, String context) throws Exception {
        boolean sendState = false;
        MemberVO memberVO = new MemberVO();
        memberVO.setMember_email(member_email);
        String checkMailById = dao.checkMailState(memberVO);
        if (checkMailById != null) {
            sendState = true;
            memberVO.setMember_id(checkMailById);
            send_mail(memberVO, "password", context);
        }
        return sendState;
    }
}

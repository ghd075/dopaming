package com.dopaming.www.login;

public interface MemberService {
	//로그인
	public String normalLogin(MemberVO vo);
	
	//로그아웃
	public String logout(MemberVO memberVO); 
	
	//초대장 전송
	public boolean inviteUser(String id, String context) throws Exception;
	
	//회원가입
	public String register(MemberVO member) throws Exception;
	
	//이메일 발송
	public void send_mail(MemberVO vo, String type, String context) throws Exception;
	
	//회원 인증
	public String emailAuthentication(String id, String code);

	//미인증 회원 삭제
	public String deleteInformation(MemberVO memberVO);
	
	//이메일 인증을 통한 비밀번호변경
	public String changePasswordEmailAuth(MemberVO memberVO);
	
	//회원 비밀번호 변경 메일 발송
	public boolean sendMailPasswordChanger(String mbrEmail, String context) throws Exception;
	
	//패스워드변경메일인증확인
	public String recoveryPassword(String member_id, String member_code);
}

package com.dopaming.www.login;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MemberDAO {
	
	//SqlSessionTemplate객체가 모든 JDBC처리를 대신 해줌
	@Autowired
	SqlSessionTemplate mybatis;
	
	//로그인(회원정보 조회)
	public MemberVO login(MemberVO vo) {
		System.out.println("===> Mybatis로  login() 기능 처리");
		return (MemberVO) mybatis.selectOne("MemberDAO.login", vo);
	}
	
	//이메일 조회
	public String getEmail(MemberVO member) {
		System.out.println("===> Mybatis로  valueCheckPW() 기능 처리");
		return mybatis.selectOne("MemberDAO.getEmail", member);
	}
	
	//아이디 중복 검사
	public int check_id(MemberVO member) {
		System.out.println("===> Mybatis로  check_id() 기능 처리");
		return mybatis.selectOne("MemberDAO.check_id", member);
	}
	
	//이메일 중복 검사
	public String check_email(MemberVO member) {
		System.out.println("===> Mybatis로  check_email() 기능 처리");
		return mybatis.selectOne("MemberDAO.check_email", member);
	}
	
	//회원가입
	@Transactional
	public int register(MemberVO vo) {
		System.out.println("===> Mybatis로  register() 기능 처리");
		return mybatis.insert("MemberDAO.register",vo);
	}
	
	//이메일 인증을 통한 패스워드 변경
	public int changePasswordEmailAuth(MemberVO memberVO) {
		System.out.println("===> Mybatis로 	changePasswordEmailAuth() 기능 처리");
		return mybatis.update("MemberDAO.changePasswordEmailAuth", memberVO);
	}
	
	//비밀번호 변경
	@Transactional
	public int changepass(MemberVO vo) {
		System.out.println("===> Mybatis로  changepass() 기능 처리");
		return mybatis.update("MemberDAO.changepass", vo);
	}
	
	//회원 인증코드 조회
	public String getCode(MemberVO vo) {
		System.out.println("===> Mybatis로  getCode() 기능 처리");
		return mybatis.selectOne("MemberDAO.getCode", vo);
	}
	
	//회원 타입 값 조회
	public String getType(MemberVO vo) {
		System.out.println("===> Mybatis로  getType() 기능 처리");
		return mybatis.selectOne("MemberDAO.getType", vo);
	}
	
	//회원가입여부 확인
	public int registStatus(MemberVO vo) {
		System.out.println("===> Mybatis로  registStatus() 기능 처리");
		return mybatis.selectOne("MemberDAO.registStatus", vo);
	}
	
	//회원코드 갱신
	@Transactional
	public void renewCode(MemberVO vo) {
		System.out.println("===> Mybatis로  renewCode() 기능 처리");
		mybatis.update("MemberDAO.renewCode", vo);
	}
	
	//회원인증 확인
	public int informationCheckStatus(MemberVO vo) {
		System.out.println("===> Mybatis로  informationCheckStatus() 기능 처리");
		return mybatis.selectOne("MemberDAO.informationCheckStatus", vo);
	}
	
	//회원활성화 및 코드 갱신
	public void chageStatud(MemberVO vo ) {
		System.out.println("===> Mybatis로  chageStatud() 기능 처리");
		mybatis.update("MemberDAO.chageStatud", vo);
	}
	
	//미승인 회원 아콘정보 삭제
	public int deleteMemberAcon(MemberVO vo) {
		System.out.println("===> Mybatis로  deleteMemberAcon() 기능 처리");
		return mybatis.delete("MemberDAO.deleteMemberAcon", vo);
	}
	
	
	//미승인 회원 삭제
	public int deleteInformation(MemberVO vo) {
		System.out.println("===> Mybatis로  deleteInformation() 기능 처리");
		return mybatis.delete("MemberDAO.deleteInformation", vo);
	}
	
	//회원 이메일 정보 확인
	public String checkMailState(MemberVO vo) {
		System.out.println("===> Mybatis로  checkMailState() 기능 처리");
		return mybatis.selectOne("MemberDAO.checkMailState", vo);
	}
	
	//회원 이메일 중복 체크
	public int emailReduplicationCheck(MemberVO vo) {
		System.out.println("===> Mybatis로  emailReduplicationCheck() 기능 처리");
		return mybatis.selectOne("MemberDAO.emailReduplicationCheck", vo);
	}
}


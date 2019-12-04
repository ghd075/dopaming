package com.dopaming.www.admin.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dopaming.www.encryption.EgovFileScrty;

@Service("userService")
public class LoginServiceImpl_min implements Loginservice_min {
	
	@Autowired private LoginDAOMybatis_min mambersDAO;
	
	//로그인
	public MembersVO_min getMembers(MembersVO_min vo) throws Exception {
		
		// 1. 입력한 비밀번호를 암호화한다.
		String enpassword = EgovFileScrty.encryptPassword(vo.getMember_password(), vo.getMember_id());
		vo.setMember_password(enpassword);

		// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
		MembersVO_min loginVO = mambersDAO.getMembers(vo);

		// 3. 결과를 리턴한다.
		if (loginVO != null && !loginVO.getMember_id().equals("") && !loginVO.getMember_password().equals("")) {
			return loginVO;
		} else {
			loginVO = new MembersVO_min();
		}
		return mambersDAO.getMembers(vo);
	}
}

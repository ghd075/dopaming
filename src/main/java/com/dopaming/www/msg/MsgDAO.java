package com.dopaming.www.msg;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MsgDAO {
	
	@Autowired
	SqlSessionTemplate mybatis;

//Receive
	
	//받은 쪽지 전체 목록
	public List<MsgVO> all_receive_list(MsgVO vo) {
		return mybatis.selectList("MsgDAO.all_receive_list",vo);
	}
	
	//받은 쪽지 전체 수
	public int receive_cnt(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.receive_cnt", vo);
	}
	
	//받은쪽지 보낸이 id
	public String sender_receive(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.sender_receive",vo);
	}
	
	//받은쪽지 보낸이 쪽지 수
	public int sender_receive_cnt(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.sender_receive_cnt", vo);
	}
	
	//받은쪽지 보낸이 그룹
	public List<MsgVO> sender_receive_list(MsgVO vo) {
		return mybatis.selectList("MsgDAO.sender_receive_list",vo);
	}
			
	//읽지않은 쪽지 목록
	public List<MsgVO> unselect_receive_list(MsgVO vo) {
		return mybatis.selectList("MsgDAO.unselect_receive_list",vo);
	}
	
	//읽지않은 쪽지 수
	public int unselect_receive_cnt(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.unselect_receive_cnt",vo);
	}
	
	//받은쪽지함에서 보관함으로 이동 전 상태 체크
	public List<MsgVO> check_keeping(MsgVO vo) {
		return mybatis.selectList("MsgDAO.check_keeping",vo);
	}
	
	//받은쪽지함에서 보관함으로 이동
	public void receive_keeping(MsgVO vo) {
		mybatis.insert("MsgDAO.receive_keeping",vo);
	}
	
	//받은 쪽지 확인상태 변경
	public void changing(MsgVO vo) {
		mybatis.insert("MsgDAO.changing",vo);
	}
	
	//받은 쪽지 내용 확인하기
	public MsgVO select_receive(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.select_receive", vo);
	}
	
	//받은 쪽지함에서 휴지통으로 이동
	public void receive_trashing(MsgVO vo) {
		mybatis.insert("MsgDAO.receive_trashing",vo);
	}
	
	

//Sent
	
	//보낸 쪽지 전체 목록
	public List<MsgVO> all_sent_list(MsgVO vo) {
		return mybatis.selectList("MsgDAO.all_sent_list",vo);
	}
	
	//보낸 쪽지 전체 수
	public int sent_cnt(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.sent_cnt", vo);
	}
	
	//보낸쪽지 받은이 id
	public String receiver_sent(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.receiver_sent",vo);
	}
	
	//받은 쪽지 전체 수
	public int receiver_sent_cnt(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.receiver_sent_cnt", vo);
	}
	
	//보낸쪽지 받은이 그룹
	public List<MsgVO> receiver_sent_list(MsgVO vo) {
		return mybatis.selectList("MsgDAO.receiver_sent_list",vo);
	}
	
	//보낸 쪽지 내용 확인하기
	public MsgVO select_sent(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.select_sent", vo);
	}
	
	//보낸 쪽지함에서 휴지통으로 이동
	public void sent_trashing(MsgVO vo) {
		mybatis.insert("MsgDAO.sent_trashing",vo);
	}
	

	
//Write
	
	//쪽지 보내기
	public void sending_write(MsgVO vo) {
		mybatis.insert("MsgDAO.sending_write",vo);
	}

	
	
//Keep
	
	//쪽지 보관함 전체 목록
	public List<MsgVO> all_keep_list(MsgVO vo) {
		return mybatis.selectList("MsgDAO.all_keep_list",vo);
	}
	
	//보관함 쪽지 전체 수
	public int keep_cnt(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.keep_cnt", vo);
	}
	
	//보관함 보낸이 쪽지 수
	public int sender_keep_cnt(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.sender_keep_cnt", vo);
	}
		
	//보관함 보낸이 그룹
	public List<MsgVO> sender_keep_list(MsgVO vo) {
		return mybatis.selectList("MsgDAO.sender_keep_list",vo);
	}
	
	//보관함 이전으로 복원
	public void keep_returning(MsgVO vo) {
		mybatis.insert("MsgDAO.keep_returning",vo);
	}
	
	//보관함에서 휴지통으로 이동
	public void keep_trashing(MsgVO vo) {
		mybatis.insert("MsgDAO.keep_trashing",vo);
	}

	
	
//Trash
	
	//휴지통 전체 목록
	public List<MsgVO> all_trash_list(MsgVO vo) {
		return mybatis.selectList("MsgDAO.all_trash_list",vo);
	}
	
	//휴지통 쪽지 전체 수
	public int trash_cnt(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.trash_cnt", vo);
	}
	
	//휴지통 보낸이 쪽지 수
	public int sender_trash_cnt(MsgVO vo) {
		return mybatis.selectOne("MsgDAO.sender_trash_cnt", vo);
	}
	
	//휴지통 보낸이 그룹
	public List<MsgVO> sender_trash_list(MsgVO vo) {
		return mybatis.selectList("MsgDAO.sender_trash_list",vo);
	}
	
	//휴지통 이전으로 복원
	public void trash_returning(MsgVO vo) {
		mybatis.insert("MsgDAO.trash_returning",vo);
	}
	
	//휴지통 비우기
	public void delete(MsgVO vo) {
		mybatis.insert("MsgDAO.delete",vo);
	}
}
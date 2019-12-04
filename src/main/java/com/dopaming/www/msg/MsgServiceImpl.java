package com.dopaming.www.msg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("msgService")
public class MsgServiceImpl implements MsgService{
	
	@Autowired
	private MsgDAO dao;

//Receive
	
	@Override
	public List<MsgVO> all_receive_list(MsgVO vo) {
		return dao.all_receive_list(vo);
	}
	
	@Override
	public int receive_cnt(MsgVO vo) {
		return dao.receive_cnt(vo);
	}
		
	@Override
	public String sender_receive(MsgVO vo) {
		return dao.sender_receive(vo);
	}
	
	@Override
	public int sender_receive_cnt(MsgVO vo) {
		return dao.sender_receive_cnt(vo);
	}
	
	@Override
	public List<MsgVO> sender_receive_list(MsgVO vo) {
		return dao.sender_receive_list(vo);
	}
		
	@Override
	public List<MsgVO> unselect_receive_list(MsgVO vo) {
		return dao.unselect_receive_list(vo);
	}
	
	@Override
	public int unselect_receive_cnt(MsgVO vo) {
		return dao.unselect_receive_cnt(vo);
	}
	
	@Override
	public List<MsgVO> check_keeping(MsgVO vo) {
		return dao.check_keeping(vo);
	}
	
	
	@Override
	public void receive_keeping(MsgVO vo) {
		dao.receive_keeping(vo);
	}
	
	@Override
	public void changing(MsgVO vo) {
		dao.changing(vo);
	}
	
	@Override
	public MsgVO select_receive(MsgVO vo) {
		return dao.select_receive(vo);
	}
		
	@Override
	public void receive_trashing(MsgVO vo) {
		dao.receive_trashing(vo);
	}

	
	
//Sent
	
	@Override
	public List<MsgVO> all_sent_list(MsgVO vo) {
		return dao.all_sent_list(vo);
	}
	
	@Override
	public int sent_cnt(MsgVO vo) {
		return dao.sent_cnt(vo);
	}
	
	@Override
	public String receiver_sent(MsgVO vo) {
		return dao.receiver_sent(vo);
	}
	
	@Override
	public int receiver_sent_cnt(MsgVO vo) {
		return dao.receiver_sent_cnt(vo);
	}
	
	@Override
	public List<MsgVO> receiver_sent_list(MsgVO vo) {
		return dao.receiver_sent_list(vo);
	}
	
	@Override
	public MsgVO select_sent(MsgVO vo) {
		return dao.select_sent(vo);
	}
	
	@Override
	public void sent_trashing(MsgVO vo) {
		dao.sent_trashing(vo);
	}
	

	
//Write
	
	@Override
	public void sending_write(MsgVO vo) {
		dao.sending_write(vo);
	}
	
	
	
//Keep
	
	@Override
	public List<MsgVO> all_keep_list(MsgVO vo) {
		return dao.all_keep_list(vo);
	}
	
	@Override
	public int keep_cnt(MsgVO vo) {
		return dao.keep_cnt(vo);
	}
	
	@Override
	public int sender_keep_cnt(MsgVO vo) {
		return dao.sender_keep_cnt(vo);
	}
		
	@Override
	public List<MsgVO> sender_keep_list(MsgVO vo) {
		return dao.sender_keep_list(vo);
	}
	
	@Override
	public void keep_returning(MsgVO vo) {
		dao.keep_returning(vo);
	}
	
	@Override
	public void keep_trashing(MsgVO vo) {
		dao.keep_trashing(vo);
	}
	

	
//Trash
	
	@Override
	public List<MsgVO> all_trash_list(MsgVO vo) {
		return dao.all_trash_list(vo);
	}
	
	@Override
	public int trash_cnt(MsgVO vo) {
		return dao.trash_cnt(vo);
	}
	
	@Override
	public int sender_trash_cnt(MsgVO vo) {
		return dao.sender_trash_cnt(vo);
	}
	
	@Override
	public List<MsgVO> sender_trash_list(MsgVO vo) {
		return dao.sender_trash_list(vo);
	}
	
	@Override
	public void trash_returning(MsgVO vo) {
		dao.trash_returning(vo);
	}
	
	@Override
	public void delete(MsgVO vo) {
		dao.delete(vo);
	}
	
}

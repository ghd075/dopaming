package com.dopaming.www.msg;

import java.util.List;

public interface MsgService {

//Receive
	
	//all_receive_list
	public List<MsgVO> all_receive_list(MsgVO vo);
	
	//receive_cnt
	public int receive_cnt(MsgVO vo);
	
	//sender_receive
	public String sender_receive(MsgVO vo);
	
	//sender_receive_cnt
	public int sender_receive_cnt(MsgVO vo);
	
	//sender_receive_list
	public List<MsgVO> sender_receive_list(MsgVO vo);
			
	//unselect_receive_list
	public List<MsgVO> unselect_receive_list(MsgVO vo);
	
	//unselect_receive_cnt
	public int unselect_receive_cnt(MsgVO vo);
	
	//check_keeping
	public List<MsgVO> check_keeping(MsgVO vo);
	
	//receive_keeping
	public void receive_keeping(MsgVO vo);
	
	//changing
	public void changing(MsgVO vo);
	
	//select_receive
	public MsgVO select_receive(MsgVO vo);
	
	//receive_trashing
	public void receive_trashing(MsgVO vo);
	
	

//Sent
	
	//all_sent_list
	public List<MsgVO> all_sent_list(MsgVO vo);
	
	//sent_cnt
	public int sent_cnt(MsgVO vo);
	
	//receiver_sent
	public String receiver_sent(MsgVO vo);
	
	//receiver_sent_cnt
	public int receiver_sent_cnt(MsgVO vo);
	
	//receiver_sent_list
	public List<MsgVO> receiver_sent_list(MsgVO vo);
	
	//select_receive
	public MsgVO select_sent(MsgVO vo);
	
	//sent_trashing
	public void sent_trashing(MsgVO vo);
	

	
//Write	
	
	//sending_write
	public void sending_write(MsgVO vo);
	
	
	
//Keep
	
	//all_keep_list
	public List<MsgVO> all_keep_list(MsgVO vo);
	
	//keep_cnt
	public int keep_cnt(MsgVO vo);
	
	//sender_keep_cnt
	public int sender_keep_cnt(MsgVO vo);
		
	//sender_keep_list
	public List<MsgVO> sender_keep_list(MsgVO vo);
	
	//keep_returning
	public void keep_returning(MsgVO vo);
	
	//keep_trashing
	public void keep_trashing(MsgVO vo);
	

	
//Trash
	
	//all_trash_list
	public List<MsgVO> all_trash_list(MsgVO vo);
	
	//trash_cnt
	public int trash_cnt(MsgVO vo);
	
	//sender_trash_cnt
	public int sender_trash_cnt(MsgVO vo);
	
	//sender_trash_list
	public List<MsgVO> sender_trash_list(MsgVO vo);
	
	//trash_returning
	public void trash_returning(MsgVO vo);
	
	//delete
	public void delete(MsgVO vo);
	
}
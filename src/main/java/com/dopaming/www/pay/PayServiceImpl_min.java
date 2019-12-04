package com.dopaming.www.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("payService")
public class PayServiceImpl_min implements Payservice_min {
	
	//Autowired가 UserDAO 자체를 가져오는것(DAO에 repository로 연결해줬다)
	//@Autowired private UserDAOSpring userDAO;
	@Autowired private PayDAOMybatis_min mambersDAO;

	//결제충전
	@Override
	public void insertPay(PayVO_min vo){
		mambersDAO.insertPay(vo);
	}

	//결제한사람 (아이디만)리스트(중복제거)
	@Override
	public List<PayVO_min> returnPay(PayVO_min vo) {
		return mambersDAO.returnPay(vo);
	}

	//결제했는 사람(단건)에 대한 세부내용(아이디,결제금액,충전날짜,결제고유코드)
	@Override
	public List<PayVO_min> detailPay(PayVO_min vo) {
		return mambersDAO.detailPay(vo);
	}

	//실질적으로 결제가 환불처리되는 과정
	@Override
	public void deletePay(PayVO_min vo) {
		mambersDAO.deletePay(vo);
	}

	@Override
	public int returnPayCount(PayVO_min vo) {
		return mambersDAO.returnPayCount(vo);
	}
}

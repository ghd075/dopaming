package com.dopaming.www.pay;

import java.util.List;

public interface Payservice_min {

	//결제충전
	public void insertPay(PayVO_min vo);
	
	//결제한사람 (아이디만)리스트(중복제거)
	public List<PayVO_min> returnPay(PayVO_min vo);
	
	//결제한사람 리스트 카운터
	public int returnPayCount(PayVO_min vo);
	
	//결제했는 사람(단건)에 대한 세부내용(아이디,결제금액,충전날짜,결제고유코드)
	public List<PayVO_min> detailPay(PayVO_min vo);
	
	//실질적으로 결제가 환불처리되는 과정
	public void deletePay(PayVO_min vo);
}

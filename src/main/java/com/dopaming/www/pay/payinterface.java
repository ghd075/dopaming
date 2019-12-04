package com.dopaming.www.pay;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;


/**
 * Unit test for simple App.
 */

@Component
public class payinterface {
	
	IamportClient client;
	private void setup() {
		//결제 고유키번호
		String test_api_key = "9220147042054401";
		String test_api_secret = "2E3wTXZRacWjFEk6UlFoKHvpGUo4kEgnjjUdhT4r60KLF5RzqxXbetAVoZ0IDg6Xz0aQUeh0f2MU7Euy";
		client = new IamportClient(test_api_key, test_api_secret);
	}
	//payinterface사용시 자동으로 setup메소드 실행
	public payinterface() {
		setup();
	}

	/*
	 * private void GetToken() { try { //엑세스토큰을 얻어옴(전체) IamportResponse<AccessToken>
	 * auth_response = client.getAuth();
	 * 
	 * //액세스토큰을 가져옴(특정) System.out.println(auth_response.getResponse()); //토큰의 일련번호
	 * System.out.println(auth_response.getResponse().getToken()); } catch
	 * (IamportResponseException e) { System.out.println(e.getMessage());
	 * 
	 * switch(e.getHttpStatusCode()) { case 401 : //TODO break; case 500 : //TODO
	 * break; } } catch (IOException e) { //서버 연결 실패 e.printStackTrace(); } }
	 */
	//DB에 있는 pay_code(컬럼)의 impuid값을 받아서 아임포트서버에서 전액환불처리
	public void CancelPaymentAlreadyCancelledImpUid(PayVO_min impuid) {
		String imp_uid=impuid.getPay_code();
		CancelData cancel_data = new CancelData(imp_uid, true); //imp_uid를 통한 전액취소
		
		try {
			IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);
			
			payment_response.getResponse(); // 이미 취소된 거래는 response가 null이다
		} catch (IamportResponseException e) {
			System.out.println(e.getMessage());
			
			switch(e.getHttpStatusCode()) {
			case 401 :
				//TODO
				break;
			case 500 :
				//TODO
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

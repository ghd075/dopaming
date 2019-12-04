<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/msg.js"></script>

<style>

table {
  width: auto;
  height: 100px;
  margin:auto;
}

td, th {
	border: 1px solid #dddddd;
	text-align: center;
	padding: 15px 8px;
}
tr:nth-child(even) {
	background-color: #dddddd;
	
}
.acorn {
text-align: left;
width:500px;
}

</style>
<script> 
//1.결제방식 radio선택후 버튼클릭하여 value값을 넘기면서 api화면 띄움
//2.IMP.request_pay에 적혀잇는 방식으로 api를 실행함
//3.rsp.paid_amount(결제금액),rsp.apply_num(카드 승인번호) 를 function payresult의 매개변수로 받음
//4.해당 2개의 name.value값을 받아 매개변수(param1,2)에 넘긴후 submit 실행
//5.값이 넘어가면 컨트롤러에서 vo에 저장후 mapping에서 값을 DB에 insert
//6.또한 값이 무사히 들어갔을시 결제에 성공했다고 alert뜨고 메인화면으로 이동

	var IMP = window.IMP; // 생략가능
	IMP.init('imp69493421'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

	//결제api사용시 넘어가는 값 2개를 매개변수로 받아 저장후 컨트롤러로 넘김
	function payresult(param1,param2){
		$('input[name="pay_code"]').val(param1);
		$('input[name="acorn_charge"]').val(param2);
		
		
		console.log(param1," ",param2);
		console.log($('input[name="pay_code"]').val());
		$("#payresult").submit();
	}
	
	function payment() {
		//라디오버튼 체크 value변수	
		var radioVal = $('input[name="pay"]:checked').val();
		
		//라디오버튼 value가 0일때(직접입력) inputpay(text)의 val를 사용
		if(radioVal==0){
			radioVal=$('input[name="inputpay"]').val();
		}
		//직접입력값이 0이면 값입력하라고 alert뜸
		if(radioVal==0){
			alert("직접입력해주세요.");
			return;
		}
		console.log(radioVal);
		//결제 api
		IMP.request_pay({
			pg : 'nice',
			pay_method : 'card',
			merchant_uid : 'merchant_' + new Date().getTime(),
			/* name : '주문명:결제테스트', */
			amount : radioVal,
			buyer_email : '${sessionScope.email}',
			buyer_name : '${sessionScope.Id}'
		}, function(rsp) {
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				payresult(rsp.imp_uid,rsp.paid_amount);
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
			alert(radioVal + "원 " + msg);
		})
	};
</script>
</head>
<body>
<form id="payresult" action="./acornForm" method="post">
<input type="hidden" name="acorn_charge" value=""/>
<input type="hidden" name="pay_code" value=""/>
</form> 
	<div >
		<h1 style="text-align: center;">결제페이지</h1>
		<hr>
		<div>
			<table style="margin-left: auto; margin-right: auto; ">
				<tr >
					<th colspan="2">구매금액</th>					
					<th style="width="350px;">세부충전금액</th>
					
				</tr>
				<tr>
				    <td style="BORDER-RIGHT : 0px SOLID;"><input type="radio" name="pay" value="0" style="width:20px;height:20px;"></td><td> 직접 입력</td>
				    <td class="acorn"><input type="text" name="inputpay" size='6'>아콘 충전</td>
				</tr>
				<tr>
					<td style="BORDER-RIGHT : 2px SOLID white;"><input type="radio" name="pay" value="5000" style="width:20px;height:20px"></td><td> 5000 아콘</td>
					<td class="acorn">5000아콘 충천 + 500아콘 추가 충전</td>
				</tr>
				<tr>
					<td style="BORDER-RIGHT : 0px SOLID;"><input type="radio" name="pay" value="10000" style="width:20px;height:20px"></td><td> 10000 아콘</td>
					<td class="acorn">10000아콘 충전 + 1000아콘 추가 충전</td>
				</tr>
				<tr>
					<td style="BORDER-RIGHT : 2px SOLID white;"><input type="radio" name="pay" value="20000" style="width:20px;height:20px"></td><td> 20000 아콘</td>
					<td class="acorn">20000원 충천 + 2500아콘 추가 충전</td>
				</tr>
				<tr>
					<td style="BORDER-RIGHT : 0px SOLID;"><input type="radio" name="pay" value="25000" style="width:20px;height:20px"></td><td> 25000 아콘</td>
					<td class="acorn">25000원 충천 + 3000아콘 추가 충전</td>
				</tr>
				<tr>
					<td style="BORDER-RIGHT : 2px SOLID white;"><input type="radio" name="pay" value="50000" style="width:20px;height:20px"></td><td> 50000 아콘</td>
					<td class="acorn">50000원 충천 + 6500아콘 추가 충전</td>
				</tr>
				<tr>
					<td style="BORDER-RIGHT : 0px SOLID;"><input type="radio" name="pay" value="100000" style="width:20px;height:20px"></td><td> 100000 아콘</td>
					<td class="acorn">100000원 충천 + 15000아콘 추가 충전</td>
				</tr>
			</table>
		</div><br><br>
		<div style="text-align: center;">
			<button type="button" onclick="payment()" class="btn btn-primary" >결제하기</button>
		</div>
		<br><br><br>
	</div>
</body>
</html>
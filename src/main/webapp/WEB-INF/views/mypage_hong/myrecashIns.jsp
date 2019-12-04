<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환급계좌 정보 입력</title>
<style>
.insertbar {
  padding: 8px;
  display: inline-block;
  font-size: 20px;
  width: 97.5%;
  border: none;
  background: #d6d6d6;
}

.insertbar:hover {
  background-color: #fff;
  outline: none;
}

.insertbtn {
  background-color: #fff;
  color: black;
  font-weight:bold;
  padding: 8px;
  margin-top: 20px;
  margin-left: 10%;
  border: none;
  width: 80%;
 
}

.insertbtn:hover {
  background-color: #000;
  color: white;
}
</style>
<script>
var stock = ${memberSession.acorn_stock};

function check(frm) {
	
	if(frm.reg_recash.value > stock) {
		alert("환급수량은 현재 보유중인 아콘을 넘을 수 없습니다.");
	}	
}

function insert(frm) {
	
	if(frm.bank.value == "") {
		alert("계좌은행을 입력해 주세요.");
	}
	
	else if(frm.account_no.value == "") {
		alert("계좌번호를 입력해 주세요.");
	}
	
	else if(frm.account_own.value == "") {
		alert("예금주를 입력해 주세요.");
	}
	
	else if(frm.reg_recash.value == "") {
		alert("환급수량을 입력해 주세요.");
	}
	
	else {
		if(frm.reg_recash.value > stock) {
			alert("환급수량이 현재 보유중인 아콘보다 많습니다.");
		} 
		else {
			frm.submit();
		}
	}
}
</script>

</head>
<body>
<form action="myReCashIns.do" method="post">
	<h3>계좌은행</h3>
		<input type="text" class="insertbar" name="bank" required> 
	<h3>계좌번호</h3>
		<input type="text" class="insertbar" name="account_no" placeholder=' "-" 는 생략해주세요.'>
	<h3>예금주</h3>
		<input type="text" class="insertbar" name="account_own">
	<h3>신청환급량</h3>
		<input type="text" class="insertbar" name="reg_recash" id="myacorn" onKeyUp='check(this.form)'>
			<label for="myacorn">현재 아콘 보유량:${memberSession.acorn_stock}</label>
	<button type="button" class="insertbtn" onclick="insert(this.form)">신청하기</button>
</form>
</body>
</html>
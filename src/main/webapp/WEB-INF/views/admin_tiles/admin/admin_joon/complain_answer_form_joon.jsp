<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//내용 입력 확인 기능
function answer_insert_btn(){
	//변수선언
	var chk = document.getElementById("message_title").value; //단건 id찾기
	var chk2 = document.getElementById("message_content").value; //단건 id찾기
	
	if (chk == '') { // 제목에 값이 없을시
		alert("제목을 입력하세요");
		return false;
	}
	else if(chk.length >= 20){
		alert("20 자 이내로 적어주세요");
		return false;
	}
	
	else if (chk2 == '') { // 내용 값이 없을시
		alert("내용을 입력하세요");
		return false;

	}
	else if(chk2.length >= 2000){
		alert("2000 자 이내로 적어주세요");
		return false;
	}
	
	alert("등록이 되었습니다."); // 확인창
	answer_frm.action = "answer_insert"
	answer_frm.submit();
}
</script>
</head>
<body>
<div style="width:90%; align-self: center">

<h3 align=left><u>고객센터 답변</u></h3>
	
<form name="answer_frm">
<input type="hidden" name="member_id" value="${ComplainVO.member_id}"/>
<input type="hidden" name="complain_type" value="${ComplainVO.complain_type}"/>
<input type="hidden" name="complain_no" value="${ComplainVO.complain_no}"/>
	보내는 사람 : 관리자 <br>
	제목 : 
	<input type="text" id="message_title" name="message_title" 
	style="width:100%; height:40px; background-color:ivory; border:1 solid blue; 
	font-family:굴림; font-size:10pt; color:red" onMouseOver="this.style.backgroundColor=''" 
	onMouseOut="this.style.backgroundColor='ivory'"> <br><br>
	
	내용 :<br><div align="center">
	<textarea id="message_content" name="message_content" 
	style=" width:100%; height:500; background-color:ivory; border:1 solid blue; 
	font-family:굴림; font-size:10pt; color:red" onMouseOver="this.style.backgroundColor=''" 
	onMouseOut="this.style.backgroundColor='ivory'">
	
	유형 : ${ComplainVO.complain_type}
	제목 : ${ComplainVO.complain_title}	
	글쓴이 : ${ComplainVO.member_id}
	날짜 : ${ComplainVO.complain_date}
	내용 : 
	      
	      ${ComplainVO.complain_content}
	      
	=====================================================================================
	
	</textarea></div><br>
	
	<button style="float:right;" type="button" onclick="answer_insert_btn()">보내기</button>&nbsp;&nbsp;

</form>

</div>
</body>
</html>
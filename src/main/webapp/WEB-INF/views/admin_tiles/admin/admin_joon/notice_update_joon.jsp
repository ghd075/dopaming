<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

//내용 입력 확인 기능
function notice_update(){
	//변수선언
	var chk = document.getElementById("notice_title").value; //단건 id찾기
	var chk2 = document.getElementById("notice_content").value; //단건 id찾기
	
	if (chk == '') { // 제목에 값이 없을시
		alert("제목을 입력하세요");
		return false;
	}
	
	else if(chk.length >= 30){
		alert("30 자 이내로 적어주세요");
		return false;
	}
	else if (chk2 == '') { // 내용 값이 없을시
		alert("내용을 입력하세요");
		return false;

	}
	else if(chk2.length >= 500){
		alert("500 자 이내로 적어주세요");
		return false;
	}
	

	alert("수정이 되었습니다."); // 확인창
	notice_update_frm.action = "notice_update";
	notice_update_frm.submit(); // 보낸다.
}

</script>
</head>
<body>
<div style="width:90%; align-self: center">
	
	<h3 align=center>공지사항-수정</h3>
	
	<!-- 수정할 데이터를 보내주는 폼 -->
	<form name="notice_update_frm" method="post">
	
	<!-- Notice_no가 필요가기 때문에 값을 받을 곳을 만들어둔다 -->
	<input type="hidden" name="notice_no" value="${notice.notice_no}">	
	
	공지제목 : <input type="text" id="notice_title" name="notice_title"   value="${notice.notice_title}" 
	style="width:100%; height:40px; background-color:ivory; border:1 solid blue; 
	font-family:굴림; font-size:10pt; color:red" onMouseOver="this.style.backgroundColor=''" 
	onMouseOut="this.style.backgroundColor='ivory'"> <br><br>
	
	공지내용 : <textarea id="notice_content" name="notice_content" 
	style="width:100%; height:500; background-color:ivory; border:1 solid blue; 
	font-family:굴림; font-size:10pt; color:red" onMouseOver="this.style.backgroundColor=''" 
	onMouseOut="this.style.backgroundColor='ivory'">${notice.notice_content}</textarea><br><br>
	
	<button style="float:right;" type="button" class="" onclick="notice_update()">수정</button><br>
</form>
</div>
</body>
</html>
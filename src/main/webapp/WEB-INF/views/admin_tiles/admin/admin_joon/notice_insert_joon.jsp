<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 카톡 meta -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
<title>KakaoLink v2 Demo(Default / Feed) - Kakao JavaScript SDK</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
//내용 입력 확인 기능
function notice_insert_btn2(){
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
	
	alert("등록이 되었습니다."); // 확인창
	notice_frm.action = "notice_insert"
	notice_frm.submit();

}

</script>
<!-- 카카오톡 api -->
<script type='text/javascript'>
// // 사용할 앱의 JavaScript 키를 설정해 주세요.
Kakao.init('f98be4f404409ef11e38b90311e98ca0'); //내꺼
function notice_insert_btn(){
			// 로그인창. 처음 한번만 호출하면 됩니다.
			   Kakao.Auth.login({ //카카오톡 객체에서 로그인을 하는 기능을 불러온다.
				scope : "PROFILE,TALK_MESSAGE", //프로필에 대한 정보와, 메세지를 보낼 수있는 권한을 얻기 위한 동의 화면을 띄우고 얻기
						success : function(authObj) {//로그인 성공시, 나에게 보내는 API를 호출합니다.
							Kakao.API.request({//restAPI를 사용하기 위해서 request로 요청한다.
										url : '/v2/api/talk/memo/default/send', //내게 보내기
										data : { //본문
											template_object : {
												"object_type" : "text",
												"link": {
										            "web_url": "http://114.200.227.226/dopaming/notice_select_new",
										            "mobile_web_url": "http://114.200.227.226/dopaming/notice_select_new"
										        },
												"text": "공지제목: "+notice_frm.notice_title.value +"\n"+"공지내용:\n"+notice_frm.notice_content.value,
										        
										        "button_title": "바로 확인"
												
											}//템플릿 오브젝트
										},//데이타
										success : function(res) {
											//alert(JSON.stringify(res));
											notice_insert_btn2();
										},//메세지 성공시
										fail : function(error) {
											alert(JSON.stringify(error));
										}//메세지 실패시
									});//Kakao.API.request요청
						},//접속성공시
						fail : function(err) {//접속실패시
							alert(JSON.stringify(err));
						}

					});//로그인버튼기능
}
</script>

</head>
<body>
<div style="width:90%; align-self: center">

	<h3 align=center>공지사항-등록</h3>
	
	<form name="notice_frm">
	공지제목 : <input type="text" id="notice_title" name="notice_title" 
	style="width:100%; height:40px; background-color:ivory; border:1 solid blue; 
	font-family:굴림; font-size:10pt; color:red" onMouseOver="this.style.backgroundColor=''" 
	onMouseOut="this.style.backgroundColor='ivory'"> <br><br>
	
	공지내용 :<textarea id="notice_content" name="notice_content" 
	style="width:100%; height:500; background-color:ivory; border:1 solid blue; 
	font-family:굴림; font-size:10pt; color:red" onMouseOver="this.style.backgroundColor=''" 
	onMouseOut="this.style.backgroundColor='ivory'"></textarea><br><br>
	<a id="" href="javascript:notice_insert_btn()">
	<button style="float:right;" type="button">등록</button><br>
	</a>
	</form>
	
</div>
</body>
</html>
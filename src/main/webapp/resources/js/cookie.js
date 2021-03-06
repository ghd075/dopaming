$(document).ready(function() {
	var userInputId = getCookie("userId");// 저장된 쿠기값 가져오기
	$("input[name='member_id']").val(userInputId);

	if ($("input[name='member_id']").val() != "") { // 그 전에 ID를 저장해서 처음 페이지 로딩
		// 아이디 저장하기 체크되어있을 시,
		$("#loginCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	}

	$("#loginCheck").change(function() { // 체크박스에 변화가 발생시
		if ($("#loginCheck").is(":checked")) { // ID 저장하기 체크했을 때,
			var userInputId = $("input[name='member_id']").val();
			setCookie("userId", userInputId, 1); // 1일 동안 쿠키 보관
		} else { // ID 저장하기 체크 해제 시,
			deleteCookie("userId");
		}
	});

	// ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	$("input[name='member_id']").keyup(function() { // ID 입력 칸에 ID를 입력할 때,
		if ($("#loginCheck").is(":checked")) { // ID 저장하기를 체크한 상태라면,
			var userInputId = $("input[name='member_id']").val();
			setCookie("userId", userInputId, 1); // 1일 동안 쿠키 보관
		}
	});
});

//쿠키 생성
function setCookie(cookieName, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var cookieValue = escape(value)
			+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
	document.cookie = cookieName + "=" + cookieValue;
}

//쿠키 삭제
function deleteCookie(cookieName) {
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + "= " + "; expires="
			+ expireDate.toGMTString();
}

//쿠키 가져오기
function getCookie(cookieName) {
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = '';
	if (start != -1) {
		start += cookieName.length;
		var end = cookieData.indexOf(';', start);
		if (end == -1)
			end = cookieData.length;
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
}
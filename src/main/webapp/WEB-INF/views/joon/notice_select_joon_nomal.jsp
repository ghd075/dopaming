<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
td {
	border-bottom: 1px solid;
	border-top: 1px solid;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
//삭제 기능
function notice_delete(){
	if(confirm("삭제할까요?")){ // 확인기능
		notice_select_frm.action = "notice_delete" // 확인을 누르면 값을 notice_update->notice_delete로 변경한다.
		notice_select_frm.submit(); // 보낸다.
	}
}

</script>
</head>
<body>

<h1 align = "center"><u>공지사항</u></h1>

<!-- 보낼폼을 설정 -->
<form name="notice_select_frm" action="notice_update_form">

<!-- Notice_no가 필요가기 때문에 값을 받을 곳을 만들어둔다 -->
<input type="hidden" name="notice_no" value="${notice.getNotice_no()}">
<input type="hidden" name="notice_title" value="${notice.getNotice_title()}">
<input type="hidden" name="notice_content" value="${notice.getNotice_content()}">

<table width="90%" height = "40px" align="center">
        <tr bgcolor="">        
            <td width="80p"  align="center">제목 : </td>
            <td>${notice.getNotice_title()}</td>
            <td width="80p">글쓴이 : </td>
            <td width="200p">관리자</td>
            <td width="80p" align="center">날짜 : </td>
            <td width="200p">${notice.getNotice_date()}</td>     
        </tr>
        </table>
        
    <table width="90%" height = "500px" align="center" border="1">
	<tr><td valign="top"><pre>${notice.getNotice_content()}"</pre></td></tr>
	</table><br>
</form>
	
</body>
</html>
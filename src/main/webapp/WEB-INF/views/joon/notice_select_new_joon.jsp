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
</head>
<body>
<div style="width:90%; align-self: center;">


<h1 align = "center"><u>공지사항</u></h1>

<!-- Notice_no가 필요가기 때문에 값을 받을 곳을 만들어둔다 -->
<input type="hidden" name="notice_no" value="${list[0].getNotice_no()}">
<input type="hidden" name="notice_title" value="${list[0].getNotice_title()}">
<input type="hidden" name="notice_content" value="${list[0].getNotice_content()}">

<table width="90%" height = "40px" align="center">
        <tr>        
            <td width="80p" align="center">제목 : </td>
            <td>${list[0].getNotice_title()}</td>
            <td width="80p"  align="center">글쓴이 : </td>
            <td width="200p">관리자</td>
            <td width="80p"  align="center">날짜 : </td>
            <td width="200p">${list[0].getNotice_date()}</td>     
        </tr>
        </table>
        
    <table width="90%" height = "500px" align="center" border="1">
	<tr><td valign="top"><pre>${list[0].getNotice_content()}</pre></td></tr>
	</table><br>

</div>	
</body>
</html>
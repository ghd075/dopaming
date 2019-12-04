<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	response.setStatus(HttpServletResponse.SC_OK);
%>
<!-- 에러 메시지 -->
<table width="100%" border="1" cellspacing="0" cellpadding="0"
align="center">
	<tr>
		<td align="center">
			<br><br><br><br>
			<h1>코드에러입니다.</h1>
			<br><br><br><br>
		</td>
	</tr>
</table>
</body>
</html>
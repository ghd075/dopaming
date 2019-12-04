<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

.dateBox {
	width: 115px;
	border: none;
	color: #585858;
	font-weight: bold;
	font-size: 17px;
	text-align: center;
	float: left;
}

.dateBox:focus {
	outline: none;
}

.pagination {
	display: inline-block;
}

.pagination ul {
	display: inline-block;
	padding: 10px;
	margin: 0px;
}

.pagination li {
	display: inline-block;
	padding: 10px;
}

.active {
	color: red;
}

th, td {
	text-align: center;
}
</style>
<script>
function go_page(p) {
	recashFrm.page.value = p;
	recashFrm.submit();
}
</script>
</head>
<body>
	<% String reg_date = request.getParameter("reg_date"); %>
	<h1 style="text-align: center;">환급신청목록</h1>
	<hr>
	<input type="text" class="dateBox" value="<%=reg_date%>" readonly>
	<form name="recashFrm" action="">	
 		<table class="table table-striped table-hover">
 			<thead>
 				<tr>
	 				<th>신청번호</th>
	 				<th>계좌은행</th>
	 				<th>계좌번호</th>
	 				<th>예금주</th>
	 				<th>아콘환급량</th>
	 				<th>신청날짜</th>	 				
	 				<th>환급날짜</th>
 				</tr>
 			</thead>
 			<tbody>
 			<c:choose>
 				<c:when test="${fn:length(list) == 0}">
 					<tr>
 						<td colspan="7" align="center">
 							신청내역이 없습니다.
 						</td>
 					</tr>
 				</c:when>
 				<c:otherwise>
		 			<c:forEach items="${list}" var="recash">
		 				<tr>
		 					<td>${recash.reg_no}</td>
		 					<td>${recash.bank}</td>
		 					<td>${recash.account_no}</td>
		 					<td>${recash.account_own}</td>
		 					<td>${recash.reg_recash}</td>
		 					<td>${recash.reg_date}</td>
		 					<td>${recash.recash_date}</td>
		 				</tr>
		 			</c:forEach>
 				</c:otherwise>
 			</c:choose>
 			</tbody>
 		</table>
 		
 		<input type="hidden" name="page" value="1">
 		<input type="hidden" name="reg_date" value="<%=reg_date%>">
	</form>
	<div align="center">
		<my:paging paging="${paging}"/>
	</div>
</body>
</html>
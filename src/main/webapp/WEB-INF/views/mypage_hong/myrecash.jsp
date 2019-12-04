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
<title>마이페이지</title>
<style>
#spann {
	color: red;
	float: right;
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
	
	function insertPop() {
		window.open('myReCashIns','insert','width=600,height=500');
	}	
</script>
</head>
<body>
	<h1 style="text-align: center;">환급조회/신청</h1>
	<hr>
	<form name="recashFrm" action="">
		<span id="spann">
			아콘과 현금의 환급 비율은 1:1 입니다.
			<button class="btn btn-danger" type="button" onclick="insertPop()">아콘환급</button>
		</span>
 		<table class="table table-striped table-hover">
 			<thead>
 				<tr>
	 				<th>신청번호</th>
	 				<th>계좌은행</th>
	 				<th>계좌번호</th>
	 				<th>예금주</th>
	 				<th>아콘환급량</th>
	 				<th>신청날짜</th>	 				
	 				<th>환급여부</th>
 				</tr>
 			</thead>
 			<tbody>
 			<c:choose>
 				<c:when test="${fn:length(list) == 0}">
 					<tr>
 						<td colspan="7" align="center">
 							환급내역이 없습니다.
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
		 					<td>${recash.state}</td>
		 				</tr>
		 			</c:forEach>
 				</c:otherwise>
 			</c:choose>
 			</tbody>
 		</table>
 		<input type="hidden" name="page" value="1">
	</form>
	<div align="center">
		<my:paging paging="${paging}"/>
	</div>
</body>
</html>
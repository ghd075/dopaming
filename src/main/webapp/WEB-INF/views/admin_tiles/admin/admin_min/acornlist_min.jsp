<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<style>
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
</style>
<script>
	function go_page(p) {
		searchFrm.page.value = p;
		searchFrm.submit();
	}
</script>
</head>
<body>
	<form name="searchFrm" method="get">
		<input type="hidden" name="page" value="1">
		<div
			class="px-3 py-5 bg-gradient-info text-white text-gray-100 p-3 bg-dark m-0"
			style="text-align: center;">
			<h3>환불처리(<c:set var="now" value="<%=new java.util.Date()%>" /> 
			<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /> ) 
			</h3>
		</div>
		<div class="px-3 py-5 bg-gradient-success text-white">
			<h4>
				조건<br>
			</h4>
			<h6>
				1. 2,3번에 해당안되면 리스트자체가 안나옴<br> 
				2. 당일 0시~24시에 충전한 금액만 당일 환불가능(다음날 넘어가면 환불불가)<br> 
				3. 남아있는아콘>충전아콘이어야함 <Br> 불가능한 예시<br>
				ex)충전후 포인트 소모한다음에 남은아콘이 충전아콘보다 적을때<br>
			</h6>
		</div>
		<table class="table table-striped table-hover"
			style="text-align: center; font-size: 25px;">
			<thead>
				<tr style="font-size: 25px">
					<td>
					충전했는 아이디리스트(클릭시 세부내용으로이동/오름차순)
					</td>
					<td>
					등록날짜
					</td>
				<tr>
			<thead>
			<tbody>
				<c:forEach items="${acornlist}" var="list">
					<tr>
						<td><a href="/dopaming/admin/acorndetaillist?member_id=${list.member_id}">${list.member_id }</a></td>
						<td>${list.acorn_date}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div align="center">
			<my:paging paging="${paging}" />
		</div>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
#spann {
float: right;
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
	<div>
		<h1 style="text-align: center;">일반회원관리</h1>
		<hr>
		<span id="spann">
		<div>
			<form name="searchFrm" method="get">
				아이디:<input type="hidden" name="page" value="1"> 
				<input name="searchKeyword" value="${boardVO.searchKeyword}" />
				<button class="btn btn-info" type="submit">검색</button>
			</form>
		</div>
		</span>
		<div>
		
		<form name="form">
			<table class="table table-striped table-hover" style="text-align: center;">
			   <thead>
				<tr>
					<td>아이디</td>
					<td>이메일</td>
					<td>가입날짜(내림차순)</td>
					<td>등급</td>
				<tr>
				<thead>
				<tbody>
					<c:forEach items="${normalList}" var="list1">
						<!-- 등급관리 전체조회 -->
						<tr>
							<td><a href="blackInsert/${list1.member_id }">${list1.member_id}</a></td>
							<td>${list1.member_email }</td>
							<td>${list1.member_date }</td>
							<td>${list1.grade_kor }</td>
						</tr>
					</c:forEach>
				<tbody>
			</table>
		</form>
		</div>
		<div align="center">
		<my:paging paging="${paging}" />
		</div>
		<br> <br> <br>
	</div>
</body>
</html>
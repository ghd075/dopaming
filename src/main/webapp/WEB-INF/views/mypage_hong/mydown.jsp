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
		downFrm.page.value = p;
		downFrm.submit();
	}	
</script>
</head>
<body>
	<h1 style="text-align: center;">내가받은자료</h1>
	<hr>
	<form name="downFrm" action="">
 		<table class="table table-striped table-hover">
 			<thead>
 				<tr>
	 				<th>등록번호</th>
	 				<th>제목</th>
	 				<th>종류</th>
	 				<th>다운로드일</th>
	 				<th>판매자</th>
 				</tr>
 			</thead>
 			<tbody>
 			<c:choose>
 				<c:when test="${fn:length(list) == 0}">
 					<tr>
 						<td colspan="5" align="center">
 							구매내역이 없습니다.
 						</td>
 					</tr>
 				</c:when>
 				<c:otherwise>
		 			<c:forEach items="${list}" var="down">
		 				<tr>
		 					<td>${down.board_no}</td>
		 					<td><a href="${pageContext.request.contextPath }/filepost?board_no=${down.board_no}&member_id=${down.member_id}">
		 					${down.board_title}</td>
		 					<td>${down.category_big}</td>
		 					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${down.download_date}"/></td>
		 					<td>${down.sal_id}</td>
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
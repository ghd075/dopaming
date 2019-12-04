<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* 페이징태그 스타일 */
.pagination {
	display: inline-block;
}

.pagination ul {
 	padding: 10px;
	margin: 0px;
}

.pagination li {
    display: inline-block; /* 페이징 가로효과 */
	padding: 10px;
}

.active {
	color: red;
}

a {
	text-decoration: none;
}

* {
	box-sizing: border-box;
}
td {
	border-bottom: 1px solid;
	height: 50px;
}
</style>
<!-- 전체선택을 하기 위해서는 자바스크립트는 포문을 써야하지만 jquery를 쓰면 배열을 자동으로 받아온다 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
//페이징 기능(처음/끝 값을 보내준다.)
function goList(p){
	page_form.page.value = p;
	page_form.submit();
}

</script>
</head>
<body>
<div style="height:700px; width:90%; margin: 0px;" align="center">

<!-- 답변 수정값(complain_check) 보내는 폼(category_form) -->
<form name="check_form">
<input type="hidden" name="complain_no" value="" />
<input type="hidden" name="complain_check" value="" />
<input type="hidden" name="complain_type" value="${list[0].getComplain_type()}" />
<input type="hidden" name="page" value="${paging.page}" /> <!-- 페이지고정을 위해 값을 보낸다 -->
</form>

<!-- 페이징 값 보내는 폼(category_form) -->
<form name="page_form">
<input type="hidden" name="page" value="1" />
<!-- 지정된complain_type을 고정시키기 -->
<input type="hidden" name="complain_type" value="${list[0].getComplain_type()}" />
</form>

	<u><h3 align=center>${complainVO.getComplain_type()} 목록</h3></u><br>

	<table width="100%" >
		<tr align="center">
			<td width="100px">번호</td>
			<td>제목</td>
			<td>날짜</td>
			<td>답변유무</td>
		</tr>
		
		<c:forEach items="${list}" var="i" varStatus="status">
		<!-- 역순 가상번호 -->
		<c:set var = "pn" value = "${paging.totalRecord - ((paging.page-1) * paging.pageUnit + status.index) }"/>
		
			<tr align="center">
				<td>${pn}</td>
				<td><a href="${pageContext.request.contextPath}/complain_select_nomal?complain_no=${ i.getComplain_no()}">
					${i.getComplain_title()}</a></td>
				<td>${i.getComplain_date() }</td>
				<td>${i.getComplain_check() }</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${empty list}">
		<h3 align=center>요청하신 자료가 없습니다.</h3>
		</c:if>
	<br>
<!-- 페이징버튼 -->
<c:if test="${not empty list}">
<div align="center">
<my:paging_joon paging="${paging}" jsfunc="goList"/>
</div>
</c:if>

</div>
</body>
</html>
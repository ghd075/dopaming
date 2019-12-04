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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
//페이징 기능(처음/끝 값을 보내준다.)
function goList(p){
	category_form.page.value = p;
	category_form.submit();
}

//풀다운기능
function changeMenu(){
	category_form.category_small.value = document.getElementsByName("category_small")[0].value
	category_form.submit();
}
//검색_풀다운
function changeMenu2(){
	category_form.category_small.value = document.getElementsByName("searchType")[0].value
}
</script>
</head>
<body >
<div style="height:700px; width:90%; align-self: center;">

<h3 align=center><u>게시판 목록</u></h3><br>

<!-- 페이징 값 보내는 폼(category_form) -->
<span style= "align-self:center; width: 100%;">
<form name="category_form">
<input type="hidden" name="page" value="1"></input>
<select style="float: left; height: 29px" name="category_small" onchange="changeMenu()">
		<option value="">전체</option>
		<option value="최신">최신</option>
		<option value="국내">국내</option>
		<option value="외국">외국</option>	
</select>

<!-- 검색창 -->
<span>
	<button style="float: right;" type="submit"> 검색</button>
	<input name="searchKeyword" style="float: right;" type="text">
	<select style="float: right; height: 29px" name="searchType" onchange="changeMenu2()">
		<option value="">검색선택</option>
		<option value="board_title">게시물 제목</option>
		<option value="member_id" >아이디</option>
	</select>
</span>

<!-- 지정된 풀다운 메뉴를 고정시키기 -->
<script>
category_form.category_small.value='${boardListVO.category_small}';//BoardListVO에 정보를 가져온다.
category_form.searchKeyword.value='${boardListVO.searchKeyword}';//BoardListVO에 정보를 가져온다.
category_form.searchType.value='${boardListVO.searchType}';//????
</script>
</form>
</span>
<br><br> 

<!-- 테이블(컬럼명) -->
<table width="100%" align="center">
	<tr align= "center" bgcolor="silver" height= "40px">
		<th>번호</th>
		<th>제목</th>
		<th width="100px">아이디</th>
		<th width="100px">대분류</th>
		<th width="100px">소분류</th>
	</tr>
		
<!--테이블(데이터값들)db에서 가져온 자료를 forEach로 반복해서 가져온다-->		
<c:forEach items="${list}" var="i" varStatus="status">
	<tr align = "center">
		<!-- 역순 가상번호 -->
		<c:set var = "pn" value = "${paging.totalRecord - ((paging.page-1) * paging.pageUnit + status.index) }"/>
		
		<td>${pn}</td>
		<td><a href="${pageContext.request.contextPath}/filepost?board_no=${ i.getBoard_no()}">
			${ i.board_title}</a></td>
		<td>${ i.getMember_id()}</td>
		<td>${ i.getCategory_big()}</td>	
		<td>${ i.getCategory_small()}</td>
	</tr>
</c:forEach>
</table>
<br>
<c:if test="${empty list}">
		<h3 align=center>요청하신 자료가 없습니다.</h3>
</c:if>

<!-- 페이징버튼 -->
<c:if test="${not empty list}">
<div align="center">
<my:paging_joon paging="${paging}" jsfunc="goList"/>
</div>
</c:if>
</div>
</body>
</html>
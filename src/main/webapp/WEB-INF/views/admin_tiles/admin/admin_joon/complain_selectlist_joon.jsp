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
 
//답변기능(답변기능의 필요한 값들을 전용 폼으로 만들어 보낸다.)
function complain_check_update(complain_no){
	//체크박스 입력 체크
	check_form.complain_no.value = complain_no; //이벤트가 발생할때 생긴 complain_no 값을 넣는다.
	check_form.complain_check.value = event.target.value; //이벤트가 발생한 타겟의 값을 넣는다.
	var n = check_form.complain_check.value
	if( n == "N" ){ //값이 N라일때
		if(confirm("답변이 완료되지 않았습니까?")){
			check_form.action = "complain_check_update";
			check_form.submit();}
	}else if(confirm("답변이 완료 되었습니까?")){
	check_form.action = "complain_check_update";
	check_form.submit();
	}
}
</script>
</head>
<body>
<div style="width:90%; align-self: center">

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

	<table width="100%" align="center">
		<tr align="center" bgcolor="silver" height= "40px">
			<th width="100px">번호</th>
			<th>제목</th>
			<th>작성자</th>
			<c:if test="${list[0].getComplain_type() == '신고' }"> <!-- 게시판번호가 있다면 출력 -->
			<th>신고된 게시판</th></c:if>
			<th>날짜</th>
			<th>답변유무</th>
			<th>답변</th>
		</tr>
		
		<c:forEach items="${list}" var="i" varStatus="status">
		<!-- 역순 가상번호 -->
		<c:set var = "pn" value = "${paging.totalRecord - ((paging.page-1) * paging.pageUnit + status.index) }"/>
		
			<tr align="center">
				<td>${pn}</td>
				<td><a href="${pageContext.request.contextPath}/admin/complain_select?complain_no=${ i.getComplain_no()}">
					${i.getComplain_title()}</a></td>
				<td>${i.getMember_id() }</td>
				
				<c:if test="${list[0].getComplain_type() == '신고' }"> <!-- 게시판번호가 있다면 태그출력 -->
				<td>
					<c:if test="${i.getBoard_no() != 0}"> <!-- 게시판 번호가 있다면 번호를 출력 -->
					<a href="${pageContext.request.contextPath}/filepost?board_no=${i.getBoard_no()}">
					${i.getBoard_no() }
					</a>
					</c:if>
				</td>
				</c:if>

				<td>${i.getComplain_date() }</td>
				<td>${i.getComplain_check() }</td>
				<td><select name="complain_check" onchange="complain_check_update('${ i.getComplain_no()}')">
						<option value="N">NO</option>
						<option value="Y">YES</option>	
					</select>
					<script> //답변상태값을 DB의 값과 맞추어 준다. 
					document.getElementsByName("complain_check")[${status.count}].value='${ i.getComplain_check()}'
					</script>
				</td>
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
<br>

</div>
</body>
</html>
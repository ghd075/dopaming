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
//전체선택
function checkAll(){
      if( $("#td_checkAll").is(':checked') ){
        $("input[name=td_checkbox]").prop("checked", true);
      }else{
        $("input[name=td_checkbox]").prop("checked", false);
      }
}

//선택삭제 기능
function td_delete(){
	//체크박스 입력 체크
	var chk = document.getElementsByName("td_checkbox"); //태그찾기
	var cnt = 0; //태그의 배열
	for (i = 0; i < chk.length; i++) { //td_checkbox그룹에서 체크된 값을 찾기위해 for문을 돌려 체크된 값이 있는지를 확인한다.
		//체크된 카운트
		if (chk[i].checked == true) { //태그에 체크가 되었는지 확인
			cnt++ //체크수 증가
		}
	}
	if (cnt == 0) { // 체크수가 0이면
		alert("삭제할 게시글을 선택하세요");
		return false;

	}
	if(confirm("삭제할까요?")){
		form.action = "notice_deletelist"
		form.submit();
	}
}

//페이징 기능(처음/끝 값을 보내준다.)
function goList(p){
	form2.page.value = p;
	form2.submit();
}
</script>
</head>
<body>
<div style="height:800px; width:90%; align-self: center">

<h3 align=center><u>공지사항 목록</u></h3><br>
<!-- 페이징 값 보내는 폼(form2) -->
<form action = "notice_selectlist" name="form2">
	<input type="hidden" name="page" value="1"></input>
</form>
<!-- 로우넘리스트 폼 -->		
<form name="form">	
	<table width="100%" align="center">
		<tr align= "center" bgcolor="silver" height= "40px">
			<th width="200px">
				<label for="td_checkAll"><input type="checkbox" id="td_checkAll" onclick="checkAll();"/> 번호</label>
			</th>
			<th>제목</th>
			<th width="200px">날짜</th>
		</tr>
		
		<c:forEach items="${list}" var="i" varStatus="status">
		
<!-- Notice_no가 필요하기 때문에 값을 받을 곳을 만들어둔다 -->
<input type="hidden" name="notice_no" value="${i.getNotice_no()}">
			
			<tr align = "center">
				<!-- 역순 가상번호 -->
				<c:set var = "pn" value = "${paging.totalRecord - ((paging.page-1) * paging.pageUnit + status.index) }"/>
				
				<td><label for="${i.getRn()}"><input type="checkbox" name="td_checkbox" id="${i.getRn()}" value="${ i.getNotice_no()}"> ${pn}</label></td>
				<td><a href="${pageContext.request.contextPath}/admin/notice_select?notice_no=${ i.getNotice_no()}">${i.getNotice_title()}</a></td>
				<td>${i.getNotice_date()}</td>
			</tr>
		</c:forEach>
</table><br>

<button style="float:right;" type="button" onclick="td_delete()">삭제</button>	
<a href="${pageContext.request.contextPath}/admin/notice_insert_form"><button style="float:right;" type="button" >공지사항 등록</button></a><br>
</form>

<!-- 페이징버튼 -->
<div align="center">
<my:paging_joon paging="${paging}" jsfunc="goList"/>
</div>

</div>
</body>
</html>
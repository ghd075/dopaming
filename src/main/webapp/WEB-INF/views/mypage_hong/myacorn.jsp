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
#spann {
	float: right;
}
.trashing {
	padding: 6px 12px;
    font-size: 14px;
    font-weight: 400;
    line-height: 1;
    color: #555;
    text-align: center;
    background-color: #eee;
    border: 1px solid #ccc;
    border-radius: 4px;
    white-space: nowrap;
    vertical-align: middle;
    display: table-cell;
    border-collapse: separate;
}

.title_c {
	height: 35px;
	border: 3px solid #fff;
	border-radius: 10px;
	text-align: center;
	vertical-align: middle;
	background-color: #BDBDBD; 
	color: #000;
	font-weight: bold;
	display: table-cell; 	
}

.title_c:focus {
	outline: none;
}
input[name=seqs]
{
  display: inline-block;
}
th, td {
  text-align: center;
}
</style>
<script>
	//페이징 기능
	function go_page(p) {
		searchFrm.page.value = p;
		searchFrm.submit();
	}
	//전체선택
	function checkAll(){
	      if( $("#td_checkAll").is(':checked') ){
	        $("input[name=seqs]").prop("checked", true);
	      }else{
	        $("input[name=seqs]").prop("checked", false);
	      }
	}
	//선택삭제 기능
	function td_delete(){
		//체크박스 입력 체크
		var chk = document.getElementsByName("seqs"); //태그찾기
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
			acornFrm.action = "${pageContext.request.contextPath}/mypage/acorn_delete"
			acornFrm.submit();
		}
	}
</script>
</head>
<body>
	<h1 style="text-align: center;">아콘사용내역</h1>
	<hr>
	<form name="acornFrm">
		<span id="spann">
			<div class="input-group">
				<span class="trashing" style="font-weight: bold;">
					남은아콘
				</span>
				<input type="text" class="title_c" value="${acorn}" readonly="readonly">
				<button class="btn btn-danger btn-sm" type="button" onclick="td_delete()">삭제</button>
			</div>
		</span>
 		<table class="table table-striped table-hover">
 			<thead>
 				<tr>
	 				<th><input type="checkbox" id="td_checkAll" onclick="checkAll();"/></th>	
	 				<th>날짜</th>
	 				<th>아콘</th>
	 				<th>사용내역</th>
 				</tr>
 			</thead>
 			<tbody>
 			<c:choose>
 			 	<c:when test="${fn:length(list) == 0}">
 					<tr>
 						<td colspan="4" align="center">
 							충전/사용내역이 없습니다.
 						</td>
 					</tr>
 				</c:when>
 				<c:otherwise>
		 			<c:forEach items="${list}" var="acorn">
		 				<tr>
		 					<c:set var="content" value="${acorn.acorn_content}"></c:set>
		 					<td><input type="checkbox" name="seqs" value="${acorn.acorn_no}"></td>
		 					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${acorn.acorn_date}"/></td>
		 					<c:if test="${fn:contains(content, '배당') or fn:contains(content, '회원가입') or fn:contains(content, '충전')}">
		 						<td style="color: blue;">${acorn.acorn_point}</td>
		 					</c:if>
		 					<c:if test="${fn:contains(content, '다운로드')}">
		 						<td style="color: red;">-${acorn.acorn_point}</td>
		 					</c:if>
		 					<td>${acorn.acorn_content}</td>
		 				</tr>
		 			</c:forEach>
 				</c:otherwise>
 			</c:choose>
 			</tbody>
 		</table>
 		<div align="center">
			<my:paging paging="${paging}"/>
		</div>
	</form>
	<div align="right">
		<form name="searchFrm" method="get">
			<input type="hidden" name="page" value="1"> 
			사용내역 : <input name="searchKeyword" value="${myAcornVO.searchKeyword}" />
			<button class="btn btn-info" type="submit">검색</button>
		</form>
	</div>
</body>
</html>
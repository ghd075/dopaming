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
		uploadFrm.page.value = p;
		uploadFrm.submit();
	}
	//전체선택
	function checkAll(){
	      if( $("#td_checkAll").is(':checked') ){
	        $("input[name=seqs]:enabled").prop("checked", true);
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
			uploadFrm.action = "${pageContext.request.contextPath}/mypage/upload_delete"
			uploadFrm.submit();
		}
	}
</script>
</head>
<body>
	<h1 style="text-align: center;">업로드관리</h1>
	<hr>
	<span id="spann">
		<button class="btn btn-danger" type="button" onclick="td_delete()">삭제</button>
	</span>
	<form name="uploadFrm">
 		<table class="table table-striped table-hover">
 			<thead>
 				<tr>
	 				<td><input type="checkbox" id="td_checkAll" onclick="checkAll();"/></th>	
	 				<th>게시판번호</th>
	 				<th>제목</th>
	 				<th>종류</th>
	 				<th>용량(단위: MB)</th>
	 				<th>등록일</th>	
 				</tr>
 			</thead>
 			<tbody>
 			<c:choose>
 				<c:when test="${fn:length(list) == 0}">
 					<tr>
 						<td colspan="6" align="center">
 							판매내역이 없습니다.
 						</td>
 					</tr>
 				</c:when>
 				<c:otherwise>
		 			<c:forEach items="${list}" var="upload">
		 				<tr>
		 					<td>
		 						<input type="checkbox" name="seqs" value="${upload.board_no}" <c:if test="${upload.down_count > 0}">disabled="disabled"</c:if>>
		 					</td>
		 					<td>${upload.board_no}</td>
		 					<td><a href="${pageContext.request.contextPath }/filepost?board_no=${upload.board_no}&member_id=${upload.member_id}">
		 					${upload.board_title}</a></td>
		 					<td>${upload.category_big}</td>
		 					<td><fmt:formatNumber pattern="0.00" value="${upload.file_storage}"/>MB</td>
		 					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${upload.upload_date}"/></td>
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
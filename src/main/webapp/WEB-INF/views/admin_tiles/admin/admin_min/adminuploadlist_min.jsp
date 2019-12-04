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
 	padding: 10px;
	margin: 0px;
}

.pagination li {
    display: inline-block; /* 페이징 가로효과 */
	padding: 10px;
}
td, th {
   border: 1px solid #dddddd;
   text-align: left;
   padding: 8px;
   text-align: center;
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
		<h1 style="text-align: center;">업로드한 리스트 뷰</h1>
		<hr>
		<span id="spann">
		<div>
			<form name="searchFrm" method="get">
				<input type="hidden" name="page" value="1"> 
				아이디:<input name="searchKeyword" value="${boardListVO_min.searchKeyword}" />
				<button class="btn btn-info" type="submit">검색</button>
			<script>
				searchFrm.searchKeyword.value="${uploadListVO_min.searchKeyword}";
			</script>
			</form>
		</div>
		</span>
		<div>
			<table class="table table-striped table-hover">
			<thead>
				<tr >
					<td style="width: 110px">게시판번호<br>(내림차순)</td>
					<td>아이디</td>
					<td>대분류</td>
					<td>소분류</td>
					<td>제목</td>
					<td>파일이름</td>
					<td>파일용량</td>
				<tr>
			</thead>
			<tbody>
					<c:forEach items="${uploadList}" var="list1">
						<!-- 등급관리 전체조회 -->
						<tr>
							<td>${list1.board_no }</td>
							<td>${list1.member_id }</td>
							<td>${list1.category_big }</td>
							<td>${list1.category_small }</td>
							<td>${list1.board_title }</td>
							<td><a href="/dopaming/filepost?board_no=${list1.board_no}">${list1.file_name }</a></td>
							<td>${list1.file_storage }MB</td>
						</tr>
					</c:forEach>
			</tbody>
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
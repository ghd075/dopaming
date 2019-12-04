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

	//등급수정기능
	function grade_update() {
		//체크박스 입력 체크
		var chk = document.getElementsByName("td_checkbox");//태그찾기
		var cnt = 0;
		for (i = 0; i < chk.length; i++) {
			if (chk[i].checked == true) {
				cnt++
			}
		}
		if (cnt == 0) {
			alert("등급을 수정할 아이디를 선택해주세요");
			return false;
		}

		var grade2 = form.member_grade2.selectedIndex;
		if (grade2 == 0) {
			alert("선택!!");
			return;
		}
		form.action = "grade_update"
		form.submit();
	}
</script>
</head>
<body>
	<!-- select + 검색 -->
	<h1 style="text-align: center;">등급관리</h1>
	<div class="p-3 bg-gray-200">
	<h3> <span style="color:black; font-weight: bold;">등급조건</span></h3>
	<h5 style="font-weight: bold;">
	브론즈도토리(기본)&nbsp; 누적금액 <span style="color:black;">1만원↓</span> + 업로드횟수 <span style="color:black; ">5회↓</span><br>
	실버도토리&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	누적금액 <span style="color:black">1만원↑</span> ~ <span style="color:black;">15만원↓</span> + 업로드횟수 <span style="color:black;">5회↑</span> ~ <span style="color:black; ">50회↓</span><br>
	골드도토리&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	누적금액 <span style="color:black;">15만원↑</span> ~ <span style="color:black; ">50만원↓</span> + 업로드횟수 <span style="color:black; ">50회↑</span> ~ <span style="color:black; ">100회↓</span><br>
	다이아도토리&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	누적금액 <span style="color:black;">50만원↑</span> + 업로드횟수 <span style="color:black;">100회↑</span>
	</h5>
	</B>
	</div>
	<hr>

	<form name="searchFrm" method="get">
		<span id="spann"> <input type="hidden" name="page" value="1">
			<select name="member_grade">
				<option value="">전체
				<option value="g4">다이아도토리
				<option value="g3">골드도토리
				<option value="g2">실버도토리
				<option value="g1">브론즈도토리
		</select> <script>
			searchFrm.member_grade.value = '${gradeVO_min.member_grade}';
		</script>
			<button class="btn btn-info">검색</button>
		</span>
	</form>

	<!-- 테이블 -->
	<form name="form">
		<table class="table table-striped table-hover" style="text-align: center;">
			<thead>
				<tr>
					<th></th>
					<th>아이디(오름차순)</th>
					<th>등급(내림차순)</th>
					<th>Total 충전금액(내림차순)</th>
					<th>총 업로드횟수</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach items="${classList}" var="classs">				
				<!-- 등급관리 전체조회 -->
				<tr>
					<td><input type="checkbox" name="td_checkbox"
						value="${classs.member_id }"></td>
					<td>${classs.member_id }</td>
					<td>${classs.grade_kor }</td>
					<td>${classs.acron }원</td>
					<td>${classs.upload_count }개</td>
				</tr>				
				</c:forEach>
			<tbody>
		</table>
		<div align="center">
			<my:paging paging="${paging}" />
		</div>
		<div align="right">
			<select name="member_grade2">
				<option value="">선택
				<option value="g4">다이아도토리
				<option value="g3">골드도토리
				<option value="g2">실버도토리
				<option value="g1">브론즈도토리
			</select>
			<button class="btn btn-primary" onclick="grade_update()">등급수정</button>
		</div>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page session="false"%>
<!-- 웹페이지언어 설정 -->
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<meta charset="utf-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/msg.js"></script>
<title>도파밍 프로젝트</title>

<!-- Bootstrap core CSS -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<style>
.navber-right {
	float: right;
}

.jumbotron {
	clear: both;
	margin: 0px auto 0px auto;
	background-image: url(./resources/images/logo2.jpg);
	background-size: 100px;
}

@media ( min-width : 768px) {
	.container .jumbotron {
		width: 100%;
	}
}

@media ( min-width : 992px) {
	.container .jumbotron {
		width: 100%;
	}
}

.carousel-caption {
	position: absolute;
	right: 15%;
	left: 15%;
	z-index: 10;
	padding-top: 20px;
	padding-bottom: 20px;
}

.carousel-inner {
	display: block;
	max-width: 100%;
	height: 300px;
}

.navbar {
	margin: 0px auto 0px auto;
	width: 100%;
}

.genre_rank {
	max-width: 100%;
	height: 82px;
}

div.footer {
	clear: both;
	width: 100%;
	float: left;
	background-color: #1f2022;
	text-align: center;
	padding-top: 15px;
	padding-bottom: 15px;
	color: #E0E0E0;
}

</style>

<script>
<!-- 로그인이 안되었을 경우(고객센터)-joon -->
var id = "${sessionScope.Id}"
	
	$(function(){ //J쿼리형태
		$(".complain").click(function(){
			if( id == ""){
				alert("로그인을 해주세요");
				return false;
			}
		})
	})

</script>

</head>
<body>

	<div class="container-fluid">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="container-fluid">
		<div class="col-md-2">
			<!-- 사이드 바 메뉴-->
			<div class="panel panel-info ">
				<div class="panel-heading">
					<h3 class="panel-title">고객센터</h3>
				</div>
				<!-- 사이드바 메뉴목록1 -->
				<ul class="list-group">
					<li class="list-group-item"><a href="notice_selectlist_nomal">공지사항</a></li>
					<li class="list-group-item complain"><a href="complain_selectlist_nomal?complain_type=qna">Q&A</a></li>
					<li class="list-group-item complain"><a href="complain_selectlist_nomal?complain_type=신고">신고사항</a></li>
					<li class="list-group-item complain"><a href="complain_selectlist_nomal?complain_type=건의">건의사항</a></li>
					<li class="list-group-item complain"><a href="complain_insert_form">글쓰기</a></li>
				</ul>
			</div>
			</div>
		<div class="container-fluid" style="width:80%; float:right;">
		<tiles:insertAttribute name="content"/>
		</div>
	</div>
	<div class="container-fluid">
		<tiles:insertAttribute name="modal" />
	</div>
	<div class="container-fluid">
		<tiles:insertAttribute name="footer" />
	</div>

</body>
</html>
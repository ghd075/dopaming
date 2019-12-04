<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page session="false"%>
<!-- 웹페이지언어 설정 -->
<html>
<head>
<title>도파밍 프로젝트</title>
<style>
.rank_num {
	font-size: 18pt;
	align: left;
}

.rank {
	font-size: 15pt;
	align: center;
}

.slide {
	width: 100%;
	padding : 5px 15%;
}
.carousel-inner>.item>a>img{
	height: 100%;
	
}
.container_center{
}

.slidename{
color: black;
font-size : 25pt;
}


</style>
</head>

<body>
	<div class="container col-sm-8 container_center">
		<div class="container col-sm-12 container_center1">
			<div id="genreCarousel" class="carousel slide" data-ride="carousel"
				data-interval="3000">
				<ol class="carousel-indicators">
					<li data-target="#genreCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#genreCarousel" data-slide-to="1" class="active"></li>
					<li data-target="#genreCarousel" data-slide-to="2" class="active"></li>
					<li data-target="#genreCarousel" data-slide-to="3" class="active"></li>
					<li data-target="#genreCarousel" data-slide-to="4" class="active"></li>
					<li data-target="#genreCarousel" data-slide-to="5" class="active"></li>
					<li data-target="#genreCarousel" data-slide-to="6" class="active"></li>
				</ol>

				<div class="carousel-inner" role="listbox">
					<c:forEach items="${slide1}" var="s" varStatus="status">
						<div class="item <c:if test="${status.index eq 0}">active</c:if>">
							<a
								href="filepost?board_no=${s.BOARD_NO}&member_id=${s.MEMBER_ID}"><img class="slide"
								src="${s.BOARD_IMG}" alt="로고1"></a>
							<div class="carousel-caption"></div>
						</div>
					</c:forEach>
				</div>

				<!-- 					<div class="item">
						<img src="./resources/images/logo2.jpg" alt="로고2">
						<div class="carousel-caption"></div>
					</div> -->

				<a class="left carousel-control" href="#genreCarousel" role="button"
					data-slide="prev"><span class="slidename"><b>영     화</b></span> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#genreCarousel"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>

		</div>
		<div class="container col-sm-12">
			<div id="genreCarousel2" class="carousel slide" data-ride="carousel"
				data-interval="3000">
				<ol class="carousel-indicators">
					<li data-target="#genreCarousel2" data-slide-to="0" class="active"></li>
					<li data-target="#genreCarousel2" data-slide-to="1" class="active"></li>
					<li data-target="#genreCarousel2" data-slide-to="2" class="active"></li>
					<li data-target="#genreCarousel2" data-slide-to="3" class="active"></li>
					<li data-target="#genreCarousel2" data-slide-to="4" class="active"></li>
					<li data-target="#genreCarousel2" data-slide-to="5" class="active"></li>
					<li data-target="#genreCarousel2" data-slide-to="6" class="active"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<c:forEach items="${slide2}" var="s" varStatus="status">
						<div class="item <c:if test="${status.index eq 0}">active</c:if>">
							<a
								href="filepost?board_no=${s.BOARD_NO}&member_id=${s.MEMBER_ID}"><img class="slide"
								src="${s.BOARD_IMG}" alt="로고1"></a>
							<div class="carousel-caption"></div>
						</div>
					</c:forEach>
				</div>

				<a class="left carousel-control" href="#genreCarousel2"
					role="button" data-slide="prev">
					<span class="slidename"><b>드 라 마</b></span>
					 <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#genreCarousel2"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<div class="container col-sm-12">
			<div id="genreCarousel3" class="carousel slide" data-ride="carousel"
				data-interval="3000">
				<ol class="carousel-indicators">
					<li data-target="#genreCarousel3" data-slide-to="0" class="active"></li>
					<li data-target="#genreCarousel3" data-slide-to="1" class="active"></li>
					<li data-target="#genreCarousel3" data-slide-to="2" class="active"></li>
					<li data-target="#genreCarousel3" data-slide-to="3" class="active"></li>
					<li data-target="#genreCarousel3" data-slide-to="4" class="active"></li>
					<li data-target="#genreCarousel3" data-slide-to="5" class="active"></li>
					<li data-target="#genreCarousel3" data-slide-to="6" class="active"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<c:forEach items="${slide3}" var="s" varStatus="status">
						<div class="item <c:if test="${status.index eq 0}">active</c:if>">
							<a
								href="filepost?board_no=${s.BOARD_NO}&member_id=${s.MEMBER_ID}"><img class="slide"
								src="${s.BOARD_IMG}" alt="로고1"></a>
							<div class="carousel-caption"></div>
						</div>
					</c:forEach>
				</div>

				<a class="left carousel-control" href="#genreCarousel3"
					role="button" data-slide="prev"><span class="slidename"><b>동 영 상</b></span> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#genreCarousel3"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		
		<div class="container col-sm-12">
			<div id="genreCarousel4" class="carousel slide" data-ride="carousel"
				data-interval="3000">
				<ol class="carousel-indicators">
					<li data-target="#genreCarousel4" data-slide-to="0" class="active"></li>
					<li data-target="#genreCarousel4" data-slide-to="1" class="active"></li>
					<li data-target="#genreCarousel4" data-slide-to="2" class="active"></li>
					<li data-target="#genreCarousel4" data-slide-to="3" class="active"></li>
					<li data-target="#genreCarousel4" data-slide-to="4" class="active"></li>
					<li data-target="#genreCarousel4" data-slide-to="5" class="active"></li>
					<li data-target="#genreCarousel4" data-slide-to="6" class="active"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<c:forEach items="${slide4}" var="s" varStatus="status">
						<div class="item <c:if test="${status.index eq 0}">active</c:if>">
							<a
								href="filepost?board_no=${s.BOARD_NO}&member_id=${s.MEMBER_ID}"><img class="slide"
								src="${s.BOARD_IMG}" alt="로고1"></a>
							<div class="carousel-caption"></div>
						</div>
					</c:forEach>
				</div>
				<a class="left carousel-control" href="#genreCarousel4"
					role="button" data-slide="prev"><span class="slidename"><b>음     악</b></span> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#genreCarousel4"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>

		</div>
	</div>

	<div class="container col-sm-4">
		<img src="./resources/images/logo1.png" class="img-responsive"
			alt="로고1" />

		<form action="mdview" name="searchFrm" method="get">
			<input type="hidden" name="page" value="1"> <input
				type="text" name="searchKeyword" width="200%" placeholder="전체검색" />
			<button class="btn btn-success btn-sm">검색</button>
		</form>

		<!-- side menu (link) -->
		<h2>Dopaming BEST!</h2>
		<ul class="list-group">
			<c:forEach items="${list}" var="main" end="9" step="1"
				varStatus="status">
				<li class="list-group-item list-group-item-action genre_rank">
				<table>
				<tr>
				<td style="width:100px">
				<font class="rank_num">${main.rn}위&emsp;&emsp;</font>
				</td>
				<td>
				<a href="filepost?board_no=${main.board_no}&member_id=${main.member_id}">
						<font class="rank">${main.board_title}</font>
				</td>
				</tr>
				</table>
						<%-- <font class="rank_num">${main.rn}위</font> --%>
						
				</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
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

.active {
	color: red;
}
</style>
<script type="text/javascript">
	function goList(p) {
		searchFrm.page.value = p;
		searchFrm.submit();
	}
</script>

<!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      google.charts.load('current', {'packages':['corechart']});

      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = new google.visualization.DataTable();
        var options = {'title':'회원등급 분포',  'width':800,  'height':500  };
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        //ajax 호출
       $.getJSON( { url :"${pageContext.request.contextPath }/admin/chartMember",
    	   success : function(list){
		        data.addColumn('string', '등급');
		        data.addColumn('number', '회원수');		        
		        var arr = [];
		        for(i=0; i<list.length;i++ ){
		        	arr.push([ list[i].grade_kor, list[i].cnt]);
		        }		        
		        data.addRows(arr);

		        chart.draw(data, options);
		    }
		});
      }
    </script>
</head>
<body>
	<div>
		<h1 style="text-align: center;">사용통계</h1>
		<hr>
		<span id="spann">
			<div>
				<form name="searchFrm" method="get">
				<input type="hidden" name="page" value="1">
					아이디: <input
						name="searchKeyword" value="${ChartVO.searchKeyword}" />
					<button class="btn btn-info" type="submit">검색</button>
				</form>
			</div>
		</span>
		<div>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<td>아이디</td>
							<td>다운로드횟수</td>
							<td>업로드횟수</td>
							<td>업로드용량</td>
							<td>등급</td>
						<tr>
					<thead>
					<tbody>
						<c:forEach items="${list}" var="list1">
							<tr>
								<td>${list1.MEMBER_ID}</td>
								<td>${list1.DOWN_COUNT}</td>
								<td>${list1.UPLOAD_COUNT}</td>
								<td>${list1.UPLOAD_STORAGE}M</td>
								<td>${list1.MEMBER_GRADE}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		<div align="center">
			<my:paging_joon paging="${paging}" jsfunc="goList" />
		</div>
		<br> <br> <br>
	</div>
	
	<div id="chart_div" align ="center"></div>
</body>
</html>
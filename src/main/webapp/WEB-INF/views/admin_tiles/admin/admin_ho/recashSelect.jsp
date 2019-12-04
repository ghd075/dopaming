<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

table {
    width: 100%;
    border-top: 1px solid #BDBDBD;
    border-collapse: collapse;
  }

th, td {
    border-bottom: 1px solid #BDBDBD;
    text-align: center;
    padding: 50px;
  }
  
.btn_cal {
	border-radius: 0px;
}

.sunday_bar {
	color: red;
}


.saturday_bar {
	color: blue;
}

.sunday {
	color: red;
}

.sunday:hover {
	background: #E6E6E6;
	cursor: pointer;
}

.saturday {
	color: blue;
}

.saturday:hover {
	background: #E6E6E6;
	cursor: pointer;
}

.general {
	color: #000;
}

.general:hover {
	background: #E6E6E6;
	cursor: pointer;
}
</style>
<script>
	window.onload = function() {
		showCal.onclick = function() {			
			var array = [ "일", "월", "화", "수", "목", "금", "토" ];
			var last_day = [ "31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31" ];
			var sel = new Date(y.value, m.value - 1, 1);
			var calendar = "";
			var d_of_w = "";
			var count = 1;
			var gap = sel.getDay();
			var blank = 0;
			var numb = 1;

			
			d_of_w += "<tr>";
			for (td = 0; td < 7; td++) { //요일을 쓰기위해 따로 반복문 써서 만들어줌
				if (td == 0) {
					d_of_w += "<td class='sunday_bar'>" + array[td] + "</td>";
				} else if (td == 6) {
					d_of_w += "<td class='saturday_bar'>" + array[td] + "</td>";
				} else {
					d_of_w += "<td >" + array[td] + "</td>";
				}
			}
			d_of_w += "</tr>";
			week.innerHTML = "<table align=center>" + d_of_w + "</table>";

			if (((y.value % 4 == 0) && (y.value % 100 != 0)) || (y.value % 400 == 0)) {
				last_day[1] = 29; //윤달이면 2월의 마지막날을 29일로 셋팅
			} else {
				last_day[1] = 28;
			}

			for (tr = 1; tr <= last_day[m.value - 1]; tr++) {
				if (count % 7 == 1) {
					calendar += "<tr>";
				}
				if (gap > blank) {
					for (r = 0; r < gap; r++) { //공백주기 위해 공백만큼 빈칸 반복
						calendar += "<td>" + "</td>";
						count++;
						blank++;
					}
				}
				if (count % 7 == 1) {
					calendar += "<td class='sunday' onclick='thisDate("+numb+")'>" + numb + "</td>";
				} else if (count % 7 == 0) {
					calendar += "<td class='saturday' onclick='thisDate("+numb+")'>" + numb + "</td>";
				} else {
					calendar += "<td class='general' onclick='thisDate("+numb+")'>" + numb + "</td>";
				}
				count++;
				numb++;
				if (count % 7 == 1) { //공백포함 카운트숫자가 7로 나눴을때 나머지가 1이면 줄바꾸기 위해 tr 닫아줌
					calendar += "</tr>";
				}
			}
			con.innerHTML = "<table align=center>" + calendar + "</table>";
		}
	}
	
	function pad(n, width) {
		  n = n + '';
		  return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
	}
	
	function thisDate(day) {
		var months = pad(m.value, 2);
		var days = pad(day,2);
		
		var date = y.value + '/' + months + '/' + days;
		
		window.open('${pageContext.request.contextPath}/admin/myReCash?reg_date=' + date, 'list', 'width=1000,height=750')
	}
</script>
</head>
<body>
	<h5>&nbspYear</h5>
	<select class="form-control" id="y">
		<c:forEach var="year" begin="2019" end="2030" step="1">
			<option>${year}</option>
		</c:forEach>
	</select>
	
	<h5>&nbspMonth</h5>
	<select class="form-control" id="m">
		<c:forEach var="month" begin="1" end="12" step="1">
			<option>${month}</option>
		</c:forEach>
	</select>
	
	<input type="button" class="btn btn-primary btn_cal" id="showCal" value="달력보기">
		<div id="week"></div>
		<div id="con"></div>
</body>
</html>
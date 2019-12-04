<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<body>
<h1 style="text-align: center;">결제코드누를시 즉시 환불</h1> <hr>
	<table class="table table-striped table-hover" style="text-align: center;">
		<thead>
			<tr>
				<td>아이디</td>
				<td>충전금액</td>
				<td>충전날짜</td>
				<td>결제코드</td>
			<tr>
		<thead>
		<tbody>
			<c:forEach items="${acorndetail}" var="list">
				<tr>
					<td>${list.member_id }</td>
					<td>${list.acorn_charge }</td>
					<td>${list.acorn_date }</td>
					<td><button><a href="./acornreturn?pay_code=${list.pay_code}" onclick="alert('환불시켰습니다!')">즉시 환불하기</a></button></td>				
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
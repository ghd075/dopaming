<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/slider.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/resources/js/register.js"></script>
</head>
<body>
	<div class="container">
		<div class="joinmodal_resize">
			<div class="loginmodal_body">
				<div class="joinmodal_title">
					<h1>회원가입</h1>
				</div>
				<form id="joinForm" action="${pageContext.request.contextPath }/register" method="post">
					<div class="form-group input-group">
						<span class="input-group-addon"><i class="fa fa-check"></i></span>
						<input type="text" id="member_id" name="member_id" class="form-control" placeholder="아이디">
					</div>
					<span id="id_check" style="color:red;text-align: left;"></span>
					
					<div class="form-group input-group">
						<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						<input type="password" id="member_password" name="member_password" class="form-control" placeholder="비밀번호">
					</div>
					
					<div class="form-group input-group">
						<span class="input-group-addon"><i class="fa fa-lock"></i></span>
						<input type="password" id="check_passowrd" class="form-control" placeholder="비밀번호 확인">					
					</div>
					
					<div class="form-group input-group">
						<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
						<input type="eamil" id="member_email" name="member_email" class="form-control" placeholder="hong@naver.com">
					</div>
					<span id="email_check" style="color:red;text-align: left;"></span>
					
					<div style="text-align: center;">
						<button class="btn btn-info" id="joinBtn" type="submit">가입</button>		
		    		    <button class="btn btn-primary" type="button" onclick="history.go(-1);">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블랙으로 넘기기</title>
<script>
function black_insert(){
	//체크박스 입력 체크
	if(confirm("블랙리스트에 등록할까요?")){
		//내용없을땐 경고띄움
		if($("textarea[name=list_content]").val()==""){
			alert("내용을 적어주세요");
			return false;
		
		}else{
		//내용있을때 블랙에 데이터 넘김
		form.action = "${pageContext.request.contextPath }/admin/blackInsert"
		form.submit();
		}
	}	
	else{
		return true;
	}
}	
</script>
</head>
<body>
<h3>블랙리스트로 변경</h3>
<div >
<form name="form" method="post">
	이름<input type="text" name="member_id" value="${normal.member_id}" readonly/><Br><br>
	<!-- readonly(VO에 값은 들어가지만 수정은 불가능(disable, VO에 값이 안들어가서 아예 못함 -->
	내용<br>
	<div align="center">
	<textarea name="list_content" cols="90%" rows="10">${normal.list_content}</textarea>
	<Br>
	<span style="float:right">
	<button class="btn btn-primary" onclick="black_insert()">등록</button> 
	</span>     
	</div>        
</form>
</div>
</body>
</html>
<%@page import="com.dopaming.www.common.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<script src="./resources/ckeditor/ckeditor.js"></script>
<title>자료 업로드</title>
<!-- <script src="//cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script> -->
<style>
.cen_table {
	margin: 0 auto;
}

table {
	width: 500px;
}

td {
	padding: 10px;
	border: 1px solid #666666;
	background: #A9F5D0;
}

th {
	padding: 10px;
	border: 1px solid #666666;
	color: #fff;
}
/* Bootstrap 수정 */
.table>thead {
	background-color: #b3c6ff;
}

.no_border {
	border: 0px;
	background: #FFFFFF;
}

.write_on {
	float: right;
}

textarea {
	margin-left: 85px;
}

.filebox label {
	display: inline-block;
	padding: .5em .75em;
	color: #999;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #fdfdfd;
	cursor: pointer;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
}



</style>
</head>
<body>
	<div class="cen_form">
		<form class="container" name="upload_frm" action="request_upload" method="get" id="uploadForm"
			enctype="multipart/form-data">
			<table class="cen_table table table-striped table-bordered">
				<tr>
					<td>제목<br> <input class="chk1" name="boardTitle" size="20" value="${BoardUpSel.board_title}" /></td>
					<td>회원아이디<br>
					<input type="hidden" name="board_no" value="${filePost.board_no}"/>
					<input type="hidden" name="memberId" value="${sessionScope.Id}"/>					
					<label>${sessionScope.Id}</label>
					</td>
					<td>업로드날짜<br><label><%=DateUtil.tDateFormat()%></label></td>
				</tr>
				<tr>
					<td colspan="2">
					그전 설정 값 : ${BoardUpSel.category_big}/ ${BoardUpSel.category_small}
					<select name="categoryBig" class="form-control">
							<option value="영화">영화</option>
							<option value="드라마">드라마</option>
							<option value="동영상">동영상</option>
							<option value="음악">음악</option>
					</select> <select name="categorySmall" class="form-control">	
							<option value="최신">최신</option>
							<option value="국내">국내</option>
							<option value="외국">외국</option>							
					</select></td>
					<td colspan="2">각 파일 아콘<br> <input class="chk2" name="BoardAcorn" value="${BoardUpSel.board_acorn}" />
					</td>
				</tr>
				<tr>
					<td class="no_border" colspan="4">
						<div class="row justify-content-md-center">
							<div style="margin-bottom: 30px">
								<div class="input-group cen_table">
									<textarea class="form-control" name="boardImg" id="p_content">${BoardUpSel.board_img}</textarea>
									<script>
  								CKEDITOR.replace('p_content', {
  									uiColor:'#9AB8F3',
  									filebrowserUploadUrl:'<%=request.getContextPath()%>/resources/ckeditor/fileUpload.jsp'
														});
									</script>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<tr>					
					<td colspan="4">						
						<textarea rows="5" cols="130" name="boardContent" class="chk3" placeholder="게시글 내용을 입력하시오">${BoardUpSel.board_content}</textarea><br>						
						<button type="button" class="btn btn-danger btn-danger pull-right" onclick="checkUpload()">수정 하기</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script>
    //값 확인
    function checkUpload(){
   	 	if($('.chk1').val()=="" || $('.chk2').val()=="" || CKEDITOR.instances.p_content.getData()=="" 
   			 || $('.chk3').val()=="" ){
   		 	alert("제목, 게시글 아콘, 편집창, 게시글내용, 파일을 모두 입력하세요");
   	 	} else {
   	 		upload_frm.action="request_update";
   	 		upload_frm.submit();
   	 	}        			 
    }   
    </script>
</body>
</html>
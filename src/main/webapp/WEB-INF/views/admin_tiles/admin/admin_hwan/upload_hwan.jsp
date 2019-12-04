<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap core CSS -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="./resources/fontawesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="./resources/js/ie-emulation-modes-warning.js"></script>
<script src="./resources/ckeditor/ckeditor.js"></script>
<title>자료 업로드</title>
<!-- <script src="//cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script> -->
</head>
<body>
<div class="container col-sm-8 col-md-8">
<form>
  <div class="form-group">
    <label for="title">글 제목 </label>
    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요">
  </div>
  <div class="form-group">
    <label for="money">올릴 아콘</label>
    <input type="text" class="form-control" id="money" placeholder="올릴 아콘을 입력하세요">
  </div>
  <div class="form-group">
  		<div class="container">
  			<div class="content" style="width: 70%">
  				<div class="row justify-content-md-center">
  					<div class="col-sm-9">
  						<div class="input-group-prepend">
  							<label class="input-group-text">제목</label>  							  						
  						</div>
  						<input type="text" class="form-control">
  					</div>
  					<div class="col-sm-3">
  						<div class="input-group mb-3">
  							<select class="custom-select" id="inputGroupSelect03">
  								<option selected>분류</option>
  								<option value="1">영화</option>
  								<option value="2">드라마</option>
  								<option value="3">동영상</option>
  								<option value="4">음악</option>
  							</select>  						
  						</div>
  					</div>
  				</div>
  				<hr>
  				<div class="row justify-content-md-center">
  					<div class="col_c" style="margin-bottom:30px">
  						<div class="input-group">
  							<textarea class="form-control" id="p_content"></textarea>
  							<script>
  								CKEDITOR.replace('p_content', {height:500});
  							</script>  							  							
  						</div>
  					</div>
  				</div>
<!--   				<div class="row justify-content-md-center">
  					<div class="input-group mb-3">
  						<span class="input-group-text">GitHub</span>
  					</div>
  					<input type="text" class="form-control">  				
  				</div> -->
  				<div class="row justify-content-md-center">
  					<div class="input-group mb-3">
  							<div class="input-group-prepend">
  								<span class="input-group-text" id="inputGroupFileAddon01">파일 썸네일</span>
  							</div>
  							<div class="custom-file">
  								&nbsp;<input type="file" class="form-control-file" id="exampleFormControlFile1">  								
  							</div>
  					</div>
  				</div>
  			</div>
  		</div>  	
  </div>
  <div class="form-group">
    <label for="S_file">파일 찾아보기</label>
    <input type="file" id="S_file">
    <p class="help-block">여기에 블록레벨 도움말 예제</p>
  </div>
  <div class="checkbox">
    <label>
      <input type="checkbox"> 입력을 기억합니다
    </label>
  </div>
  <button type="submit" class="btn btn-default">제출</button>
</form>
</div>
</body>
</html>
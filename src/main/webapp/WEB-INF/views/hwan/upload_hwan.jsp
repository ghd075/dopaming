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
.chk3{
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
		<form class="container" name="upload_frm" action="request_upload" method="post" id="uploadForm"
			enctype="multipart/form-data">
			<table class="cen_table table table-striped table-bordered">
				<tr>
					<td>제목<br> <input class="chk1" name="boardTitle" size="20" placeholder="제목입력" /></td>
					<td>회원아이디<br>
					<input type="hidden" name="memberId" value="${sessionScope.Id}"/>					
					<label>${sessionScope.Id}</label>
					</td>					
					<td>파일용량(BYTE 단위)<br>
    				<label id="stg" name="fileName"></label></td>
					<td>업로드날짜<br><label><%=DateUtil.tDateFormat()%></label></td>
				</tr>
				<tr>
					<td colspan="2"><select name="categoryBig" class="form-control">
							<option value="영화">영화</option>
							<option value="드라마">드라마</option>
							<option value="동영상">동영상</option>
							<option value="음악">음악</option>
					</select> <select name="categorySmall" class="form-control">	
							<option value="최신">최신</option>
							<option value="국내">국내</option>
							<option value="외국">외국</option>							
					</select></td>
					<td colspan="2">각 파일 아콘<br> <input class="chk2" name="BoardAcorn" placeholder="아콘값 입력" />
					</td>
				</tr>
				<tr>
					<td class="no_border" colspan="4">
						<div class="row justify-content-md-center">
							<div style="margin-bottom: 30px">
								<div class="input-group cen_table">
									<textarea class="form-control" name="boardImg" id="p_content"></textarea>
									<script>
  								CKEDITOR.replace('p_content', {
  									uiColor:'#9AB8F3',
  									width: '1060px',
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
						<textarea rows="5" cols="130" name="boardContent" class="chk3 center-block" placeholder="게시글 내용을 입력하시오"></textarea><br>
					</td>
				</tr>
				<tr>
					<td colspan="4">
							<label>업로드할 파일</label>																			
							<input type="file" name="fileName"
								class="afile3" />							
							<div id="afile3-list" style="border:2px solid #c9c9c9;min-height:50px" class="dropZone chk4">								
							
							</div>
					</td>
				</tr>
				<tr>					
					<td colspan="4">												
						<button type="button" class="btn btn-danger btn-danger pull-right" onclick="checkUpload()">업로드 하기</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	

	<!--CDN방식-->
	<script src="./resources/multifile-master/jquery.MultiFile.js"></script>
	<script>
    //값 확인
    function checkUpload(){
   	 	if($('.chk1').val()=="" || $('.chk2').val()=="" || CKEDITOR.instances.p_content.getData()=="" 
   			 || $('.chk3').val()=="" || $('.chk4').text()=="" ){
   		 	alert("제목, 게시글 아콘, 편집창, 게시글내용, 파일을 모두 입력하세요");
   	 	} else {
   	 		upload_frm.action="request_upload";
   	 		upload_frm.submit();
   	 	}        			 
    }    
	        $(function(){ // wait for page to load
                $('input.afile3').MultiFile({
            max: 5, 
            //업로드 최대 파일 갯수 (지정하지 않으면 무한대)
            accept: 'jpg|png|gif|mp4|mov|mp3|avi|mpg|mpeg|wmv|flv|dat|asf|asx|mpe|pdf|txt', 
            //허용할 확장자(지정하지 않으면 모든 확장자 허용)
            maxfile: 9999999999999, 
            //각 파일 최대 업로드 크기
            maxsize: 9999999999999999,  
            //전체 파일 최대 업로드 크기
            STRING: { //Multi-lingual support : 메시지 수정 가능
                remove : "제거", //추가한 파일 제거 문구, 이미태그를 사용하면 이미지사용가능
                duplicate : "$file 은 이미 선택된 파일입니다.", 
                denied : "$ext 는(은) 업로드 할수 없는 파일확장자입니다.",
                 selected:'$file 을 선택했습니다.', 
                toomuch: "업로드할 수 있는 최대크기를 초과하였습니다.($size)", 
                toomany: "업로드할 수 있는 최대 갯수는 $max개 입니다.",
                toobig: "$file 은 크기가 매우 큽니다. (max $size)"
            },
            list:"#afile3-list", 
            //파일목록을 출력할 요소 지정가능

            //각각의 이벤트에 따라 스크립 처리를 할수있다.
            /*
            ,onFileRemove: function(element, value, master_element) {
              $('#afile3-list').append('<li>onFileRemove - ' + value + '</li>')
            },
            afterFileRemove: function(element, value, master_element) {
              $('#afile3-list').append('<li>afterFileRemove - ' + value + '</li>')
            },*/
             /*onFileAppend: function(element, value, master_element) {
            	console.log(element,value,master_element);
              $('#afile3-list').append('<li name="fileName" class="afile3">'+value+ '</li>')
            }*/ 
            /*afterFileAppend: function(element, value, master_element) {
              $('#afile3-list').append('<li>afterFileAppend - ' + value + '</li>')
            },*/
            /*onFileSelect: function(element, value, master_element) {
              $('#afile3-list').append('<li>onFileSelect - ' + value + '</li>')
            },
            afterFileSelect: function(element, value, master_element) {
              $('#afile3-list').append('<li>afterFileSelect - ' + value + '</li>')
            },
            onFileInvalid: function(element, value, master_element) {
              $('#afile3-list').append('<li>onFileInvalid - ' + value + '</li>')
            },
            onFileDuplicate: function(element, value, master_element) {
              $('#afile3-list').append('<li>onFileDuplicate - ' + value + '</li>')
            },
            onFileTooMany: function(element, value, master_element) {
              $('#afile3-list').append('<li>onFileTooMany - ' + value + '</li>')
            },
            onFileTooBig: function(element, value, master_element) {
              $('#afile3-list').append('<li>onFileTooBig - ' + value + '</li>')
            },
            onFileTooMuch: function(element, value, master_element) {
              $('#afile3-list').append('<li>onFileTooMuch - ' + value + '</li>')
            }
            */
			
          });      

             // 파일 리스트 번호
             var fileIndex = 0;
             // 등록할 전체 파일 사이즈
             var totalFileSize = 0;
             // 파일 리스트
             var fileList = new Array();
             // 파일 사이즈 리스트
             var fileSizeList = new Array();
             // 등록 가능한 파일 사이즈 MB
             var uploadSize = 50;
             // 등록 가능한 총 파일 사이즈 MB
             var maxUploadSize = 500;            
             
             $(function (){
                 // 파일 드롭 다운
                 fileDropDown();
             });         

             // 파일 드롭 다운
             function fileDropDown(){
                 var dropZone = $(".dropZone");
                 //Drag기능 
                 dropZone.on('dragenter',function(e){
                     e.stopPropagation();
                     e.preventDefault();
                     // 드롭다운 영역 css
                     dropZone.css('background-color','#E3F2FC');
                 });
                 dropZone.on('dragleave',function(e){
                     e.stopPropagation();
                     e.preventDefault();
                     // 드롭다운 영역 css
                     dropZone.css('background-color','#FFFFFF');
                 });
                 dropZone.on('dragover',function(e){
                     e.stopPropagation();
                     e.preventDefault();
                     // 드롭다운 영역 css
                     dropZone.css('background-color','#E3F2FC');
                 });
                 dropZone.on('drop',function(e){
                     e.preventDefault();
                     // 드롭다운 영역 css
                     dropZone.css('background-color','#FFFFFF');
                     
                     var files = e.originalEvent.dataTransfer.files;
                     if(files != null){
                         if(files.length < 1){
                             alert("폴더 업로드 불가");
                             return;
                         }                         
                         $("[name=fileName]").last().get(0).files=files;
                         $("[name=fileName]").last().change();                         
                     }else{
                         alert("ERROR");
                     }
                 });
             }

             // 파일 선택시
             function selectFile(fileObject){
                 var files = null;

                 if(fileObject != null){
                     // 파일 Drag 이용하여 등록시
                     files = fileObject;
                 }else{
                     // 직접 파일 등록시
                     files = $('#multipaartFileList_' + fileIndex)[0].files;
                 }
                 
                 // 다중파일 등록
                 if(files != null){
                     for(var i = 0; i < files.length; i++){
                         // 파일 이름
                         var fileName = files[i].name;
                         var fileNameArr = fileName.split("\.");
                         // 확장자
                         var ext = fileNameArr[fileNameArr.length - 1];
                         // 파일 사이즈(단위 :MB)
                         var fileSize = files[i].size / 1024 / 1024;
                         
                         if($.inArray(ext, ['exe', 'bat', 'sh', 'java', 'jsp', 'html', 'js', 'css', 'xml']) >= 0){
                             // 확장자 체크
                             alert("등록 불가 확장자");
                             break;
                         }else if(fileSize > uploadSize){
                             // 파일 사이즈 체크
                             alert("용량 초과\n업로드 가능 용량 : " + uploadSize + " MB");
                             break;
                         }else{
                             // 전체 파일 사이즈
                             totalFileSize += fileSize;
                             
                             // 파일 배열에 넣기
                             fileList[fileIndex] = files[i];
                             
                             // 파일 사이즈 배열에 넣기
                             fileSizeList[fileIndex] = fileSize;

                             // 업로드 파일 목록 생성
                             addFileList(files);

                             // 파일 번호 증가
                             fileIndex++;
                         }
                     }
                 }else{
                     alert("ERROR");
                 }
             } 
             
//             function addFileList(){
//            	 var m =$('MultiFile1').MultiFile('data');
 //           	 $('dropZone').append(m.addToList(m.files.size, m.files.size, files));
  //           }

             // 업로드 파일 삭제
             function deleteFile(fIndex){
                 // 전체 파일 사이즈 수정
                 totalFileSize -= fileSizeList[fIndex];
                 
                 // 파일 배열에서 삭제
                 delete fileList[fIndex];
                 
                 // 파일 사이즈 배열 삭제
                 delete fileSizeList[fIndex];
                 
                 // 업로드 파일 테이블 목록에서 삭제
                 $("#fileTr_" + fIndex).remove();
             }

             // 파일 등록
             function uploadFile(){
                 // 등록할 파일 리스트
                 var uploadFileList = Object.keys(fileList);

                 // 파일이 있는지 체크
                 if(uploadFileList.length == 0){
                     // 파일등록 경고창
                     alert("파일이 없습니다.");
                     return;
                 }
                 
                 // 용량을 500MB를 넘을 경우 업로드 불가
                 if(totalFileSize > maxUploadSize){
                     // 파일 사이즈 초과 경고창
                     alert("총 용량 초과\n총 업로드 가능 용량 : " + maxUploadSize + " MB");
                     return;
                 }
                     
                 if(confirm("등록 하시겠습니까?")){
                     // 등록할 파일 리스트를 formData로 데이터 입력
                     var form = $('#uploadForm');
                     var formData = new FormData(form);
                     for(var i = 0; i < uploadFileList.length; i++){
                         formData.append('files', fileList[uploadFileList[i]]);
                     }    
                 }
             }

        });
        </script>
</body>
</html>
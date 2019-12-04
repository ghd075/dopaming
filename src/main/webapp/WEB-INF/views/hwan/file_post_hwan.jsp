<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Custom styles for this template -->
<link href="./resources/fontawesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="./resources/js/ie-emulation-modes-warning.js"></script>
<script src="./resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	//페이징 기능
	function goList(p) {
		form2.page.value = p;
		form2.submit();
	}
</script>
<title>파일 게시글</title>
<!-- <script src="//cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script> -->
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
.cen_table {
	margin: 0 auto;
}

table {
	width: 550px;
}

td {
	padding: 10px;
	border: 1px solid #666666;
}
th {
	padding: 10px;
	border: 1px solid #666666;
	background: #819FF7;
	color: #fff;
}
 /* Bootstrap 수정 */
    .table > thead {
      background-color: #b3c6ff;
    }

.no_border{
	border: 0px;
}
.write_on{
	float:right;
}
</style>
<script type="text/javascript">
function acorn_check(){
	alert("아콘 잔액이 부족합니다.");
}
$(document).ready(function(){
	var judge=0; //첫 댓글 시작
	var front_count=0; //앞 댓글 카운트
	var back_count=1; //뒤 댓글 카운트
	var id = "${sessionScope.Id}";	
	if(id != ""){
	$.ajax({
		url:"comment_list_hwan",
		data:{"board_no": $(".fileBoard_no").val() ,"comment_content" : $(".ComContent").val()},
		dataType:"json",
		beforeSend:function(){
			console.log("읽어오기 시작 전...");
		},
		complete:function(){
			console.log("읽어오기 완료 후...");
		},
	    error:function(request,status,error){
	         alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
	    },	 
		success: function(data){
			var show="";
			var sysdate=new Date(data.reg_date);
			console.log("데이터 : "+data[0].comment_content);
			for(var i =0; i<data.length;i++){
				$(".lastTr").after(makeCommentView(data[i]));
			}			
		}
	});
	} else{
		alert("로그인을 하셔야 댓글내용을 보실 수 있습니다.");
	}
	
	function makeCommentView(comment){
		var tr=$("<tr>");
		var d_com = new Date(comment.reg_date);
		tr.attr("id","c"+comment.comment_no);
		tr.addClass('comment');
		tr[0].comment=comment;
		
		var str="<td colspan='6'>"+comment.comment_content
		+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
		+"글쓴이 : "+comment.member_id
		+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
		+"날짜 : "+formatDate(d_com);
		+"</td>";
		tr.html(str);
		return tr;
	}
	
	function formatDate(date) {
		  return date.getFullYear() + '년 ' + 
		    (date.getMonth() + 1) + '월 ' + 
		    date.getDate() + '일 ';
	}
	
	
	$(".ComBtn").click(function(){		
		$.ajax({
			url:"comment_hwan",
			data:{"board_no": $(".fileBoard_no").val() ,"comment_content" : $(".ComContent").val()},
			dataType:"json",
			beforeSend:function(){
				console.log("읽어오기 시작 전...");
			},
			complete:function(){
				console.log("읽어오기 완료 후...");
			},
		    error:function(request,status,error){
		         alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
		    },	 
			success: function(data){
				var d_com = new Date(data.reg_date);
				var show="";
				var sysdate=new Date(data.reg_date);				
				if(judge ==0){
				show+="<tr class='rlast"+front_count+"'>";
				show+="<td colspan='6'>"+$(".ComContent").val()
				+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				+"글쓴이 : "+data.member_id
				+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				+"날짜 : "+formatDate(d_com);
				+"</td>";
				show+="</tr>";
				$(".lastTr").after(show);
				judge++;		
				} else {		  
				  show+="<tr class='rlast"+back_count+"'>";
				  show+="<td colspan='6'>"+$(".ComContent").val()
				  +"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				  +"글쓴이 : "+data.member_id
				  +"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				  +"날짜 : "+formatDate(d_com);
				  +"</td>";
				  show+="</tr>";
				  $('.rlast'+front_count).after(show);
				  back_count++;
				  front_count++;
				}
			}
		});			
	});	
});

//신고하기기능-joon
function complain_frm_send(){
	complain_frm.action = "complain_insert_form";
	complain_frm.submit();
}
</script>
</head>
<body>
	<div class="container">
	       <form action="filepost" name="form2" method="get">
		<input type="hidden" name="page" value="1"></input>
		<input type="hidden" name="board_no" value="${filePost.board_no}"></input>
		</form>
	
<!-- joon -->
<form name="complain_frm">
	<input type="hidden" name="board_no" value="${filePost.board_no}"/>
	<input type="hidden" name="member_id" value="${sessionScope.Id}"/>
	<input type="hidden" name="complain_type" value="신고"/>
</form>	
		
	<input type="hidden" name="board_acorn" value="${filePost.board_acorn}" />
	<table class="cen_table  table table-striped table-bordered">
		<tr>
			<th width="10%">게시글 번호</th>
			<th class="c1">분류</th>
			<th>제목</th>
			<th>각 파일 아콘</th>
			<th>용량(MB)</th>
			<th>판매자</th>
		</tr>
		<tr>			
			<td>${filePost.board_no}</td>
			<td class="c1">${filePost.category_big}/${filePost.category_small}</td>
			<td>${filePost.board_title}</td>
			<td>${filePost.board_acorn}</td>
			<td>${filePost.board_file_storage}MB</td>
			<td>${filePost.member_id}</td>				
		</tr>		
		<tr>
			<td>게시글 파일</td>
			<td colspan="5">
				<c:forEach items="${Board_FileList}" var="list">
					<label>${list.fileName_List}</label><br>					
				</c:forEach>
			</td>
		</tr>
		<tr>
		<td colspan="6" class="no_border">
		<label>${filePost.board_img}<br>${filePost.board_content}</label>		
  	 </td>
		</tr>
		<tr>
		<td colspan="6" class="no_border">
			 <table class="cen_table table table-striped table-bordered table-hover">
        <thead>
          <tr>
            <th width="10%">번호</th>
            <th width="50%">제목</th>
            <th width="10%">작성자</th>
            <th width="20%">대장르</th>
            <th width="10%">소장르</th>            
          </tr>
        </thead>
        <tbody>
           	<c:forEach items="${Board_List_Hwan}" var="flist">
          		<tr>
          		<td>${flist.board_no}</td>
          		<td><a href="filepost?board_no=${flist.board_no}&member_id=${flist.member_id}">${flist.board_title}</a></td>
          		<td>${flist.member_id}</td>
          		<td>${flist.category_big}</td>
          		<td>${flist.category_small}</td>
          		</tr>
          	</c:forEach>          
        </tbody>
     </table>    
	<!-- 페이징버튼 -->
	<my:paging_joon paging="${paging}" jsfunc="goList" />
		</td>
		</tr>
		<tr>
			<td colspan="6" class="no_border">
           <div>
            <c:if test="${not empty sessionScope.Id}">            
            	<button type="button" class="btn btn-primary hjh" onclick="complain_frm_send()">신고하기</button>            
            </c:if>
            <c:if test="${sessionScope.Id eq filePost.member_id}">
            	<button type="button" class="btn btn-primary" onclick="location.href='filepostUpdate?board_no=${filePost.board_no}&member_id=${filePost.member_id}'">수정하기</button>
            </c:if>
            <c:choose>   
                <c:when test="${sessionScope.Id eq filePost.member_id}">
            			<button onclick="location.href='download_hwan?group_no=${filePost.board_no}'" class="btn btn-success write_on">다운로드 하기</button>
            	</c:when> 
            	<c:when test="${(sessionScope.Acorn_stock lt filePost.board_acorn)}">
            			<button onclick="acorn_check()" class="btn btn-success write_on">다운로드 하기</button>
            	</c:when>
            	<c:when test="${(sessionScope.memberSession ne null || sessionScope.Id eq 'admin')}">
            			<button onclick="location.href='download_hwan?group_no=${filePost.board_no}&member_id=${filePost.member_id}'" class="btn btn-success write_on">다운로드 하기</button>
            	</c:when> 
            </c:choose>          
           </div>
           	</td>
		</tr>
		<tr class="lastTr">
			<td colspan="6" class="no_border">
			      <div>
                <c:choose>    
           		 	<c:when test="${sessionScope.memberSession ne null || sessionScope.Id eq 'admin'}">
 			      		<input type="hidden" class="fileBoard_no" name="board_no" value="${filePost.board_no}"/>				      					      		
      					<input  width="100%" class="ComContent" name="comment_content" placeholder="댓글쓰기">		
      					<button type="button" class="btn btn-success ComBtn">댓글 쓰기</button>
      				 </c:when>  
            	</c:choose>      			
				 </div>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
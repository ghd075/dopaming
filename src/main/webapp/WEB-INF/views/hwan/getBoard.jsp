<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><title>getBoard.jsp</title>
<style>
#comments {border : 1px solid blue; }
</style>
<script src="${pageContext.request.contextPath}/scripts/jquery-3.3.1.min.js"></script>
<script>
$(function(){
	
	var context = '${pageContext.request.contextPath}';
	
	//댓글 등록 이벤트
	$("#btnAdd").click(function(){
		$("#btnCancel").click();
		var params = $("[name=addForm]").serialize();
		var url = context + "insertComments";
		$.getJSON(url, params, function(datas){
			$("#commentList").append( makeCommentView(datas) );
			$("[name=addForm]")[0].reset();
		});
	});
	
	//댓글 수정 이벤트
	$("#btnUpd").click(function(){
		var params = $("[name=updateForm]").serialize();
		var url = context + "/updateComments";
		$.getJSON(url, params, function(datas){
			var newDiv = makeCommentView(datas);
			var oldDiv = $("#c"+datas.seq);
			$("#btnCancel").click();
			$(newDiv).replaceAll(oldDiv);  // 수정된 DIV를 교체
		});
	});
	
	
	//수정폼 이벤트(수정할 댓글밑에 수정폼 보이게 함)
	$("#commentList").on("click", ".btnUpdFrm", function(){
		var seq = $(this).parent().attr("id").substring(1);
		//수정할 데이터 입력필드에 셋팅
		$("#updateForm [name=seq]").val(seq);    
		$("#updateForm [name=name]").val($("#c"+seq+">.commentName").text());
		$("#updateForm [name=content]").val($("#c"+seq+">.commentContent").text());
		//수정할 댓글밑으로 이동하고 보이게
		$("#c"+seq).append($('#commentUpdate'));  
		$('#commentUpdate').show();   
	});


	//댓글 삭제 이벤트
	$("#commentList").on("click", ".btnDel", function(){
		var seq = $(this).parent().attr("id").substr(1);
		if(confirm("삭제할까요?")) {
			var params = "seq="+ seq;  // { seq : seq };
			var url = context + "/deleteComments";
			$.getJSON(url, params, function(datas){
				$('#c'+datas.seq).remove();
			});
		}
	});
	
	//수정 취소 이벤트
	$("#btnCancel").click(function(){
		$("[name=updateForm]")[0].reset();   //폼 필드 클리어
		$("#comments").append( $("#commentUpdate") );//수정 폼(div)를 이동
		$("#commentUpdate").hide();    // 수정폼 숨기기
	});
	
	//목록조회 요청
	function loadCommentList() {
		var params = { boardSeq : '${board.seq}' };
		$.getJSON(context + "/getCommentsList",params, function(datas){
			for(i=0; i<datas.length;i++) {
				$("#commentList").append( makeCommentView(datas[i]) );
			}
		});
	}
	
	function makeCommentView(comment){
		var div = $("<div>"); 
		div.attr("id", "c"+comment.seq);
		div.addClass('comment');
		div[0].comment=comment;  //{id:1,.... }
		
		var str ="<strong class='commentName'>" + comment.name + "</strong>" 
		        +"<span class='commentContent'>" + comment.content +"</span>"
				+"<button type=\"button\" class=\"btnUpdFrm\">수정</button>"
				+"<button type=\"button\" class=\"btnDel\">삭제</button>"
		div.html(str);
		return div;
	}
	
	//목록 조회
	loadCommentList(); 
})




</script>
</head>
<body>
${boardVO.title}
<hr>
<h1>게시글 조회</h1>
번호:${board.seq}   
작성자: ${board.writer} 
작성일자 :${board.regDate} <br>
제목 ${board.title} <br>
내용 ${board.content} <br>
<a href="../updateBoardForm?seq=${board.seq}">수정</a>  
<a href="../deleteBoard?seq=${board.seq}">삭제</a>


<div id="comments">

<div id="commentList"></div>

<!-- 댓글등록시작 -->
<div id="commentAdd">
	<form name="addForm" id="addForm">
	<input type="hidden" name="boardSeq" value="${board.seq}">
	이름: <input type="text" name="name" size="10"><br/>
	내용: <textarea name="content" cols="20" rows="2"></textarea><br/>
	<input type="button" value="등록" id="btnAdd"/>
	</form>
</div>
<!-- 댓글등록끝 -->

<!-- 댓글수정폼시작 -->
<div id="commentUpdate" style="display:none">
	<form action="" name="updateForm" id="updateForm">
	<input type="hidden" name="boardSeq" value="${board.seq}">
	<input type="hidden" name="seq" value=""/>
	이름: <input type="text" name="name" size="10"><br/>
	내용: <textarea name="content" cols="20" rows="2"></textarea><br/>
	<input type="button" value="등록" id="btnUpd"/>
	<input type="button" value="취소" id="btnCancel"/>
	</form>
</div>
<!-- 댓글수정폼끝 -->

</div>

</body>
</html>
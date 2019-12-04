<%@page import="com.dopaming.www.common.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료 다운로드</title>
<style>
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
.table>thead {
	background-color: #b3c6ff;
}

.no_border {
	border: 0px;
}

.write_on {
	float: right;
}

#main {
	transition: all .5s;
}

#main.ic-transitioning {
	opacity: 0;
}
</style>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"
	data-semver="2.1.1" data-require="jquery@*"></script>
<!-- <script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"
	data-semver="3.1.1" data-require="bootstrap@3.1.1"></script> -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery-mockjax/1.5.3/jquery.mockjax.min.js"></script>
<!-- <script
	src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-switch/3.0.2/js/bootstrap-switch.js"></script> -->

<!-- <link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet" data-semver="3.1.1" data-require="bootstrap-css@3.1.1" /> -->
<!-- <link
	href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-switch/3.0.2/css/bootstrap2/bootstrap-switch.css"
	rel="stylesheet" / -->
<script src="./resources/js/intercooler-1.2.2.js"></script>
<!-- <script>
	$(function() {
		fileDownload();
	});

	// 다운로드 요청 ==> json 
	function fileDownload() {
		$('.btnDown').on('click', function() {			
			$.ajax({
				url:"progressbar_progressing",				
				dataType : "json",				
				data:{"test_id":$(".test_id").text()},
				beforesend : function(date){
				    $.mockjax({
				        url: '/job',
				        response: function (settings) {    	
				          jobManager.start();
				        }
				      });
					console.log("읽어오기 시작 전...");
					alert("읽어오기 시작 전...");
				}, 
				complete:function(){
					console.log("읽어오기 완료 후...");
					alert("읽어오기 시작 전...");
				},
				success : function(data) {
					console.log("동작 성공...");
					alert("동작 성공...");
				},
				error : function(xhr, status, message) {
					console.log("에러 발생...");
					alert("에러 발생...");
				}
			});
		});
	}
</script> -->
</head>
<body>
	<div class="container cen_form">
		<form>
			<input type="hidden" name="download_acorn"
				value="${downPost.board_acorn}" /> <input type="hidden"
				name="board_acorn" value="${downPost.board_acorn}" />
			<table class="cen_table table table-striped table-bordered">
				<tr>
					<th>각 파일 다운로드 아콘</th>
					<th>회원 아이디</th>
					<th>결제 날짜</th>
				</tr>
				<tr>
					<td>${downPost.board_acorn}</td>
					<td><label class="test_id">${sessionScope.Id}</label></td>
					<td><label><%=DateUtil.tDateFormat()%></label></td>
				</tr>
				<tr>
					<td colspan="3">
						<ol>
							다운로드 항목들
							<c:forEach items="${downPost_List}" var="list">
								<li>${list.file_name}</li>
							</c:forEach>
						</ol>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="main" ic-src="/job/status"></div>
					</td>
				</tr>
				<tr>
					<td style="border-right: none;"></td>
					<td colspan="2" class="cen_table" style="border-left: none;">
						<button type="button" class="btn btn-success btn-md btnDown" 
						onclick="location.href='request_download?group_no=${downPost.group_no}&member_id=${sessionScope.Id}&seller=${downPost.member_id}'"				 
							ic-post-to="/job">다운로드 하기</button>
						<button type="button" class="btn btn-success btn-md"
							onclick="location.href='download_cancel'">취소 하기</button>							
					</td>					
				</tr>
			</table>
		</form>
	</div>
</body>
<script>
	$(function() {
		fileDownload();
	});

	// 다운로드 요청 ==> json 
	function fileDownload() {
		$('.btnDown').on('click', function() {
			$.ajax({
				url : "request",
				dataType : "json",				
				beforesend : function() {
					console.log("읽어오기 시작 전...");
					//alert("읽어오기 시작 전...");
				},
				complete : function() {
					console.log("읽어오기 완료 후...");
					//alert("읽어오기 완료 후...");
				},
				/*success : function(data) {
					console.log("동작 성공...");
					alert("동작 성공...");
				},*/
				error : function(xhr, status, message) {
					//console.log("에러 발생...");
					//alert("에러 발생...");
				}
			});
		});
	}

	//========================================================================
	// Mock Server-Side HTTP End Point
	//========================================================================
	$.mockjax({
		url : '/job',
		response : function(settings) {
			jobManager.start();
		}
	});
	
	$.mockjax({
		url : '/job/status',
		response : function(settings) {
			var job = jobManager.currentProcess();
			this.responseText = jobStatusTemplate(job);
		}
	});

	$.mockjax({
		url : '/job/status/percent_complete',
		response : function() {
			var job = jobManager.currentProcess();
			this.responseText = job.percentComplete + "%";
			if (job.complete) {
				this.headers = {
					"X-IC-Refresh" : "/job/status"
				}; // if the job is complete, refresh the entire job status UI
			}
		}
	});

	//========================================================================
	// Mock Server-Side Templates
	//========================================================================
	function jobStatusTemplate(process) {
		if (process.complete) {
			return 'Download Complete!';
		} else {
			return 'Running Download:'
					+ '  <div class="progress progress-striped active">'
					+ '   <div class="progress-bar" ic-style-src="width:/job/status/percent_complete" ic-poll="1s" style="width:0%"></div>'
					+ '  </div>';
		}
	}

	//========================================================================
	// Mock Process Manager
	//========================================================================
	var jobManager = (function() {
		var currentProcess = null;
		return {
			start : function() {
				currentProcess = {
					complete : false,
					percentComplete : 0
				}
			},
			currentProcess : function() {
				currentProcess.percentComplete += Math.min(100, Math
						.floor(33 * Math.random())); // simulate progress
				currentProcess.complete = currentProcess.percentComplete >= 100;
				return currentProcess;
			}
		}
	})();
</script>
</html>
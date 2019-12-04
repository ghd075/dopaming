<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/slider.css">
<!-- 로그인 모달 -->
<div class="modal fade" id="modalLogin" role="dialog">
	<div class="modal-dialog cascading-modal modal-margin" role="document">
		<div class="modal-content loginmodal_resize">
			<div class="modal-body loginmodal_body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<img class="rounded-circle loginmodal_image" src="<c:url value="/resources/images/hong/login.gif"/>"/>
				<p class="loginmodal_title">Dopaming</p>
				<input class="loginmodal_loginid modal-input login-input" type="text" id="input-loginid" name="member_id" placeholder="아이디 입력">
         		<input class="loginmodal_loginpass modal-input login-input" type="password" id="input-loginpass" placeholder="비밀번호 입력">
              	<div class="form-check">
	      			<label class="switch">
						<input type="checkbox" name="loginCheck" id="loginCheck">
						<span class="slider round"></span>
					</label>
					<label class="form-check-label" for="loginCheck">아이디 저장</label>
              	</div>
              	<div class="text-center mt-2">
         			<button class="btn btn-primary modal-loginbtn" type="button">로그인</button>
         		</div>
         		<div class="content_modalsocialwarp">
					<img src="${pageContext.request.contextPath}/resources/images/hong/google.png" class="content_socialicon" data="social-google">
					<img src="${pageContext.request.contextPath}/resources/images/hong/naver.png" class="content_socialicon" data="social-naver">
					<img src="${pageContext.request.contextPath}/resources/images/hong/facebook.png" class="content_socialicon" data="social-facebook"> 
					<img src="${pageContext.request.contextPath}/resources/images/hong/kakao.png" class="content_socialicon" data="social-kakao">
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 회원가입 모달 -->
<div class="modal fade" id="joinModal" role="dialog">
	<div class="modal-dialog cascading-modal modal-margin" role="document">
		<div class="modal-content loginmodal_resize">
			<div class="modal-body joinmodal_body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<p class="joinmodal_title">회원가입</p>
				<input class="joinmodal_joinid modal-input join-input" type="text" id="input-joinid" placeholder="아이디 입력">
                <input class="joinmodal_joinpass modal-input join-input" type="password" id="input-joinpass" placeholder="비밀번호 입력">
               	<input class="joinmodal_joinpass modal-input join-input" type="password" id="input-joincheck" placeholder="비밀번호 재입력">
               	<input class="joinmodal_joinemail modal-input join-input" type="email" id="input-joinemail" placeholder="EXAMPLE@GMAIL.COM">
	   	        <div class="text-center">
			        <button id="join-submit" class="btn btn-info modal-joinbtn" type="button">회원가입</button>			
	           	</div>
            </div>
		</div>
	</div>
</div>
<!-- / 경로만 적용(요청URI정보받아오기  -->
<c:if test="${requestScope['javax.servlet.forward.servlet_path'] eq '/' }">	
	<!-- 미인증회원 로그인시 모달 -->
   	<div class="modal fade" id="modalSendMail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   		<div class="modal-dialog cascading-modal modal-sm" role="document">
       		<div class="modal-content">
       			<div class="modal-header">
              		<img class="rounded-circle img-responsive" src="<c:url value="/resources/images/hong/login.gif"/>">
               	</div>
               	<div class="modal-body text-center mb-1">
     				<h6 class="mt-1 mb-2 modal-resendtext"><span class='modal-resendpoint'>인증</span>이 필요한 아이디 입니다.</h6>
                   	<h6 class="mt-1 mb-2 modal-resendtext">필요한 사항의 버튼을 눌러주세요.</h6>
                    <h6 class="mt-1 mb-2 modal-resendtext">정보삭제시 <span class='modal-resendpoint'>재가입</span> 가능합니다.</h6>
                   	<h5 class=""></h5>
                    <div class="md-form ml-0 mr-0">
                   		<input type="hidden" id="sendmailid">
                    </div>
                    <div class="text-center mt-4 modal-sendbox">
                   		<button class="btn btn-warning mt-1 modal-resendbtn" id="modal-deleteinfo">정보삭제</button>
                    	<button class="btn btn-warning mt-1 modal-resendbtn" id="modal-resendmail">초대장재전송</button>
                   	</div>
              	</div>
      		</div>
       	</div>
   	</div>
   	
	<!-- 메일 인증 비밀번호 변경 모달 -->
 	<div class="modal fade" id="modalChangePassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    	<div class="modal-dialog cascading-modal modal-avatar modal-sm" role="document">
    		<div class="modal-content">
            	<div class="modal-header">
                 	<img src="<c:url value="/resources/images/hong/login.gif"/>" alt="avatar" class="rounded-circle img-responsive">
                </div>
               	<div class="modal-body text-center mb-1">
               		<h6 class="mt-1 mb-2 modal-resendtext"><span class='modal-resendpoint'>비밀번호</span>를 입력해주세요.</h6>
                	<h6 class="mt-1 mb-2 modal-resendtext"><span class='modal-resendpoint'>영어숫자</span>포함 8~16자 설정!</h6>
                  	<h5 class=""></h5>
                  	<div class="md-form ml-0 mr-0">
                    	<input type="hidden" id="authId" value="${sessionScope.authId}">
                  		<input type="password" class="form-control form-control-sm modal-input" id="changePass">
                    </div>
                    <br>
                    <div class="text-center mt-4 modal-sendbox">
                       <button class="btn btn-warning mt-1 modal-resendbtn" id="modal-changepass">비밀번호변경</button>
                    </div>
                </div>
             </div>
          </div>
      </div>
   	
</c:if>
        <!-- 상태코드 메세지에 따른 결과 처리 -->
        <c:if test="${sessionScope.stateCode ne null}">
            <script type="text/javascript">
                $(document).ready(function () {
                    let stateComment = '${sessionScope.stateCode}';
                    let stateFlag = stateComment.includes('SUCCESS');
                    // 패스워드이메일인증
                    if (stateComment.includes('PASSAUTH')) {
                        timerAlert('비밀번호변경 인증', '이메일 정보를 인증중 입니다.', 2000);
                        setTimeout(() => {
                        stateFlag ? $('#modalChangePassword').modal('show') : errorAlert(stateCode.get(stateComment));
                        }, 2300);
                    // 초대장메일인증
                    } else {
                        stateComment.includes('SUCCESS') ? successAlert(stateCode.get(stateComment)) : errorAlert(stateCode.get(stateComment));
                    }
                });
                <c:remove var="stateCode" scope="session"/>
            </script>
        </c:if>

<script src="${pageContext.request.contextPath}/resources/js/modal.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/cookie.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/lib/Liar.js"></script>

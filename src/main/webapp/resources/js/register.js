$(function() {
	var checkId = RegExp(/^[a-zA-Z0-9]{4,12}$/);	//아이디 4~12자리
	var checkEmail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/); //이메일 정규표현식

	$("#joinForm").submit(
			function() {
				//아이디 공백 확인
			    if($("#member_id").val() == ""){
			       alert("아이디 입력바랍니다.");
			       $("#member_id").focus();
			       return false;
			     }
			    
			    //아이디 유효성 검사
			    if(!checkId.test($("#member_id").val())){
			        alert("형식에 맞게 입력해주세요");
			        $("#member_id").val("").focus();
			        return false;
			      }
			  
			    //비밀번호 공백 확인
			    if($("#member_password").val() == ""){
			       alert("패스워드 입력해주세요");
			       $("#member_password").focus();
			       return false;
			    }
			    
			     //아이디 비밀번호 같음 확인
			      if($("#member_id").val() == $("#member_password").val()){
			        alert("아이디와 비밀번호가 같습니다");
			        $("#member_password").val("").focus();
			        return false;
			      }
			      
			      //비밀번호 길이 8자리 이상
			      if ($("#member_password").val().length < 8) {
						alert("비밀번호는 8자 이상으로 설정해야 합니다.");
						$("#member_password").val("").focus();
						return false;
					}
			      
			      //비밀번호 확인란 공백 확인
			      if($("#check_passowrd").val() == ""){
			        alert("패스워드 확인란을 입력해주세요");
			        $("#check_passowrd").focus();
			        return false;
			      }

			      //비밀번호 서로 확인
			      if ($("#member_password").val() !== $("#check_passowrd").val()) {
						alert("비밀번호가 다릅니다.");
						$("#member_password").val("").focus();
						$("#check_passowrd").val("");
						return false;
					}
			      
			      //이메일 공백 확인
			      if($("#member_email").val() == ""){
			        alert("이메일을 입력해주세요");
			        $("#member_email").focus();
			        return false;
			      }

			      //이메일 유효성 검사
			      if(!checkEmail.test($("#member_email").val())){
			        alert("이메일형식에 맞게 입력해주세요")
			        $("#member_email").val("").focus();;
			        return false;
			      }
			})
			
			$("#member_id").keyup(function() {
				$.ajax({
					url : contextPath + "/check_id",
					type : "POST",
					data : {
						id : $("#member_id").val()
					},
					success : function(result) {
						if (result == 1) {
							$("#id_check").html("중복된 아이디가 있습니다.");
							$("#joinBtn").attr("disabled", "true");
						} else {
							$("#id_check").html("");
							$("#joinBtn").removeAttr("disabled");
						}
					},
				})
			});
	$("#member_email").keyup(function(){
		$.ajax({
			url : contextPath + "/check_email",
			type : "POST",
			data : {
				email : $("#member_email").val()
			},
			success : function(result) {
				if (result == 1) {
					$("#email_check").html("중복된 이메일이 있습니다.");
				} else {
					$("#email_check").html("");
				}
			},
		})
	});
});
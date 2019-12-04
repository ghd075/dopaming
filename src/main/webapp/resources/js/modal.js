
$(document).ready(function () {
	
	//엔터키적용
    enterPressAction('login-input', 'modal-loginbtn');
    enterPressAction('join-input', 'modal-joinbtn');
	
    // 모달버튼열기 [ 로그인 / 회원가입 / 패스워드변경 ]
    $('.modal-user').on('click', function () {
        let user = $(this).attr('data-user');
        initElement('modal-input');
        switch (user) {
            case 'login' :
                $('#modalLogin').modal('show');
                var userInputId = getCookie("userId");// 저장된 쿠기값 가져오기
            	$("input[name='member_id']").val(userInputId);
                break;
            case 'join' :
                $('#joinModal').modal('show');
                break;
            case 'password' :
                $('#modalChangePass').modal('show');
                break;
            case 'logout' :
                let logginedId = $(this).attr('data-id');
                let currentUrl = $(location).attr('pathname');
                let searchUrl = $(location).attr('search');
                let moveUrl = `/logoutA/${logginedId}?currentUrl=${currentUrl}${searchUrl}`;
                location.href = context+moveUrl;
                console.log(context,moveUrl);
                break;
        }
    });

	
	// 모달 로그인 버튼 클릭시
    $('.modal-loginbtn').on('click', function () {
        let memberInfo = new Map();
        memberInfo.set('member_id', $('#input-loginid').val());
        memberInfo.set('member_password', $('#input-loginpass').val());
        let memberJson = mapToJson(memberInfo);	//map -> Json
        
        for (let item of memberInfo) {
            if (item[1] === '') {
                errorAlert('정보를 모두 입력해주세요');
                return false;
            }
        }
        $('#modalLogin').modal('hide');
        memberNormalLogin(memberJson)
            .then((data) => {
                if (data === 'LOGIN_STATE_SUCCESS') {
                    location.href = context+'/';
                } else if (data === 'ID_STATE_WAITAPPROVAL') {
                    timerAlert('로그인', '회원정보를 확인중입니다.', 2000);
                    setTimeout(() => {
                        $('#sendmailid').val($('#input-loginid').val());
                        $('#modalSendMail').modal('show');
                    }, 2300)
                }
            }).catch((error) => {
            error = error.responseText;
            setTimeout(() => {
                errorAlert(stateCode.get(error));
            }, 1400);
        });
    });
	
	// 모달 회원가입 버튼 클릭시
    $('.modal-joinbtn').on('click', function () {
        const memberInfo = new Map();
        memberInfo.set('member_id', $('#input-joinid').val());
        memberInfo.set('member_password', $('#input-joinpass').val());
        memberInfo.set('checkPassword', $('#input-joincheck').val());
        memberInfo.set('member_email', $('#input-joinemail').val());

        for (let item of memberInfo) {
            if (item[1] === '') {
                errorAlert('정보를 모두 입력해주세요');
                return false;
            }
        }
        let memberJson = mapToJson(memberInfo);	//map -> Json
        memberJoin(memberJson)
            .then((data) => {
                if (data == 'MEMBER_STATE_SUCCESS') {
                	$('#joinModal').modal('hide');
                    setTimeout(() => {
                        timerAlert('초대장전송', '메일로 초대장을 전송중입니다!', 100000);
                    }, 700);
                    sendMail(memberInfo).then((data) => {
                        Swal.closeModal();
                        setTimeout(() => {
                            successAlert(stateCode.get(data));
                        }, 700);
                    }).catch((error) => {
                        errorAlert(stateCode.get(error.responseText));
                    });
                }
            }).catch((error) => {
            errorAlert(stateCode.get(error.responseText));
        });
    });
	
    // 비밀번호변경 모달창 호출
    $('#recovery-password').on('click', () => {
        ajaxAlert('가입하신 이메일을 입력하세요.', context+'/mail/changepass/', '이메일이 전송되었습니다.', '이메일이 올바르지 않습니다.');
    });
    
    // 이메일 인증을 통한 비밀번호 변경 버튼
    $('#modal-changepass').on('click', () => {
        let memberInfo = new Map();
        memberInfo.set('member_id', $('#authId').val());
        memberInfo.set('member_password', $('#changePass').val());
        let memberJson = mapToJson(memberInfo);
        changePassword(memberJson)
            .then((data) => {
                $('#modalChangePassword').modal('hide');
                setTimeout(() => {
                    successAlert(stateCode.get(data));
                }, 300);
            }).catch((error) => {
            errorAlert(stateCode.get(error.responseText));
        }).finally(() => {
            $('#changePass').val('');
        });
    });


    // 회원인증 비밀번호 변경
    const changePassword = (memberJson) => {
        return new Promise((resolve, reject) => {
            $.ajax({
                type: 'PUT',
                url: context+'/mail/changepass/mailpassword',
                data: memberJson,
                contentType: 'application/json; charset=utf-8',
                success: (data) => {
                    resolve(data);
                },
                error: (error) => {
                    reject(error);
                }
            });
        });
    }
});

//클래스 value 초기화
const initElement = (className) => {
    let elements = document.getElementsByClassName(className);
    for (let element of elements) {
        element.value = '';
    }
}

//회원로그인 정보 입력
const memberNormalLogin = (memberJson) => {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: 'POST',
            url: context+'/loginA',
            data: memberJson,
            contentType: 'application/json; charset=utf-8',
            success: (data) => {
                resolve(data);	//성공
            }, error: (error) => {
                reject(error);	//실패
            }
        });
    });
}

//회원가입 정보 입력
const memberJoin = (memberJson) => {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: 'POST',
            url: context+'/register',
            data: memberJson,
            contentType: 'application/json; charset=utf-8',
            success: (data) => {
                resolve(data);
            },
            error: (error) => {
                reject(error);
            }
        });
    });
}

//미승인 회원정보 삭제
$('#modal-deleteinfo').on('click', () => {
    $('#modalSendMail').modal('hide');
    let memberJson = {
        'member_id': $('#sendmailid').val()
    }
    memberJson = JSON.stringify(memberJson);
    setTimeout(() => {
        timerAlert('정보삭제', '회원님의 정보를 삭제중입니다.', 1500);
    }, 200);
    deleteInfomation(memberJson)
        .then((data) => {
            setTimeout(() => {
                successAlert(stateCode.get(data));
            }, 1900);
        }).catch((error) => {
        setTimeout(() => {
            errorAlert(stateCode.get(error.responseText));
        }, 1900);
    });
})

//미인증 회원정보 삭제처리
const deleteInfomation = (memberJson) => {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: 'POST',
            url: context+'/deleteinfo',
            data: memberJson,
            contentType: 'application/json; charset=utf-8',
            success: (data) => {
                resolve(data);
            },
            error: (error) => {
                reject(error);
            }
        });
    });
}

//회원가입 이메일 발송
const sendMail = (memberInfo) => {
    return new Promise((resolve, reject) => {
        $.ajax({
            type: 'GET',
            url: context+`/mail/invite/${memberInfo.get('member_id')}`,
            contentType: 'application/json; charset=utf-8',
            success: (data) => {
                resolve(data);
            },
            error: (error) => {
                reject(error);
            }
        });
    });
}

//이메일 재발송
$('#modal-resendmail').on('click', () => {
    $('#modalSendMail').modal('hide');
    let memberInfo = new Map();
    memberInfo.set('member_id', $('#sendmailid').val());
    setTimeout(() => {
        timerAlert('초대장 재발송', '초대장을 전송중입니다!', 100000);
    }, 300);
    sendMail(memberInfo)
        .then((data) => {
            Swal.closeModal();
            setTimeout(() => {
                successAlert(stateCode.get(data));
            }, 500);
        }).catch((error) => {
        liar(res => {
            setTimeout(() => {
                Swal.closeModal();
                res.next();
            }, 500);
        }).next(res => {
            setTimeout(() => {
                errorAlert(stateCode.get(error.responseText));
                res.next();
            }, 300);
        });
    });
});

// SweetAlert Ajax
const ajaxAlert = (title, url, successText, errorText) => {
    Swal.fire({
        title: title,
        input: 'text',
        inputAttributes: {
            autocapitalize: 'off'
        },
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '발송',
        showLoaderOnConfirm: true,
        preConfirm: (data) => {
            return fetch(`${url}${data}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }
            })
                .then(response => {
                    if (response.status == 500) {
                        throw new Error(response.statusText);
                    }
                    return response;
                })
                .catch(error => {
                    Swal.showValidationMessage(
                        `오류 발생: ${errorText}.`
                    )
                })
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((data) => {
        if (data.dismiss == 'cancel' || data.dismiss == 'backdrop') {
            return false;
        }
        Swal.closeModal();
        setTimeout(() => {
            successAlert(successText);
        }, 300);
    })
};

// SweetAlert Success
const successAlert = (text) => {
    Swal.fire({
        type: 'success',
        title: text,
        showConfirmButton: false,
        allowOutsideClick: false,
        timer: 2000
    });
};

// SweetAlert Timer
const timerAlert = (title, text, time) => {
    let timerInterval
    Swal.fire({
        title: title,
        html: text,
        timer: time,
        allowOutsideClick: false,
        onBeforeOpen: () => {
            Swal.showLoading()
            timerInterval = setInterval(() => {
            }, 100)
        },
        onClose: () => {
            clearInterval(timerInterval)
        }
    }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer) {
        }
    })
}

//SwertAlert Error
const errorAlert = (text) => {
    Swal.fire({
        type: 'error',
        title: text,
        showConfirmButton: false,
        allowOutsideClick: false,
        timer: 1500,
    });
}

//Map을 Json 으로 변환
const mapToJson = (map,isCheck) => {
    return JSON.stringify(mapToObject(map,isCheck));
}

const mapToObject = (map,isCheck) => {
    if ( !isCheck ) isCheck = false;
    let obj = Object.create(null);
    for (let [key, value] of map) {
        if ( isCheck ) {
            typeof value == 'string' ? obj[key] = value.toLowerCase() : obj[key] = value;
        } else {
            obj[key] = value;
        }
    }
    return obj;
}

//url + parameter(경로 지정
const currentUrl = () =>{
    return `${location.pathname}${location.search}`;
}

//엔터키 적용
const enterPressAction = (inputName, targetName) => {
    $(`.${inputName}`).keyup( function(e) {
        if ( e.keyCode == 13 ) {
            $(`.${targetName}`).click();
        }
    });
}
// 공통상태코드
const stateCode = new Map();

// 회원가입/수정
stateCode.set('MEMBER_STATE_SUCCESS', '회원정보가 등록되었습니다.');
stateCode.set('MEMBER_UPDATE_SUCCESS', '회원정보가 수정되었습니다.');
stateCode.set('ID_STATE_EMPTY', '아이디를 입력해주세요..');
stateCode.set('PASS_STATE_EMPTY', '비밀번호를 입력해주세요.');
stateCode.set('CHEKPASS_STATE_EMPTY', '비밀번호확인을 입력해주세요.');
stateCode.set('PASS_STATE_SAME','비밀번호가 일치하지 않습니다.');
stateCode.set('EMAIL_STATE_EMPTY', '이메일을 입력해주세요.');
stateCode.set('ID_STATE_USED', '이미 사용중인 아이디 입니다.');
stateCode.set('EMAIL_STATE_USED', '이미 사용중인 이메일 입니다.');
stateCode.set('ID_STATE_ERROR', '부적절한 아이디 입니다.');
stateCode.set('PASS_STATE_ERROR', '부적절한 비밀번호 입니다.');
stateCode.set('EMAIL_STATE_ERROR', '부적절한 이메일 입니다.');

// 초대장 발송
stateCode.set('INVITE_STATE_SUCCESS', '회원가입 이메일이 발송되었습니다.');
stateCode.set('INVITE_STATE_ERROR', '회원가입 이메일 발송을 실패했습니다.');
stateCode.set('AUTH_STATE_SUCCESS', '회원가입 인증에 성공했습니다.');
stateCode.set('AUTH_STATE_ERROR', '회원가입 인증에 실패했습니다.');
stateCode.set('ID_STATE_WAITAPPROVAL', '이메일 승인대기 아이디 입니다.');

// 미인증 회원정보 삭제
stateCode.set('INFODEL_STATE_SUCCESS', '회원님의 정보를 삭제 했습니다.');
stateCode.set('INFODEL_STATE_ERROR', '회원님의 정보 삭제에 실패했습니다.');

//로그인
stateCode.set('LOGIN_STATE_SUCCESS', '로그인 되었습니다.');
stateCode.set('LOGIN_STATE_ERROR', '아이디 혹은 패스워드가 다릅니다.');
stateCode.set('LOGIN_BLACK_ERROR', '아이디가 차단되었습니다.');

//소셜 로그인
stateCode.set("SOCIAL_LOGIN_SUCCESS", "로그인 되었습니다.");
stateCode.set("SOCIAL_JOIN_SUCCESS", "회원가입 되었습니다.");
stateCode.set("SOCIAL_JOIN_ERROR", "회원가입 실패했습니다.");

//패스워드 임시비밀번호 발급메일
stateCode.set('PASSMAIL_STATE_SUCCESS', '메일을 발송 했습니다.');
stateCode.set('PASSMAIL_STATE_ERROR', '이메일이 올바르지 않습니다.');

//회원정보 변경
stateCode.set("INFORMATION_CHANGE_SUCCESS", "회원정보를 변경 했습니다.");
stateCode.set("INFORMATION_CHANGE_ERROR", "정보변경에 실패 했습니다.");
stateCode.set("SOCIAL_PASSWORD_ERROR", "소셜회원은 변경 할수 없습니다.");

//로그아웃
stateCode.set("LOGOUT_STATE_SUCCESS", "로그아웃 되었습니다.");
stateCode.set("LOGOUT_STATE_ERROR", "잘못된 접근입니다.");

//패스워드 재설정 인증메일
stateCode.set('PASSMAIL_STATE_SUCCESS', '메일을 발송 했습니다.');
stateCode.set('PASSMAIL_STATE_ERROR', '이메일이 올바르지 않습니다.');
stateCode.set('PASSAUTH_STATE_SUCCESS', '메일 인증에 성공 했습니다.');
stateCode.set('PASSAUTH_STATE_ERROR', '메일 인증에 실패 했습니다.');

//패스워드 변경
stateCode.set('PASS_CHANGE_SUCCESS', '비밀번호가 변경되었습니다..');
stateCode.set('PASS_CHANGE_ERROR', '비밀번호가 올바르지 않습니다.');
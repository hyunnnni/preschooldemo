package com.preschool.preschooldemo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor//생성자로 넣어준다
public enum AuthErrorCode implements ErrorCode{//enum : Const를 대체할 수 있는

    NOT_EXIST_USER_ID(HttpStatus.NOT_FOUND,"아이디가 존재하지 않습니다."),
    VALID_PASSWORD(HttpStatus.NOT_FOUND,"비밀번호를 확인해주세요"),
    NEED_SIGNIN(HttpStatus.UNAUTHORIZED,"로그인이 필요합니다"),
    FAIL(HttpStatus.NOT_ACCEPTABLE,"요청은 정상이나 실패하였습니다"),
    SUCCESS(HttpStatus.OK,"요청 수행 성공하였습니다"),
    NO_PERMISSION(HttpStatus.UNAUTHORIZED,"관리자 페이지에 접근할 권한이 없습니다"),
    NO_INFORMATION(HttpStatus.NOT_FOUND,"조회되는 정보가 없습니다"),
    UPD_STATE_FAIL(HttpStatus.NOT_FOUND,"재원상태/반 수정 실패"),
    UPD_IS_DEL_FAIL(HttpStatus.NOT_FOUND,"연결 부모님 계정 삭제 처리 실패입니다"),
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.NOT_FOUND,//NOT_FOUND 상수 객체화를 해서 AuthErrorCode주소값을 NOT_FOUND_REFRESH_TOKEN에 담겠다가 enum
            "refresh_token이 없습니다");

    private final HttpStatus httpStatus;
    private final String message;

/*    AuthErrorCode(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message
    } 이 작업으로 값을 넣어주고 있다*/
}

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
    NO_PERMISSION(HttpStatus.UNAUTHORIZED,"관리자 페이지에 접근할 권한이 없습니다"),
    NO_INFORMATION(HttpStatus.NOT_FOUND,"조회되는 정보가 없습니다"),
    UPD_STATE_FAIL(HttpStatus.NOT_FOUND,"재원상태/반 수정 실패"),
    UPD_IS_DEL_FAIL(HttpStatus.NOT_FOUND,"부모님 계정 삭제 처리 실패입니다"),
    NOT_CORRECT_INFORMATION(HttpStatus.BAD_REQUEST,"잘못된 요청입니다"),
    CHECK_CODE(HttpStatus.BAD_REQUEST,"식별코드를 확인하세요."),
    CHECK_DUPLICATION_ID(HttpStatus.BAD_REQUEST,"아이디 중복체크를 해주세요."),
    NOT_ENTER_ID(HttpStatus.BAD_REQUEST,"아이디를 입력하세요."),
    ALREADY_EXIST_ID(HttpStatus.BAD_REQUEST,"이미 존재하는 아이디입니다"),
    NOT_EMPTY_INFO(HttpStatus.BAD_REQUEST,"정확한 값을 입력하세요"),
    ALL_YOU_NEED_IS_PARAM(HttpStatus.BAD_REQUEST,"모든 값을 입력하세요"),
    CHANGE_JUST_ONETHING(HttpStatus.BAD_REQUEST, "한 개 이상 입력하세요"),
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.NOT_FOUND,"refresh-token이 없습니다"),
    NOT_CONNETCT_KID(HttpStatus.BAD_REQUEST,"최대 2명까지만 연결 가능 합니다."),
    NOT_IMPORTED(HttpStatus.BAD_REQUEST ,"정보를 불러올 수 없습니다"),
    OVER_FIX_NOTICE(HttpStatus.BAD_REQUEST, "공지갯수를 초과했습니다"),
    NOT_ENTER_ACCESS(HttpStatus.BAD_REQUEST,"권한이 없습니다"),
    PICS_FAIL(HttpStatus.NOT_ACCEPTABLE,"사진 실패하였습니다"),
    GRADE_FAIL(HttpStatus.NOT_ACCEPTABLE,"반 승급 데이터 삽입을 실패했습니다"),
    OVER_GROWTH(HttpStatus.NOT_FOUND, "분기당 한번만 등록할 수 있습니다");



    private final HttpStatus httpStatus;
    private final String message;

/*    AuthErrorCode(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message
    } 이 작업으로 값을 넣어주고 있다*/
}

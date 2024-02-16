package com.preschool.preschooldemo.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    /*@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e ){
        log.warn("handleIllegalArgument", e);
        return handleExceptionInternal(CommonErrorCode.INVALID_PARAMETER);
    }*/

    // @Valid 어노테이션으로 넘어오는 에러 처리
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex, CommonErrorCode.INVALID_PARAMETER);
    }

    //대부분의 에러처리는 서버에러가 터지게 만듬
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e){

        log.warn("handleException", e);
        return handleExceptionInternal(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }

    //customizing 한 에러메세지
    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleRestApiException(RestApiException e){
        log.warn("handleRestApiException", e);
        return handleExceptionInternal(e.getErrorCode());
    }


    /*@ExceptionHandler(MethodArgumentNotValidException.class)//Valid를 사용한 부분에서 정해놓은 거 외에 값이 들어와 에러가 발생했을 때의 메소드
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.warn("handleMethodArgumentNotValidException", e);

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(lfe -> lfe.getDefaultMessage())
                .collect(Collectors.toList());


        String errStr = "["+String.join( ", " , errors)+"]";
        return handleExceptionInternal(CommonErrorCode.INVALID_PARAMETER, errors.toString());
    }*/

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return handleExceptionInternal(errorCode, null);
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(message == null
                        ? makeErrorResponse(errorCode)
                        : makeErrorResponse(errorCode, message));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode) { //원래 있던 에러메세지 사용
        return makeErrorResponse(errorCode, errorCode.getMessage());
    }


    private ErrorResponse makeErrorResponse(ErrorCode errorCode, String message) { //커스터마이징해서 에러메세지 사용
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(message)
                .build();
    }
    // @Valid 어노테이션으로 넘어오는 에러 처리 메세지를 보내기 위한 메소드
    private ResponseEntity<Object> handleExceptionInternal(BindException e, ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(e, errorCode));
    }

    // 코드 가독성을 위해 에러 처리 메세지를 만드는 메소드 분리
    private ErrorResponse makeErrorResponse(BindException e, ErrorCode errorCode) {
        List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .valid(validationErrorList)
                .build();
    }

}

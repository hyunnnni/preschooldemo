package com.preschool.preschooldemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{//예외를 잡아주는 controller


    @ExceptionHandler(IllegalCallerException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e){
        log.warn("HandleIllegalArgument", e);
        return handleExceptionInternal(CommonErrorCode.INVALID_PARAMETER);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e){
        log.warn("handleException", e);
        return handleExceptionInternal(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handlerestApiException(RestApiException e){
        log.warn("handlerestApiException", e);
        return handleExceptionInternal(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)//Valid를 사용한 부분에서 정해놓은 거 외에 값이 들어와 에러가 발생했을 때의 메소드
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.warn("handleMethodArgumentNotValidException", e);

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(lfe -> lfe.getDefaultMessage())
                .collect(Collectors.toList());


        String errStr = "["+String.join( ", " , errors)+"]";
        return handleExceptionInternal(CommonErrorCode.INVALID_PARAMETER, errors.toString());
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode){
        return handleExceptionInternal(errorCode, null);
    }
    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message){
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(message == null
                        ? makeErrorResponse(errorCode)
                        : makeErrorResponse(errorCode, message));
    }
    private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
    return makeErrorResponse(errorCode, errorCode.getMessage());
    }
    private ErrorResponse makeErrorResponse(ErrorCode errorCode, String message){
        //메세지를 유연하게 바꿀 수 있는 메소드
        //에러 발생 시 코드와 메세지를 응답하기 위한 메소드
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(message)
                .build();
    }
}

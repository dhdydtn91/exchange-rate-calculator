package wooahan.youth.exchange.common.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wooahan.youth.exchange.common.exception.BusinessException;
import wooahan.youth.exchange.common.exception.ErrorCode;

@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    /**
     * http status :200 비지니스 로직에서 예외가 발생한 경우
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<CommonResponse> handleBusinessException(final BusinessException e) {
        log.error("handleBusinessException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final CommonResponse response = CommonResponse.fail(errorCode.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * http status :400 javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final CommonResponse response = CommonResponse.fail(FieldErrors.of(e.getBindingResult()),
                ErrorCode.INVALID_INPUT_VALUE.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * http status :400 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<CommonResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final CommonResponse response = CommonResponse.fail(ErrorCode.METHOD_NOT_ALLOWED.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * http status :500 알 수 없는 시스템 예외 처리
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse> handleException(Exception e) {
        log.error("handleException", e);
        final CommonResponse response = CommonResponse.fail(
                ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

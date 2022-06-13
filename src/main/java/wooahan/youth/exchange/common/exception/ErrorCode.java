package wooahan.youth.exchange.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "유효하지 않는 입력값 입니다."),
    METHOD_NOT_ALLOWED(400, "지원하지 않는 Method 입니다."),
    URL_NOT_FOUND(404, "요청하신 url을 찾지 못했습니다."),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final String message;
    private final int status;


    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}

package wooahan.youth.exchange.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "송급액이 바르지 않습니다."),
    METHOD_NOT_ALLOWED(400, "지원하지 않는 Method 입니다."),
    URL_NOT_FOUND(404, "요청하신 url을 찾지 못했습니다."),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    //Money
    FAILED_PARSE_VALUE(200 , "숫자로 파싱에 실패하였습니다. 해당 문자는 올바른 숫자 포멧이 아닙니다.");

    private final String message;
    private final int status;


    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}

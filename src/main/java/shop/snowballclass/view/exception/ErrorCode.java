package shop.snowballclass.view.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    BAD_REQUEST(400, "BAD_REQUEST"),
    INVALID_INPUT_VALUE(400, "Invalid Input Value"),
    INVALID_TYPE_VALUE(400, "Invalid Type Value"),
    INVALID_SIGNATURE_TOKEN(401, "토큰이 유효하지 않습니다."),
    INVALID_TOKEN(401, "올바르지 않은 토큰입니다."),
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다."),
    UNAUTHENTICATED_ACCESS(401, "해당 리소스에 접근하려면 인증이 필요합니다."),
    ACCESS_DENIED(403, "해당 리소스에 접근할 수 있는 권한이 부족합니다."),
    ENTITY_NOT_FOUND(404, "Entity Not Found"),
    RESOURCE_NOT_FOUND(404, "Resource not found"),
    METHOD_NOT_ALLOWED(405, "Invalid Method"),
    INTERNAL_SERVER_ERROR(500, "Server Error"),



    // client
    EVENT_NOT_FOUND(404, "Event not found"),
    LESSON_NOT_FOUND(404, "Lesoon not found"),
    ;

    private final String message;
    private int status;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    public int getStatus() {
        return status;
    }


}

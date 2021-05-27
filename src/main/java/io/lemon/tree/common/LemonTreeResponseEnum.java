package io.lemon.tree.common;

public enum LemonTreeResponseEnum {

    SUCCESS(2000, "OK"),
    BAD_REQUEST(4000, "Bad Request"),
    PARAM_ERROR(1001, "parameter error"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private Integer code;

    private String message;

    LemonTreeResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

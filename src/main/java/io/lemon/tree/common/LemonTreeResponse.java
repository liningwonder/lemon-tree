package io.lemon.tree.common;

import lombok.Data;

@Data
public class LemonTreeResponse {

    private int code;

    private String message;

    private Object data;

    private LemonTreeResponse(LemonTreeResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    private LemonTreeResponse(LemonTreeResponseEnum responseEnum, Object data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }

    public static LemonTreeResponse success() {
        return new LemonTreeResponse(LemonTreeResponseEnum.SUCCESS);
    }

    public static LemonTreeResponse success(Object data) {
        return new LemonTreeResponse(LemonTreeResponseEnum.SUCCESS, data);
    }

    public static LemonTreeResponse failure() {
        return new LemonTreeResponse(LemonTreeResponseEnum.BAD_REQUEST);
    }

    public static LemonTreeResponse failure(Object data) {
        return new LemonTreeResponse(LemonTreeResponseEnum.BAD_REQUEST, data);
    }

    public static LemonTreeResponse construct(LemonTreeResponseEnum responseEnum) {
        return new LemonTreeResponse(responseEnum);
    }

    public static LemonTreeResponse construct(LemonTreeResponseEnum responseEnum, Object data) {
        return new LemonTreeResponse(responseEnum, data);
    }
}

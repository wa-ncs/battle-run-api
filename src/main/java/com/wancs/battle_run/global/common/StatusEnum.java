package com.wancs.battle_run.global.common;

public enum StatusEnum {
    OK(200, "OK"),
    CREATED(201, "CREATED"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERER_ERROR(500, "INTERNAL_SERER_ERROR");

    Integer statusCode;
    String code;

    StatusEnum(Integer statusCode, String code){
        this.statusCode = statusCode;
        this.code = code;
    }
}

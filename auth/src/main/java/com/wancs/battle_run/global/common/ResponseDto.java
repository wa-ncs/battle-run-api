package com.wancs.battle_run.global.common;

import com.wancs.battle_run.global.common.StatusEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// ToDo: 추후에 AccessLevel PROTECTED로 변경필요
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseDto<T> {
    private StatusEnum code;
    private T data;
    private String message;

    @Builder
    public ResponseDto(StatusEnum code, T data, String message){
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Integer getCode() {
        return code.statusCode;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}

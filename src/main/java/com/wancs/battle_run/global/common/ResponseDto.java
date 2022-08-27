package com.wancs.battle_run.global.common;

import com.wancs.battle_run.global.common.StatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
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
}

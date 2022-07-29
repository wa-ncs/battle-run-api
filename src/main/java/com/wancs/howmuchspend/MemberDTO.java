package com.wancs.howmuchspend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    @JsonProperty
    private String name;

    public void memberDTO() {}
    public void memberDTO(String name) {
        this.name = name;
    }
}

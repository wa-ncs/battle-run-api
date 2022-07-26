package com.wancs.howmuchspend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/jh")
    public String helloWorld(){
        return "안녕 난 방지훈이야!";
    }

}

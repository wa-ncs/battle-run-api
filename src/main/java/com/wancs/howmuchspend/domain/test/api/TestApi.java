package com.wancs.howmuchspend.domain.test.api;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/test"})
public class TestApi {
    @Autowired
    private Environment env;

    @GetMapping("")
    public String helloWorld() {

        System.out.println(env.getProperty("TEST"));
        System.out.println(env.getProperty("TEST2"));

        return "테스트 페이지 입니다.3";
    }
}

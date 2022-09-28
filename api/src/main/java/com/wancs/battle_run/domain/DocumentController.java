package com.wancs.battle_run.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentController {

    @GetMapping("docs")
    public String document() {
        return "docs";
    }
}

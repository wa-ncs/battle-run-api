package com.wancs.howmuchspend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public Map<String, Object> findAll() {
        Map<String, Object> response = new HashMap<>();
        response.put("data",memberService.findAll());
        return response;
    }
    @GetMapping("/{id}")
    public Map<String, Object> find(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("data",memberService.find(id));
        return response;
    }

    @PostMapping("")
    public Map<String, Object> save(@RequestBody MemberDTO member) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", memberService.save(member));
        return response;
    }
    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable("id") Long id,@RequestBody MemberDTO member) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", memberService.update(id, member));
        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> update(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        memberService.delete(id);
        response.put("data", "success");
        return response;
    }
}

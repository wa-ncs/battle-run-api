package com.wancs.howmuchspend.controller;

import com.wancs.howmuchspend.domain.posts.Posts;
import com.wancs.howmuchspend.dto.PostsDeleteRequestDto;
import com.wancs.howmuchspend.dto.PostsResponseDto;
import com.wancs.howmuchspend.dto.PostsSaveRequestDto;
import com.wancs.howmuchspend.dto.PostsUpdateRequestDto;
import com.wancs.howmuchspend.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@RequestBody PostsDeleteRequestDto requestDto){
        return postsService.deletePost(requestDto.getId());
    }
}

package com.wancs.howmuchspend.controller;

import com.wancs.howmuchspend.domain.posts.Posts;
import com.wancs.howmuchspend.dto.PostsDeleteRequestDto;
import com.wancs.howmuchspend.dto.PostsResponseDto;
import com.wancs.howmuchspend.dto.PostsSaveRequestDto;
import com.wancs.howmuchspend.dto.PostsUpdateRequestDto;
import com.wancs.howmuchspend.service.posts.PostsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"게시글의 전반적인 API를 기술했음"})
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @ApiOperation(value ="일단 이 url로 post로 호출하고 body에 담아서 저장하는 메서드")
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @ApiOperation(value="아이디를 붙여서 body에 변경할 값 넣으면 바꿔주는 메서드")
    @ApiImplicitParam(name = "id", value ="수정할 게시글 아이디", required = true, dataType = "long")
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

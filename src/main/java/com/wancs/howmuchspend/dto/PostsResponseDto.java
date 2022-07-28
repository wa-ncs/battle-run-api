package com.wancs.howmuchspend.dto;

import com.wancs.howmuchspend.domain.posts.Posts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value="PostsResponseDto : 조회할 게시글 정보")
@Getter
public class PostsResponseDto {

    private Long id;

    @ApiModelProperty(value="게시글의 제목", example = "이직을 하고싶습니다. 그런데...", required = true)
    private String title;
    @ApiModelProperty(value="게시글의 내용", example = "코드몽키입니다 개망했쥬", required = true)
    private String content;
    @ApiModelProperty(value="게시글의 저자", example = "김영규", required = true)
    private String author;

    public PostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }
}

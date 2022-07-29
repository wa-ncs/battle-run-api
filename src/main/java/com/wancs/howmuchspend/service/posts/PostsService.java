package com.wancs.howmuchspend.service.posts;

import com.wancs.howmuchspend.domain.posts.Posts;
import com.wancs.howmuchspend.domain.posts.PostsRepository;
import com.wancs.howmuchspend.dto.PostsResponseDto;
import com.wancs.howmuchspend.dto.PostsSaveRequestDto;
import com.wancs.howmuchspend.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //추삭수에는 트랜잭션이 필요하다.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        return new PostsResponseDto(posts);
    }

    @Transactional
    public Long deletePost(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= "+id));
        postsRepository.delete(posts);
        return posts.getId();
    }

}

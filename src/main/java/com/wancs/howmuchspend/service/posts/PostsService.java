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

    //추삭수에 트랜잭션이 모두 걸리는걸로 아는데 얘는 왜 안걸었을까?
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.setTitle(requestDto.getTitle());
        posts.setContent(requestDto.getContent());
        postsRepository.save(posts);
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));
        return new PostsResponseDto(posts);
    }

    public Long deletePost(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= "+id));
        postsRepository.delete(posts);
        return posts.getId();
    }

}

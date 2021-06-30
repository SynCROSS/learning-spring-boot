package com.syncross.learningspring.service.posts;

import com.syncross.learningspring.domain.posts.Posts;
import com.syncross.learningspring.domain.posts.PostsRepository;
import com.syncross.learningspring.web.dto.PostsListResponseDto;
import com.syncross.learningspring.web.dto.SavePostsDto;
import com.syncross.learningspring.web.dto.PostsResponseDto;
import com.syncross.learningspring.web.dto.UpdatePostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    
    @Transactional
    public Long savePost(SavePostsDto savePost) {
        return postsRepository.save(savePost.toEntity()).getId();
    }
    
    @Transactional
    public Long updatePost(Long id, UpdatePostsDto updatePostsDto) {
        Posts posts = postsRepository.findById(id)
                                     .orElseThrow(() -> new IllegalArgumentException(
                                             "해당 게시글이 없습니다. 게시글 id: " + id));
        posts.updatePost(updatePostsDto.getTitle(),
                         updatePostsDto.getContent()
        );
        return id;
    }
    
    @Transactional
    public void deletePost(Long id) {
        Posts posts = postsRepository.findById(id)
                                     .orElseThrow(() -> new IllegalArgumentException(
                                             "해당 게시글이 없습니다. 게시글 id: " + id));
        postsRepository.delete(posts);
    }
    
    public PostsResponseDto findPostsById(Long id) {
        Posts posts = postsRepository.findById(id)
                                     .orElseThrow(() -> new IllegalArgumentException(
                                             "해당 게시글이 없습니다. 게시글 id: " + id));
        
        return new PostsResponseDto(posts);
    }
    
    @Transactional(readOnly = true) // 트랜잭션 범위 유지하면서 조회 기능만 있어 속도가 개선됨
    public List<PostsListResponseDto> findAllPostsOrderByDesc() {
        return postsRepository.findAllPostsOrderByDesc()
                              .stream()
                              .map(PostsListResponseDto::new) // posts -> new PostsListResponseDto(posts)
                              .collect(Collectors.toList());
    }
}
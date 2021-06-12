package com.syncross.learningspring.service.posts;

import com.syncross.learningspring.domain.posts.Posts;
import com.syncross.learningspring.domain.posts.PostsRepository;
import com.syncross.learningspring.web.dto.SavePostsDto;
import com.syncross.learningspring.web.dto.PostsResponseDto;
import com.syncross.learningspring.web.dto.UpdatePostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    public PostsResponseDto findPostsById(Long id) {
        Posts posts = postsRepository.findById(id)
                                     .orElseThrow(() -> new IllegalArgumentException(
                                             "해당 게시글이 없습니다. 게시글 id: " + id));
        
        return new PostsResponseDto(posts);
    }
}
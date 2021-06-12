package com.syncross.learningspring.service.posts;

import com.syncross.learningspring.domain.posts.PostsRepository;
import com.syncross.learningspring.web.dto.SavePostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    
    @Transactional
    public Long savePost(SavePostsDto savePost){
        return postsRepository.save(savePost.toEntity()).getId();
    }
}
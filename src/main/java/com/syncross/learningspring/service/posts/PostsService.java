package com.syncross.learningspring.service.posts;

import com.syncross.learningspring.domain.posts.PostsRepository;
import com.syncross.learningspring.web.dto.PostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    
    @Transactional
    public Long savePost(PostsDto postsDto){
        return postsRepository.save(postsDto.toEntity()).getId();
    }
}
package com.syncross.learningspring.web;

import com.syncross.learningspring.service.posts.PostsService;
import com.syncross.learningspring.web.dto.PostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsController {
    private final PostsService postsService;
    
    @PostMapping("api/v1/posts")
    public Long savePost(@RequestBody PostsDto postsDto){
        return postsService.savePost(postsDto);
    }
}
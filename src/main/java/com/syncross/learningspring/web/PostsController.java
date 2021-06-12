package com.syncross.learningspring.web;

import com.syncross.learningspring.service.posts.PostsService;
import com.syncross.learningspring.web.dto.SavePostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController
public class PostsController {
    private final PostsService postsService;
    
    @PostMapping("api/v1/posts")
    public Long savePost(@RequestBody SavePostsDto savePostsDto){
        return postsService.savePost(savePostsDto);
    }
    
    @PutMapping("/api/v1/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody ){
        return
    }
}
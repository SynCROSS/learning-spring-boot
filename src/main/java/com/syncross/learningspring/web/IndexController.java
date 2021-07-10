package com.syncross.learningspring.web;

import com.syncross.learningspring.config.auth.dto.SessionUser;
import com.syncross.learningspring.config.auth.LoggedInUser;
import com.syncross.learningspring.service.posts.PostsService;
import com.syncross.learningspring.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    
    @GetMapping("/")
    public String index(Model model, @LoggedInUser SessionUser sessionUser) {
        model.addAttribute("posts", postsService.findAllPostsOrderByDesc());
        
        if (sessionUser != null) {
            model.addAttribute("userName", sessionUser.getName());
        }
        return "index";
    }
    
    @GetMapping("posts/save")
    public String savePosts() {
        return "SavePosts";
    }
    
    @GetMapping("posts/update/{id}")
    public String updatePosts(@PathVariable Long id, Model model) {
        PostsResponseDto postsResponseDto = postsService.findPostsById(id);
        model.addAttribute("post", postsResponseDto);
        return "UpdatePosts";
    }
}
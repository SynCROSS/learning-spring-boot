package com.syncross.learningspring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.syncross.learningspring.service.posts.PostsService;
import org.springframework.ui.Model;

@Controller
public class IndexController {
    private PostsService postsService;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllPostsOrderByDesc());
        return "index";
    }
    
    @GetMapping("posts/save")
    public String savePosts() {
        return "SavePosts";
    }
}
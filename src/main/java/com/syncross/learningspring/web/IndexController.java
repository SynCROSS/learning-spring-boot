package com.syncross.learningspring.web;

import com.syncross.learningspring.config.auth.dto.SessionUser;
import com.syncross.learningspring.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.syncross.learningspring.service.posts.PostsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllPostsOrderByDesc());
    
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if (sessionUser!=null){
            model.addAttribute("userName", sessionUser.getName());
        }
        return "index";
    }
    
    @GetMapping("posts/save")
    public String savePosts() {
        return "SavePosts";
    }
    
    @GetMapping("posts/update/{id}")
    public String updatePosts(@PathVariable Long id, Model model){
        PostsResponseDto postsResponseDto = postsService.findPostsById(id);
        model.addAttribute("post", postsResponseDto);
        return "UpdatePosts";
    }
}
package com.syncross.learningspring.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syncross.learningspring.domain.posts.Posts;
import com.syncross.learningspring.domain.posts.PostsRepository;
import com.syncross.learningspring.web.dto.SavePostsDto;
import com.syncross.learningspring.web.dto.UpdatePostsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsControllerTest {
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @Autowired
    private PostsRepository postsRepository;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    private MockMvc mvc;
    
    @BeforeEach
    public void setupMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                             .apply(springSecurity())
                             .build();
    }
    
    @AfterEach
    public void deleteAllPosts() throws Exception {
        postsRepository.deleteAll();
    }
    
    @Test
    @WithMockUser(roles = "USER")
    public void addPostTest() throws Exception {
        String title = "title";
        String content = "content";
        
        SavePostsDto savePostsDto = SavePostsDto.builder()
                                                .title(title)
                                                .content(content)
                                                .author("SynCROSS")
                                                .build();
        
        final String URL = "http://localhost:" + port + "/api/v1/posts";
        
        mvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                             .content(new ObjectMapper().writeValueAsString(savePostsDto)))
           .andExpect(status().isOk());
        
        List<Posts> allPosts = postsRepository.findAll();
        
        assertThat(allPosts.get(0).getTitle()).isEqualTo(title);
        assertThat(allPosts.get(0).getContent()).isEqualTo(content);
        
    }
    
    @Test
    @WithMockUser(roles = "USER")
    public void updatePostTest() throws Exception {
        String title = "title";
        String content = "content";
        
        Posts savedPost = postsRepository.save(Posts.builder()
                                                    .title(title)
                                                    .content(content)
                                                    .author("SynCROSS")
                                                    .build());
        
        Long updateTargetId = savedPost.getId();
        
        String updatedTitle = "title2";
        String updatedContent = "content2";
        
        UpdatePostsDto updatePostsDto = UpdatePostsDto.builder()
                                                      .title(updatedTitle)
                                                      .content(updatedContent)
                                                      .build();
        
        final String URL = "http://localhost:" +
                           port +
                           "/api/v1/posts/" +
                           updateTargetId;
    
        mvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON)
                             .content(new ObjectMapper().writeValueAsString(updatePostsDto)))
           .andExpect(status().isOk());
        
        List<Posts> allPosts = postsRepository.findAll();
        
        assertThat(allPosts.get(0).getTitle()).isEqualTo(updatedTitle);
        assertThat(allPosts.get(0).getContent()).isEqualTo(updatedContent);
    }
}
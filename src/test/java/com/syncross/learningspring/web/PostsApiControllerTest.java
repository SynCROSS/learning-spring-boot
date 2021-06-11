package com.syncross.learningspring.web;

import com.syncross.learningspring.domain.posts.Posts;
import com.syncross.learningspring.domain.posts.PostsRepository;
import com.syncross.learningspring.web.dto.PostsDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @Autowired
    private PostsRepository postsRepository;
    
    @AfterEach
    public void deleteAllPosts() throws Exception {
        postsRepository.deleteAll();
    }
    
    @Test
    public void addPost() throws Exception {
        String title = "title";
        String content = "content";
        
        PostsDto postsDto = PostsDto.builder()
                                    .title(title)
                                    .content(content)
                                    .author("SynCROSS")
                                    .build();
        
        final String URL = "http://localhost:" + port + "/api/v1/posts";
        
        ResponseEntity<Long> responseEntity
                = testRestTemplate.postForEntity(URL, postsDto, Long.class);
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        
        List<Posts> allPosts = postsRepository.findAll();
        
        assertThat(allPosts.get(0).getTitle()).isEqualTo(title);
        assertThat(allPosts.get(0).getContent()).isEqualTo(content);
    }
}
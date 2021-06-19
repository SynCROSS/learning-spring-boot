package com.syncross.learningspring.domain;

import com.syncross.learningspring.domain.posts.Posts;
import com.syncross.learningspring.domain.posts.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    
    @AfterEach // 테스트 이후에 수행할 메소드
    public void cleanUp() {
        postsRepository.deleteAll();
    }
    
    @Test
    public void PostCrudTest() {
        String title = "Test Title";
        String content = "Test Body";
        
        // repository.prototype.save: 데이터 insert/update 수행
        postsRepository.save(Posts.builder()
                                  .title(title)
                                  .content(content)
                                  .author("SynCROSS")
                                  .build());
        
        List<Posts> postsList = postsRepository.findAll(); // 모든 데이터 조회
        
        Posts posts = postsList.get(0);
        
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        
    }
    
    @Test
    public void addBaseTimeEntityTest() {
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                                  .title("title")
                                  .content("content")
                                  .author("SynCROSS")
                                  .build());
        
        List<Posts> postsList = postsRepository.findAll();
        
        Posts posts = postsList.get(0);
        
        LocalDateTime createdAt = posts.getCreatedDate();
        LocalDateTime updatedAt = posts.getModifiedDate();
        
        System.out.println("----------------------------------------" +
                           "\ncreatedAt: " +
                           createdAt +
                           "\nupdatedAt: " +
                           updatedAt +
                           "\n----------------------------------------");
        
        assertThat(createdAt).isAfter(now);
        assertThat(updatedAt).isAfter(now);
    }
}
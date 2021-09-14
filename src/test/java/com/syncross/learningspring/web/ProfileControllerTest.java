package com.syncross.learningspring.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerTest {
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @Test
    public void getProfileWithoutVerification() throws Exception {
        String expectedProfile = "default";
//        String expectedProfile = "oauth";
        
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(
                "/profile",
                String.class
        );
        
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(expectedProfile);
    }
}
package com.syncross.learningspring.web;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {
    @Test
    public void getRealProfile() {
        String expectedProfile = "real";
        
        MockEnvironment mockEnvironment = new MockEnvironment();
        mockEnvironment.addActiveProfile(expectedProfile);
        mockEnvironment.addActiveProfile("oauth");
        mockEnvironment.addActiveProfile("real-db");
        
        ProfileController profileController = new ProfileController(mockEnvironment);
        
        String profile = profileController.profile();
        
        assertThat(profile).isEqualTo(expectedProfile);
    }
    
    @Test
    public void getFirstOneIfThereIsNoRealProfile() {
        String expectedProfile = "oauth";
        
        MockEnvironment mockEnvironment = new MockEnvironment();
        mockEnvironment.addActiveProfile(expectedProfile);
        mockEnvironment.addActiveProfile("real-db");
        
        ProfileController profileController = new ProfileController(mockEnvironment);
        
        String profile = profileController.profile();
        
        assertThat(profile).isEqualTo(expectedProfile);
    }
    
    @Test
    public void getDefaultIfThereIsNoActiveProfile() {
        String expectedProfile = "default";
        
        MockEnvironment mockEnvironment = new MockEnvironment();
        ProfileController profileController = new ProfileController(mockEnvironment);
        
        String profile = profileController.profile();
        
        assertThat(profile).isEqualTo(expectedProfile);
    }
    
}
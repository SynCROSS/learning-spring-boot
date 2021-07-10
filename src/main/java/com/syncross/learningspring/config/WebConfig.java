package com.syncross.learningspring.config;

import com.syncross.learningspring.config.auth.LoggedInUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoggedInUserArgumentResolver loggedInUserArgumentResolver;
    
    // HandlerMethodArgumentResolver는
    // 항상 WebMvcConfigurer의 addArgumentResolvers를 통해 추가해야 함
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loggedInUserArgumentResolver);
    }
}
package com.syncross.learningspring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePostsDto {
    private String title;
    private String content;
    
    @Builder
    public UpdatePostsDto(String title, String content) {
        this.title   = title;
        this.content = content;
    }
}
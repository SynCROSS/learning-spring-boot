package com.syncross.learningspring.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "Guest"), // 권한 코드 앞은 항상 'ROLE'이 붙어야 함.
    USER("ROLE_USER", "End User");
    
    private final String key;
    private final String title;
}
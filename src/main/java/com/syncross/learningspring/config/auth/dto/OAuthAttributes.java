package com.syncross.learningspring.config.auth.dto;

import com.syncross.learningspring.domain.user.Role;
import com.syncross.learningspring.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    
    @Builder
    public OAuthAttributes(
            Map<String, Object> attributes,
            String nameAttributeKey,
            String name,
            String email,
            String picture
    ) {
        this.attributes       = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name             = name;
        this.email            = email;
        this.picture          = picture;
    }
    
    // 반환 정보가 Map이기 때문에 값을 하나하나 변환 해야 함
    public static OAuthAttributes of(
            String registrationId,
            String userNameAttributeName,
            Map<String, Object> attributes
    ) {
        if (registrationId.equals("naver")) {
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }
    
    private static OAuthAttributes ofGoogle(
            String userNameAttributeName, Map<String, Object> attributes
    ) {
        return OAuthAttributes.builder()
                              .name((String) attributes.get("name"))
                              .email((String) attributes.get("email"))
                              .picture((String) attributes.get("picture"))
                              .attributes(attributes)
                              .nameAttributeKey(userNameAttributeName)
                              .build();
    }
    
    private static OAuthAttributes ofNaver(
            String userNameAttributeName, Map<String, Object> attributes
    ) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        
        return OAuthAttributes.builder()
                              .name((String) response.get("name"))
                              .email((String) response.get("email"))
                              .picture((String) response.get("profile_image"))
                              .attributes(response)
                              .nameAttributeKey(userNameAttributeName)
                              .build();
    }
    
    public User toEntity() {
        return User.builder()
                   .name(name)
                   .email(email)
                   .picture(picture)
                   .role(Role.USER)
                   .build();
    }
}
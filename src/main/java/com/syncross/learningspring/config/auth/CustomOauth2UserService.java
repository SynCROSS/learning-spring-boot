package com.syncross.learningspring.config.auth;

import com.syncross.learningspring.config.auth.dto.OAuthAttributes;
import com.syncross.learningspring.config.auth.dto.SessionUser;
import com.syncross.learningspring.domain.user.User;
import com.syncross.learningspring.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;


@RequiredArgsConstructor
@Service
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        
        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 현재 로그인 진행 중인 서비스를 구분하는 코드
        String userNameAttributeName = userRequest.getClientRegistration()
                                                  .getProviderDetails()
                                                  .getUserInfoEndpoint()
                                                  .getUserNameAttributeName(); // OAuth2 로그인 시 키가 되는 필드값
        
        OAuthAttributes oAuthAttributes = OAuthAttributes.of(registrationId,
                                                             userNameAttributeName,
                                                             oAuth2User.getAttributes()
        ); // OAuth2User의 attribute를 담은 클래스
        
        User user = saveOrUpdate(oAuthAttributes);
        
        httpSession.setAttribute("user", new SessionUser(user)); // 로그인된 사용자 정보 클래스, User 클래스 직렬화 방지, 직렬화 기능을 가진 DTO
        
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                oAuthAttributes.getAttributes(),
                oAuthAttributes.getNameAttributeKey()
        );
    }
    
    private User saveOrUpdate(OAuthAttributes oAuthAttributes) {
        User user = userRepository.findByEmail(oAuthAttributes.getEmail())
                                  .map(entity -> entity.updateUser(oAuthAttributes.getName(),
                                                                   oAuthAttributes.getPicture()
                                  ))
                                  .orElse(oAuthAttributes.toEntity());
        return userRepository.save(user);
    }
}
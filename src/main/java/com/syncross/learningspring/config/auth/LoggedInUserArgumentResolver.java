package com.syncross.learningspring.config.auth;

import com.syncross.learningspring.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoggedInUserArgumentResolver implements HandlerMethodArgumentResolver {
    
    private final HttpSession httpSession;
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) { // 특정 파라미터 지원 여부 반환
        boolean isLoggedInUserAnnotation = parameter.getParameterAnnotation(LoggedInUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        
        return isLoggedInUserAnnotation && isUserClass;
    }
    
    @Override
    public Object resolveArgument( // 파라미터에 전달할 객체 생성
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        return httpSession.getAttribute("user");
    }
}
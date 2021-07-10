package com.syncross.learningspring.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션 사용 가능 위치
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 유지 기간을 런타임 중으로 설정
public @interface LoggedInUser {}
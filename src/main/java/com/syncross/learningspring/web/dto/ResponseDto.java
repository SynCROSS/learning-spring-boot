package com.syncross.learningspring.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 GET 메소드를 생성함
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성함
public class ResponseDto {
    private final String name;
    private final int amount;
}

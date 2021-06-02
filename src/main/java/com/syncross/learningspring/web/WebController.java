package com.syncross.learningspring.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러
public class WebController {
    @GetMapping("hello") // Get 요청을 받을 수 있는 주소
    public String hello() {
        return "Hello World";
    }
}
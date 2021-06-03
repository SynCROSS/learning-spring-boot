package com.syncross.learningspring.web;

import com.syncross.learningspring.web.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러
public class WebController {
    @GetMapping("hello") // Get 요청을 받을 수 있는 주소
    public String hello() {
        return "Hello World";
    }

    @GetMapping("hello/dto")
    // @RequestParam: 외부에서 넘긴 파라미터를 가져옴.
    public ResponseDto responseDto (@RequestParam("name") String name,
                                    @RequestParam("amount") int amount){
        return new ResponseDto(name, amount);
    }
}
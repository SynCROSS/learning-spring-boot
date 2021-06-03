package com.syncross.learningspring.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // 확장을 선언적으로 등록하는 데 사용되며 상속됨.
@WebMvcTest(controllers = WebController.class) // MVC 위주의 테스트용. 주로 컨트롤러 쪽
public class WebControllerTest {
    @Autowired // 스프링이 관리하는 빈을 주입받음.
    private MockMvc mvc; // 서버측 스프링 MVC 테스트의 시작점

    @Test
    public void returnHelloWorld() throws Exception {
        String resultValue = "Hello World";

        mvc.perform(get("/hello")) // /hello 주소로 GET 요청을 보냄
           .andExpect(status().isOk()) // 상태값이 200인지 검증
           .andExpect(content().string(resultValue)); // 본문이 resultValue와 맞는지 확인
    }
}

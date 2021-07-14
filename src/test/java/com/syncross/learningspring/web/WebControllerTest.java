package com.syncross.learningspring.web;

import com.syncross.learningspring.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class) // 확장을 선언적으로 등록하는 데 사용되며 상속됨.
@WebMvcTest(controllers = WebController.class,
            excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                                          classes = SecurityConfig.class)
            }) // SecurityConfig를 스캔 대상에서 제외
public class WebControllerTest {
    @Autowired // 스프링이 관리하는 빈을 주입받음.
    private MockMvc mvc; // 서버측 스프링 MVC 테스트의 시작점
    
    @WithMockUser(roles = "USER")
    @Test
    public void returnHelloWorld() throws Exception {
        String resultValue = "Hello World";
        
        mvc.perform(get("/hello")) // /hello 주소로 GET 요청을 보냄
           .andExpect(status().isOk()) // 상태값이 200인지 검증
           .andExpect(content().string(resultValue)); // 본문이 resultValue와 맞는지 확인
    }
    
    @WithMockUser(roles = "USER")
    @Test
    public void returnDto() throws Exception {
        String name = "test";
        int amount = 10;
        
        // param: 파라미터를 설정함, 그러나 문자열이어야 함.
        // jsonpath: JSON 응답을 $를 기준으로 필드별로 검증할 수 있음.
        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
           .andExpect((status().isOk()))
           .andExpect(jsonPath("$.name", is(name)))
           .andExpect(jsonPath("$.amount", is(amount)));
    }
}
package com.syncross.learningspring.web.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ResponseDtoTest {
    @Test
    public void testLombok(){
        String name = "test";
        int amount =1000;

        ResponseDto responseDto = new ResponseDto(name, amount);

        // assertThat: assertj의 검증 메소드
        // isEqualTo: assertj의 동등 비교 메소드
        assertThat(responseDto.getName()).isEqualTo(name);
        assertThat(responseDto.getAmount()).isEqualTo(amount);
    }
}

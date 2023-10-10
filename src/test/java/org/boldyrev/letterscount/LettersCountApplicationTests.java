package org.boldyrev.letterscount;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.boldyrev.letterscount.dao.WordRequestDto;
import org.boldyrev.letterscount.exceptions.ValidationException;
import org.boldyrev.letterscount.models.WordRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LettersCountApplicationTests {

    private final MockMvc mvc;

    private final ObjectMapper mapper;

    @Autowired
    LettersCountApplicationTests(MockMvc mvc) {
        this.mvc = mvc;
        mapper = new ObjectMapper();
    }

    @Test
    void contextLoads() {
    }

    public

    @ParameterizedTest
    @CsvSource(value = {
            "AAAAAAaaaaaBBBBbbbCCc; 'A': 6, 'a': 5, 'B': 4, 'b': 3, 'C': 2, 'c': 1; true",
            "AAAAAAaaaaaBBBBbbbCCc; 'a': 11, 'b': 7, 'c': 3; false"
    }, delimiterString = ";")
    void countLetters_WithCorrectInput_ReturnsCountedLetters(String inputString, String lettersCount, boolean flag) throws Exception {
        var request = new WordRequestDto();
        request.setInputString(inputString);

        HttpHeaders headers = new HttpHeaders();
        headers.add("case-sensitive", Boolean.toString(flag));

        mvc.perform(post("/api/v1/letters-count")
                        .params(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.input_string", Matchers.equalTo(inputString)))
                .andExpect(jsonPath("$.letters_count", Matchers.equalTo(lettersCount)));
    }

    @ParameterizedTest
    @CsvSource(value = {"'     ', null", "'', null", "null, null", "'abc', 'abc'"})
    void countLetters_WithIncorrectInput_ThrowsValidationException(String inputString, String lettersCount) throws Exception {
        var request = new WordRequestDto();
        request.setInputString(inputString);
        request.setLettersCount(lettersCount);

        mvc.perform(post("/api/v1/letters-count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(r -> assertThatExceptionOfType(ValidationException.class));
    }

}

package org.boldyrev.letterscount;

import org.boldyrev.letterscount.dao.WordRequestDto;
import org.boldyrev.letterscount.models.WordRequest;
import org.boldyrev.letterscount.util.mappers.WordRequestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WordRequestMapperTest {

    private final WordRequestMapper mapper;

    @Autowired
    public WordRequestMapperTest(WordRequestMapper mapper) {
        this.mapper = mapper;
    }

    @Test
    void shouldMapWordRequestToDTOCorrectly() {
        var request = new WordRequest();
        request.setInputString("123");
        request.setLettersCount("'1': 1, '2': 1, '3': 1");

        WordRequestDto dto = mapper.mapToWordRequestDto(request);

        assertThat(request.getInputString().equals(dto.getInputString()));
        assertThat(request.getLettersCount().equals(dto.getLettersCount()));
    }

    @Test
    void shouldMapWordRequestDtoToEntityCorrectly() {
        var dto = new WordRequestDto();
        dto.setInputString("123");
        dto.setLettersCount(null);

        WordRequest entity = mapper.mapToWordRequest(dto);

        assertThat(entity.getInputString().equals(dto.getInputString()));
        assertThat(entity.getLettersCount() == null);
    }
}

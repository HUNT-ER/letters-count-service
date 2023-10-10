package org.boldyrev.letterscount;

import org.boldyrev.letterscount.models.WordRequest;
import org.boldyrev.letterscount.services.WordRequestsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WordRequestServiceTest {

    private final WordRequestsService service;

    @Autowired
    public WordRequestServiceTest(WordRequestsService service) {
        this.service = service;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "AAAAAAaaaaaBBBBbbbCCc; 'A': 6, 'a': 5, 'B': 4, 'b': 3, 'C': 2, 'c': 1; true",
            "AAAAAAaaaaaBBBBbbbCCc; 'a': 11, 'b': 7, 'c': 3; false",
            "'                     '; ' ': 21; true",
            "''; ''; true"
    }, delimiterString = ";")
    void countLetters_ReturnsCountedLetters(String inputString, String lettersCount, boolean isCaseSensitive) {
        var request = new WordRequest();
        request.setInputString(inputString);

        var result = service.countLetters(request, isCaseSensitive);

        assertThat(request.getInputString().equals(result.getInputString()));

        assertThat(result.getLettersCount().equals(lettersCount));
    }
}

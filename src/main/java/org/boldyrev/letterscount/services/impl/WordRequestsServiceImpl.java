package org.boldyrev.letterscount.services.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.boldyrev.letterscount.models.WordRequest;
import org.boldyrev.letterscount.services.WordRequestsService;
import org.springframework.stereotype.Service;

@Service
public class WordRequestsServiceImpl implements WordRequestsService {

    @Override
    public WordRequest countLetters(WordRequest wordRequest, boolean isCaseSensitive) {
        var inputString = wordRequest.getInputString();
        var joiner = new StringJoiner(", ");

        if (!isCaseSensitive) {
            inputString = inputString.toLowerCase();
        }

        Arrays.stream(inputString.split(""))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEachOrdered(
                letter -> joiner.add("'%s': %d".formatted(letter.getKey(), letter.getValue())));

        wordRequest.setLettersCount(joiner.toString());

        return wordRequest;
    }

}

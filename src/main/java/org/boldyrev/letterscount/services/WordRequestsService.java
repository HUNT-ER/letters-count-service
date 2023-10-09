package org.boldyrev.letterscount.services;

import org.boldyrev.letterscount.models.WordRequest;
import org.springframework.stereotype.Service;

public interface WordRequestsService {

    WordRequest countLetters(WordRequest wordRequest, boolean isCaseSensitive);
}

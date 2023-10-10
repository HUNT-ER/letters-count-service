package org.boldyrev.letterscount.controllers;

import jakarta.validation.Valid;
import org.boldyrev.letterscount.dao.WordRequestDto;
import org.boldyrev.letterscount.services.WordRequestsService;
import org.boldyrev.letterscount.util.mappers.WordRequestMapper;
import org.boldyrev.letterscount.util.validators.WordRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/letters-count")
public class WordRequestsController {

    private final WordRequestsService wordRequestsService;

    private final WordRequestMapper mapper;

    private final WordRequestValidator validator;

    @Autowired
    public WordRequestsController(WordRequestsService wordRequestsService,
        WordRequestMapper mapper, WordRequestValidator validator) {
        this.wordRequestsService = wordRequestsService;
        this.mapper = mapper;
        this.validator = validator;
    }

    /**
     * POST endpoint для подсчёта символов в строке
     *
     * @param isCaseSensitive флаг для установки режима подсчёта символов с учетом регистра или без (default true)
     * @return подсчитанные символы в строке
     */
    @PostMapping
    public ResponseEntity<WordRequestDto> countLetters(
        @RequestParam(name = "case-sensitive", defaultValue = "true") Boolean isCaseSensitive,
        @RequestBody @Valid WordRequestDto wordRequest, BindingResult bindingResult) {
        validator.validate(wordRequest, bindingResult);

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(mapper.mapToWordRequestDto(
                wordRequestsService.countLetters(mapper.mapToWordRequest(wordRequest),
                    isCaseSensitive)));
    }
}

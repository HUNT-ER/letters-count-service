package org.boldyrev.letterscount.util.mappers;

import org.boldyrev.letterscount.dao.WordRequestDto;
import org.boldyrev.letterscount.models.WordRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordRequestMapper {

    WordRequestDto mapToWordRequestDto(WordRequest wordRequest);

    WordRequest mapToWordRequest(WordRequestDto wordRequest);
}

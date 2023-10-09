package org.boldyrev.letterscount.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class WordRequestDto {

    @NotBlank
    @JsonProperty("input_string")
    private String inputString;

    @Null
    @JsonProperty("letters_count")
    private String lettersCount;
}

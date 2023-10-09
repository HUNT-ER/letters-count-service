package org.boldyrev.letterscount.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WordRequest {

    @NotBlank
    private String inputString;

    private String lettersCount;
}

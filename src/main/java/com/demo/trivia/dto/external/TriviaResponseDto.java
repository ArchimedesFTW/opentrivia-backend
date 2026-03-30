package com.demo.trivia.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TriviaResponseDto(
        @JsonProperty("response_code")
        Integer responseCode,

        TriviaQuestionDto[] results
) {
}

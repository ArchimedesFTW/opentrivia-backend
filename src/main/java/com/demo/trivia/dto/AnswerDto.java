package com.demo.trivia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AnswerDto(
        QuestionDto question,

        @JsonProperty("given_answer")
        String givenAnswer
) {

}

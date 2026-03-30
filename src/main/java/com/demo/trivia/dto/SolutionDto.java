package com.demo.trivia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SolutionDto(
        QuestionDto question,

        @JsonProperty("given_answer")
        String givenAnswer,

        @JsonProperty("correct_answer")
        String correctAnswer
) {

}

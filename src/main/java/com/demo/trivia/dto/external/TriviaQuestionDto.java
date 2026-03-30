package com.demo.trivia.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TriviaQuestionDto(
        String type,
        String difficulty,
        String category,
        String question,

        @JsonProperty("correct_answer")
        String correctAnswer,

        @JsonProperty("incorrect_answers")
        String[] incorrectAnswers
) {

}

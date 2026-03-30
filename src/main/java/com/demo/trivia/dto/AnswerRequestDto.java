package com.demo.trivia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AnswerRequestDto(
        AnswerDto[] answers
) {

}

package com.demo.trivia.dto;


import jakarta.validation.constraints.NotNull;

public record AnswerDto(
        @NotNull
        long questionId,

        @NotNull
        int selectedIndex
) {

}

package com.demo.trivia.dto;


import jakarta.validation.constraints.NotNull;

public record SolutionDto(
        @NotNull
        QuestionDto question,

        @NotNull
        int selectedIndex,

        @NotNull
        int correctIndex
) {

}

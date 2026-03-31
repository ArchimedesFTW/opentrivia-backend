package com.demo.trivia.mapper;

import com.demo.trivia.dto.QuestionDto;
import com.demo.trivia.dto.external.TriviaQuestionDto;
import com.demo.trivia.entity.Question;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class OpenTriviaMapper {
    private OpenTriviaMapper() {
    }

    public static List<Question> toQuestionEntity(TriviaQuestionDto[] response) {
        return Arrays.stream(response)
                .map(dto -> new Question(
                        dto.type(),
                        dto.difficulty(),
                        dto.category(),
                        dto.question(),
                        dto.correctAnswer(),
                        dto.incorrectAnswers()
                ))
                .toList();
    }

    public static QuestionDto toQuestionDto(Question question) {
        // Merge answers and return them in sorted order
        // Sort so correct answer is ambiguously placed.
        // Reverse so that the order of answers is: <True> <False> instead of <False> <True>
        String[] answers = Stream.concat(
                        Stream.of(question.getCorrectAnswer()),
                        Arrays.stream(question.getIncorrectAnswers())
                )
                .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                .toArray(String[]::new);

        return new QuestionDto(
                question.getId(),
                question.getType(),
                question.getDifficulty(),
                question.getCategory(),
                question.getQuestion(),
                answers);
    }

    public static List<QuestionDto> toQuestionDto(List<Question> questions) {
        return questions.stream()
                .map(OpenTriviaMapper::toQuestionDto).toList();
    }
}

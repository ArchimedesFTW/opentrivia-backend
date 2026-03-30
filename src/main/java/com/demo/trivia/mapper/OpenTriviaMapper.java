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

    public static List<QuestionDto> toQuestionDto(List<Question> questions) {
        return questions.stream()
                .map(q -> {
                            // Merge answers and return them in sorted order
                            // Sort so correct answer is ambiguously placed.
                            // Reverse so that the order of answers is: <True> <False> instead of <False> <True>
                            String[] answers = Stream.concat(
                                            Stream.of(q.getCorrectAnswer()),
                                            Arrays.stream(q.getIncorrectAnswers())
                                    )
                                    .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                                    .toArray(String[]::new);

                            return new QuestionDto(
                                    q.getId(),
                                    q.getType(),
                                    q.getDifficulty(),
                                    q.getCategory(),
                                    q.getQuestion(),
                                    answers);
                        }
                ).toList();
    }
}

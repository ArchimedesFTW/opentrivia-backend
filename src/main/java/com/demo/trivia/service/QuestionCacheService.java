package com.demo.trivia.service;

import com.demo.trivia.dto.QuestionDto;
import com.demo.trivia.dto.QuestionResponseDto;
import com.demo.trivia.dto.external.TriviaResponseDto;
import com.demo.trivia.entity.Question;
import com.demo.trivia.external.OpenTriviaBackend;
import com.demo.trivia.mapper.OpenTriviaMapper;
import com.demo.trivia.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.Console;
import java.util.List;


@Service
public class QuestionCacheService {
    private final QuestionRepository questionRepository;
    private final OpenTriviaBackend openTriviaBackend;

    public QuestionCacheService(QuestionRepository questionRepository, OpenTriviaBackend openTriviaBackend) {
        this.questionRepository = questionRepository;
        this.openTriviaBackend = openTriviaBackend;
    }

    public QuestionResponseDto getQuestions(int amount) {
        TriviaResponseDto response = null;
        try {
            response = this.openTriviaBackend.getQuestion(amount);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "OpenTrivia API failed", ex);
        }

        List<Question> questions = OpenTriviaMapper.toQuestionEntity(response.results());

        // Cache them, so we can find them when verifying the correct answer.
        this.questionRepository.saveAll(questions);

        // Convert them to show the possible answers without indication of solution.
        List<QuestionDto> questionDtos = OpenTriviaMapper.toQuestionDto(questions);

        return new QuestionResponseDto(questionDtos.toArray(QuestionDto[]::new));
    }
}

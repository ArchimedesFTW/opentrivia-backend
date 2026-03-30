package com.demo.trivia.external;

import com.demo.trivia.dto.external.TriviaResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

@Service // TODO not a service?
public class OpenTriviaBackend {

    private final String baseURL = "https://opentdb.com/";
    private final RestTemplate restTemplate = new RestTemplate();

    @Retryable(
            retryFor = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000)
    ) // Other exceptions cannot be solved using retry
    public TriviaResponseDto getQuestion(int numQuestions) {
        String url = baseURL + "api.php" + "?amount=" + numQuestions;
        return restTemplate.getForObject(url, TriviaResponseDto.class);
    }

}

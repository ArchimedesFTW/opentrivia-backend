package com.demo.trivia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;


// We need to enable retry config manually, so we can retry 429 too Many Request errors.
@Configuration
@EnableRetry
public class RetryConfig {
}

package com.rlimbu.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping("/circuit-breaker-api")
    //@Retry(name = "circuit-breaker-sample",fallbackMethod = "fallbackResponse")
    @CircuitBreaker(name = "default",fallbackMethod = "fallbackResponse")
    public String sampleApi() {
        log.info("SampleApi call received.");
        ResponseEntity<String> forEntity = new RestTemplate()
                .getForEntity("http://localhost:8080/some-dummy-uri",String.class);
        return forEntity.getBody();
        //return "sample API for Circuit breaker";
    }

    @GetMapping("/circuit-breaker-ratelimit")
    @RateLimiter(name = "default") //let's limit only 4 calls to the sample api in every 10s
    public String rateLimitSample() {
        log.info("circuit-breaker-ratelimit API call received!");
        return "Circuit breaker rate limit response!";
    }

    @GetMapping("/circuit-breaker-bulkhead")
    @Bulkhead(name = "circuit-breaker-bulkhead")
    public String bulkHeadSample() {
        log.info("circuit-breaker-bulkhead API call received!");
        return "circuit-breaker-bulkhead API call response!";
    }

    public String fallbackResponse(Exception ex) {
        return "fallback example";
    }
}

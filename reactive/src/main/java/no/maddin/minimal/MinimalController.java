package no.maddin.minimal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
public class MinimalController {

    @GetMapping(value = "/now/utc", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TimeResponse> currentTimeUTC() {
        log.info("Before time response");
        return Mono.just(TimeResponse.builder()
            .timeString(ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
            .build());
    }

    @GetMapping(value = "/weather/{city}")
    public Mono<WeatherResponse> weather(@PathVariable String city) {
        log.info("Before weather");
        return WebClient.create("https://goweather.herokuapp.com")
            .get()
            .uri("/weather/{city}", city)
            .retrieve()
            .bodyToMono(WeatherResponse.class)
            .doOnTerminate(() -> log.info("After weather"));
    }
}

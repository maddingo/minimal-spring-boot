package no.maddin.minimal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
public class MinimalController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/now/utc", produces = MediaType.APPLICATION_JSON_VALUE)
    public TimeResponse currentTimeUTC() {
        log.info("Before time response");
        return TimeResponse.builder()
            .timeString(ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
            .build();
    }

    @GetMapping(value = "/weather/{city}")
    public ResponseEntity<WeatherResponse> weather(@PathVariable String city) {
        log.info("Before weather");
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity("https://goweather.herokuapp.com/weather/{city}", WeatherResponse.class, city);
        log.info("After weather");

        return response;
    }
}

package no.maddin.minimal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class MinimalController {
    @GetMapping(value = "/now/utc", produces = MediaType.APPLICATION_JSON_VALUE)
    public TimeResponse currentTimeUTC() {
        return TimeResponse.builder()
            .timeString(ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
            .build();
    }
}

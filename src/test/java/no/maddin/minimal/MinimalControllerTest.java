package no.maddin.minimal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MinimalControllerTest {

    @LocalServerPort
    int serverPort;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void timeResponse() {
        ResponseEntity<TimeResponse> response = restTemplate.getForEntity("http://localhost:" + serverPort + "/now/utc", TimeResponse.class);

        assertThat(response, allOf(
            hasProperty("statusCode", equalTo(HttpStatus.OK)),
            hasProperty("body", hasProperty("timeString", instanceOf(String.class)))
        ));
    }

    @ParameterizedTest
    @ValueSource(strings = {"London", "Berlin"})
    void weatherResponse(String city) {

        log.info("TEST before weather");
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity("http://localhost:" + serverPort + "/weather/" + city, WeatherResponse.class);
        log.info("TEST after weather");

        assertThat(response, allOf(
            hasProperty("statusCode", equalTo(HttpStatus.OK)),
            hasProperty("body", hasProperty("temperature", containsString("°C")))
        ));
    }
}

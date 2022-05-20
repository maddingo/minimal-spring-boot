package no.maddin.minimal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
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
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MinimalControllerTest {

    @LocalServerPort
    int serverPort;

    @Test
    void timeResponse() {
        WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:" + serverPort).build();

        client.get()
            .uri("/now/utc")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(TimeResponse.class).value(hasProperty("timeString", instanceOf(String.class)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"London", "Berlin"})
    void weatherResponse(String city) {

        WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:" + serverPort).build();

        log.info("TEST before weather");
        client.get()
            .uri("/weather/{city}", city)
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(WeatherResponse.class)
            .value(hasProperty("temperature", containsString("°C")));
    }
}

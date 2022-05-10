package no.maddin.minimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MinimalControllerTest {

    @LocalServerPort
    int serverPort;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void timeResponse() {
        ResponseEntity<TimeResponse> timeResponse = restTemplate.getForEntity("http://localhost:" + serverPort + "/now/utc", TimeResponse.class);

        assertThat(timeResponse, allOf(
            hasProperty("statusCode", equalTo(HttpStatus.OK)),
            hasProperty("body", hasProperty("timeString", instanceOf(String.class)))
        ));
    }
}

package no.maddin.minimal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@Slf4j
@EnableWebFlux
public class MinimalApplication {
    public static void main(String... args) {
        SpringApplication.run(MinimalApplication.class, args);
    }

}

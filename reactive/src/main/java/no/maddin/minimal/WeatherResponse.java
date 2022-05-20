package no.maddin.minimal;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class WeatherResponse {
    private String temperature;
    private String wind;
    private String description;

    List<Forecast> forecast;

    @Data
    public static class Forecast {
        private int day;
        private String temperature;
        private String wind;
    }
}

package no.maddin.minimal;

import lombok.extern.jackson.Jacksonized;

@lombok.Data
@lombok.Builder
@Jacksonized
public class TimeResponse {
    String timeString;
}

/* (C) 2024 */
package dev.aoc.starter.internal.aocapi;

import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import lombok.SneakyThrows;

public record PuzzleLoader(String token) {

    private static final String COOKIE_HEADER = com.google.common.net.HttpHeaders.COOKIE;
    private static final Map<String, String> HEADERS =
            Map.of(com.google.common.net.HttpHeaders.ACCEPT, MediaType.PLAIN_TEXT_UTF_8.toString());

    @SneakyThrows
    public String load(PuzzleDetails puzzleDetails) {

        var uriString =
                String.format(
                        "https://adventofcode.com/%s/day/%s/input",
                        puzzleDetails.year(), puzzleDetails.day());
        var requestBuilder =
                HttpRequest.newBuilder(URI.create(uriString)).GET().header(COOKIE_HEADER, token);

        HEADERS.forEach(
                (k, v) -> {
                    requestBuilder.header(k, v);
                });

        var request = requestBuilder.build();

        var client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(1)).build();

        var response = client.send(request, BodyHandlers.ofString());

        Preconditions.checkState(
                response.statusCode() == 200,
                String.format(
                        "Unexpected status code from %s: %d: %s: %s",
                        uriString, response.statusCode(), response.body(), token));

        return Optional.ofNullable(response.body()).orElseThrow();
    }
}

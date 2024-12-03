/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal.aocapi;

import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public record PuzzleAnswerSubmitter(String token) {
    private static final String COOKIE_HEADER =
        com.google.common.net.HttpHeaders.COOKIE;

    private static final Map<String, String> HEADERS = Map.of(
        com.google.common.net.HttpHeaders.ACCEPT,
        MediaType.PLAIN_TEXT_UTF_8.toString(),
        com.google.common.net.HttpHeaders.CONTENT_TYPE,
        MediaType.FORM_DATA.toString()
    );

    @SneakyThrows
    public String submit(PuzzleDetails puzzleDetails, String answer) {
        var uriString = String.format(
            "https://adventofcode.com/%s/day/%s/answer",
            puzzleDetails.year(),
            puzzleDetails.day()
        );
        var form = Map.of(
            "level",
            puzzleDetails.levelString(),
            "answer",
            answer
        );

        Function<String, String> enc = s ->
            URLEncoder.encode(s, StandardCharsets.UTF_8);

        var formBody = form
            .entrySet()
            .stream()
            .map(e ->
                String.format(
                    "%s=%s",
                    enc.apply(e.getKey()),
                    enc.apply(e.getValue())
                )
            )
            .collect(Collectors.joining("&"));

        var requestBuilder = HttpRequest.newBuilder(URI.create(uriString))
            .POST(BodyPublishers.ofString(formBody))
            .header(COOKIE_HEADER, token);

        HEADERS.forEach((k, v) -> {
            requestBuilder.header(k, v);
        });

        var request = requestBuilder.build();

        var client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(1))
            .followRedirects(Redirect.NEVER)
            .build();

        var response = client.send(request, BodyHandlers.ofString());

        Preconditions.checkState(
            response.statusCode() == 200,
            String.format(
                "Unexpected status code from %s: %d: %s: %s",
                uriString,
                response.statusCode(),
                response.body(),
                token
            )
        );

        Pattern pattern = Pattern.compile("<p>(.*?)</p>");
        Matcher matcher = pattern.matcher(response.body());

        if (matcher.find()) {
            return matcher.group(1);
        }

        return response.body();
    }
}

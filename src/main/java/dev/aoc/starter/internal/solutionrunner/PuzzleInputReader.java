/* (C) 2024 */
package dev.aoc.starter.internal.solutionrunner;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Function;
import lombok.SneakyThrows;

public class PuzzleInputReader implements Function<PuzzleDetails, Optional<String>> {

    @Override
    public Optional<String> apply(PuzzleDetails puzzle) {
        var inputStream = Optional.ofNullable(this.getClass().getClassLoader().getResourceAsStream(puzzle.inputPath()));

        return inputStream.map(PuzzleInputReader::readAllBytes);
    }

    @SneakyThrows
    private static String readAllBytes(InputStream is) {
        return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    }
}

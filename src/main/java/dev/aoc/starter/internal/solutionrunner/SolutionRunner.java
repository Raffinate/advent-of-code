/* (C) 2024 */
package dev.aoc.starter.internal.solutionrunner;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.SneakyThrows;

public record SolutionRunner(SolutionContainer solution) implements Runnable {

    @Override
    @SneakyThrows
    public void run() {
        var path = solution().puzzleDetails().inputPath();

        System.out.println("Running solution for Puzzle: " + solution().puzzleDetails().toString());

        var inputStream =
                Optional.ofNullable(this.getClass().getClassLoader().getResourceAsStream(path));

        var inputText =
                new String(inputStream.orElseThrow().readAllBytes(), StandardCharsets.UTF_8);

        var outputText = solution.solution().solve(inputText).toString();

        System.out.println(outputText);
    }
}

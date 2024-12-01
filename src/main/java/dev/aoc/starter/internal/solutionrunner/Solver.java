/* (C) 2024 */
package dev.aoc.starter.internal.solutionrunner;

import dev.aoc.starter.solution.Solution.Puzzle;
import java.util.Collection;
import java.util.function.Function;
import lombok.SneakyThrows;

public record Solver(Collection<SolutionContainer> solutions, PuzzleInputReader puzzleInputReader)
        implements Function<Puzzle, String> {

    @Override
    @SneakyThrows
    public String apply(Puzzle puzzle) {
        //
        var solution = solutions().stream()
                .filter(s -> {
                    return s.puzzleDetails().compareTo(PuzzleDetails.fromPuzzle(puzzle)) == 0;
                })
                .findAny()
                .orElseThrow(() -> {
                    return new RuntimeException(String.format("Could not find solution for: %s", puzzle.toString()));
                });

        var path = puzzle.inputPath().orElse(solution.puzzleDetails().inputPath());

        var resolvedDetails = new PuzzleDetails(puzzle.year(), puzzle.day(), puzzle.puzzleNumber(), path);

        System.out.println("Running solution for Puzzle: " + resolvedDetails);

        var reader = new PuzzleInputReader();

        var inputText = reader.apply(resolvedDetails)
                .orElseThrow(
                        () -> new IllegalStateException("Could not read input for: " + resolvedDetails.toString()));

        var outputText = solution.solution().solve(inputText).toString();

        System.out.println("Answer: " + outputText);

        return outputText;
    }
}

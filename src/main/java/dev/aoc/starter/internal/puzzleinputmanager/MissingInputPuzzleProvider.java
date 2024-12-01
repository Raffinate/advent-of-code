/* (C) 2024 */
package dev.aoc.starter.internal.puzzleinputmanager;

import dev.aoc.starter.internal.aocapi.PuzzleLoader;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import dev.aoc.starter.internal.solutionrunner.PuzzleInputReader;
import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public record MissingInputPuzzleProvider(
    Collection<SolutionContainer> solutions,
    PuzzleInputReader reader,
    PuzzleLoader loader
)
    implements Callable<List<PuzzleDetails>> {
    @Override
    public List<PuzzleDetails> call() {
        var puzzles = solutions
            .stream()
            .map(SolutionContainer::puzzleDetails)
            .toList();
        return puzzles.stream().filter(p -> !inputExists(p)).toList();
    }

    private boolean inputExists(PuzzleDetails puzzle) {
        var storedData = reader.apply(puzzle);

        if (storedData.isEmpty()) {
            return false;
        }

        var actualData = loader.load(puzzle);

        return Objects.equals(actualData.trim(), storedData.get().trim());
    }
}

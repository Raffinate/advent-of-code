/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal.puzzleinputmanager;

import dev.aoc.starter.internal.aocapi.PuzzleLoader;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import dev.aoc.starter.internal.solutionrunner.PuzzleInputReader;
import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public record MissingInputPuzzleProvider(
    Collection<SolutionContainer> solutions,
    PuzzleInputReader reader,
    PuzzleLoader loader
) {
    public List<PuzzleDetails> find(boolean checkContent) {
        var puzzles = solutions
            .stream()
            .map(SolutionContainer::puzzleDetails)
            .toList();
        return puzzles
            .stream()
            .filter(p -> !inputExists(p, checkContent))
            .toList();
    }

    private boolean inputExists(PuzzleDetails puzzle, boolean checkContent) {
        var storedData = reader.apply(puzzle);

        if (storedData.isEmpty()) {
            return false;
        }

        if (!checkContent) {
            return true;
        }

        var actualData = loader.load(puzzle);

        return Objects.equals(actualData.trim(), storedData.get().trim());
    }
}

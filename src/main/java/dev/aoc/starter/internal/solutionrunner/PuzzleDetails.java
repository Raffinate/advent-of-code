/* (C) 2024 */
package dev.aoc.starter.internal.solutionrunner;

import com.google.common.base.Preconditions;
import dev.aoc.starter.solution.Solution;
import dev.aoc.starter.solution.Solution.Puzzle;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Optional;

public record PuzzleDetails(int year, int day, int puzzleNumber, String inputPath)
        implements Comparable<PuzzleDetails> {

    public static PuzzleDetails fromPuzzle(Puzzle p) {
        var year = p.year() < 1000 ? 2000 + p.year() : p.year();
        var day = p.day();
        var puzzleNumber = p.puzzleNumber();

        var inputPath = p.inputPath().orElseGet(() -> {
            return String.format("puzzle/%04d_%02d_%01d.txt", year, day, puzzleNumber);
        });

        return new PuzzleDetails(year, day, puzzleNumber, inputPath);
    }

    public static PuzzleDetails fromSolution(Solution solution) {

        var puzzle = solution.puzzle().orElseGet(() -> {
            var className = solution.getClass().getCanonicalName();
            var digits = StringExtractionUtils.extractNonNegativeIntegers(className);
            Preconditions.checkState(
                    digits.size() >= 3,
                    MessageFormat.format(
                            "Class {1} must contain year, day and" + " puzzleNumber in canonical name.", className));

            digits = digits.reversed();
            var year = digits.get(2);
            var day = digits.get(1);
            var puzzleNumber = digits.get(0);
            return new Puzzle(year, day, puzzleNumber, Optional.empty());
        });

        return PuzzleDetails.fromPuzzle(puzzle);
    }

    public String yearString() {
        return String.format("%04d", year);
    }

    public String dayString() {
        return String.format("%02d", day);
    }

    public String puzzleNumberString() {
        return String.format("%01d", puzzleNumber);
    }

    @Override
    public int compareTo(PuzzleDetails that) {
        var comparator = Comparator.comparingInt(PuzzleDetails::year)
                .thenComparingInt(PuzzleDetails::day)
                .thenComparingInt(PuzzleDetails::puzzleNumber);

        return comparator.compare(this, that);
    }
}

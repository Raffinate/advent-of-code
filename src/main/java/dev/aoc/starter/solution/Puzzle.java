package dev.aoc.starter.solution;

import java.text.MessageFormat;
import java.util.Comparator;

import com.google.common.base.Preconditions;

import dev.aoc.starter.internal.solutionrunner.StringExtractionUtils;

public record Puzzle(
        int year,
        int day,
        int puzzle,
        String inputPath) implements Comparable<Puzzle> {

    public static Puzzle fromSolution(Solution solution) {
        var className = solution.getClass().getCanonicalName();
        return solution.puzzle().orElseGet(() -> {
            var digits = StringExtractionUtils.extractNonNegativeIntegers(className);
            Preconditions.checkState(
                    digits.size() >= 3,
                    MessageFormat.format(
                            "Class {1} must contain year, day and puzzle numbers.",
                            className));

            digits.reversed();

            var year = digits.get(2);
            if (year < 1000) {
                year = 2000 + year;
            }
            var day = digits.get(1);
            var puzzle = digits.get(0);

            var inputPath = String.format("%04d_%02d_%01d.txt", year, day, puzzle);

            return new Puzzle(
                    year,
                    day,
                    puzzle,
                    inputPath);
        });

    }

    public String yearString() {
        return String.format("%04d", year);
    }

    public String dayString() {
        return String.format("%02d", day);
    }

    public String puzzleString() {
        return String.format("%01d", puzzle);
    }

    @Override
    public int compareTo(Puzzle that) {
        var comparator = Comparator.comparingInt(Puzzle::year).thenComparingInt(Puzzle::day)
                .thenComparingInt(Puzzle::puzzle);

        return comparator.compare(this, that);
    }
}

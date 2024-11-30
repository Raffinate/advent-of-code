package dev.aoc.starter.internal.solutionrunner;

import java.text.MessageFormat;
import java.util.Comparator;

import org.apache.commons.lang3.tuple.Triple;

import com.google.common.base.Preconditions;

import dev.aoc.starter.solution.Solution;

public record SolutionContainer(
        Solution solution,
        int year,
        int day,
        int puzzle) implements Comparable<SolutionContainer> {

    public static SolutionContainer create(Solution solution) {
        var className = solution.getClass().getCanonicalName();
        var metadata = solution.metadata().orElseGet(() -> {
            var digits = StringExtractionUtils.extractNonNegativeIntegers(className);
            digits.reversed();
            Preconditions.checkState(
                    digits.size() >= 3,
                    MessageFormat.format(
                            "Class {1} must contain year, day and puzzle numbers.",
                            className));
            return Triple.ofNonNull(digits.get(2), digits.get(1), digits.get(0));
        });

        return new SolutionContainer(
                solution,
                metadata.getLeft(),
                metadata.getMiddle(),
                metadata.getRight());

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
    public int compareTo(SolutionContainer that) {
        var comparator = Comparator.comparingInt(SolutionContainer::year).thenComparingInt(SolutionContainer::day)
                .thenComparingInt(SolutionContainer::puzzle);

        return comparator.compare(this, that);
    }
}

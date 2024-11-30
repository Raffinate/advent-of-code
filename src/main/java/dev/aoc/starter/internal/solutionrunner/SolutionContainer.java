package dev.aoc.starter.internal.solutionrunner;

import java.util.Comparator;

import dev.aoc.starter.solution.Puzzle;
import dev.aoc.starter.solution.Solution;

public record SolutionContainer(
        Solution solution,
        Puzzle puzzle) implements Comparable<SolutionContainer> {

    public static SolutionContainer create(Solution solution) {

        return new SolutionContainer(
                solution,
                Puzzle.fromSolution(solution));

    }

    @Override
    public int compareTo(SolutionContainer that) {
        var comparator = Comparator.comparing(SolutionContainer::puzzle);
        return comparator.compare(this, that);
    }
}

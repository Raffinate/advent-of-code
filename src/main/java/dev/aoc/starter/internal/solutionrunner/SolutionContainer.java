package dev.aoc.starter.internal.solutionrunner;

import java.util.Comparator;

import dev.aoc.starter.solution.Solution;

public record SolutionContainer(
        Solution solution,
        PuzzleDetails puzzleDetails) implements Comparable<SolutionContainer> {

    public static SolutionContainer create(Solution solution) {

        return new SolutionContainer(
                solution,
                PuzzleDetails.fromSolution(solution));

    }

    @Override
    public int compareTo(SolutionContainer that) {
        var comparator = Comparator.comparing(SolutionContainer::puzzleDetails);
        return comparator.compare(this, that);
    }
}

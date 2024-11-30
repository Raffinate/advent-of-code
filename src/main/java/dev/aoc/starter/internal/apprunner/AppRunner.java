package dev.aoc.starter.internal.apprunner;

import java.util.List;
import java.util.stream.Collectors;

import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import dev.aoc.starter.solution.Puzzle;

public record AppRunner(
        List<SolutionContainer> solutions) implements Runnable {

    @Override
    public void run() {

        System.out.println(
                "Solutions: " + solutions.stream().map(SolutionContainer::puzzle).map(Puzzle::inputPath)
                        .collect(Collectors.joining(",")));

    }

}

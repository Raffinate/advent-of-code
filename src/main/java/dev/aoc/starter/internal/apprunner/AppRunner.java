package dev.aoc.starter.internal.apprunner;

import java.util.List;

import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import dev.aoc.starter.internal.solutionrunner.SolutionRunner;

public record AppRunner(
        List<SolutionContainer> solutions) implements Runnable {

    @Override
    public void run() {

        var runner = new SolutionRunner(solutions().get(0));

        runner.run();

    }

}

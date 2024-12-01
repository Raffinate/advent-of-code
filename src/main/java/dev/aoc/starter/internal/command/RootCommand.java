/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal.command;

import picocli.CommandLine.Command;

@Command(
    mixinStandardHelpOptions = true,
    subcommands = {
        SolveCommand.class, DownloadCommand.class, CheckCommand.class,
    },
    description = {
        "CLI to quickly start solving AOC tasks without boilerplate setup.",
        "To run puzzle solution: 'make solve'",
        "To start solving new puzzle: Create Solution implementation class accoring to README.md",
        "To download input for new puzzle: 'make download' or 'make redownload'",
        "To run tests: 'make test'",
        "To add test: see src/test/java/dev/aoc/starter/solution/ExampleTest.java",
    }
)
public class RootCommand {}

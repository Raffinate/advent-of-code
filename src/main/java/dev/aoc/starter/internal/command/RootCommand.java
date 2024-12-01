/* (C) 2024 */
package dev.aoc.starter.internal.command;

import picocli.CommandLine.Command;

@Command(
    mixinStandardHelpOptions = true,
    subcommands = {
        SolveCommand.class, DownloadCommand.class, MissingCommand.class,
    }
)
public class RootCommand {}

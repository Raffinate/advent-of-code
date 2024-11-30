/* (C) 2024 */
package dev.aoc.starter.internal.apprunner;

import picocli.CommandLine.Command;

@Command(
        mixinStandardHelpOptions = true,
        subcommands = {SolveCommand.class})
public class RootCommand {}

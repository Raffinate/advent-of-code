/* (C) 2024 */
package dev.aoc.starter.internal;

import dev.aoc.starter.internal.apprunner.RootCommand;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@AllArgsConstructor
public class CliRunner implements CommandLineRunner, ExitCodeGenerator {

    private final IFactory factory;
    private final RootCommand command;
    private int exitCode;

    @Override
    public int getExitCode() {
        return exitCode;
    }

    @Override
    public void run(String... args) throws Exception {
        exitCode = new CommandLine(command, factory).execute(args);
    }
}

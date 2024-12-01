/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal.command;

import com.google.common.collect.Lists;
import dev.aoc.starter.internal.puzzleinputmanager.MissingInputPuzzleProvider;
import java.util.Collections;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "check",
    mixinStandardHelpOptions = true,
    description = {
        "Checks for puzzles with missing input files.",
        "Use: 'make download' to automatically download input files.",
        "Use: 'make redownload' to automatically download corrupted input files.",
    }
)
public class CheckCommand implements Callable<Integer> {

    @Option(
        names = { "-a", "--all" },
        negatable = true,
        description = "Show all puzzles with missing inputs."
    )
    boolean all;

    @Option(
        names = { "-p", "--print-path" },
        negatable = true,
        description = "Print file path where input is expected."
    )
    boolean printDownloadPath;

    @Option(
        names = { "-d", "--print-download-args" },
        negatable = true,
        description = "Print args to supply to download command."
    )
    boolean printDownloadCommandArgs;

    @Option(
        names = { "-c", "--content-check" },
        negatable = true,
        description = "If TRUE verifies input content in addition to file existance. Requires TOKEN."
    )
    boolean verifyContent;

    MissingInputPuzzleProvider missingInputPuzzleProvider;

    public CheckCommand(MissingInputPuzzleProvider missingInputPuzzleProvider) {
        this.missingInputPuzzleProvider = missingInputPuzzleProvider;
    }

    @Override
    public Integer call() {
        var missing = Lists.newArrayList(
            missingInputPuzzleProvider.find(verifyContent)
        );
        Collections.sort(missing);

        var filtered = all ? missing : missing.stream().limit(1).toList();

        filtered.forEach(p -> {
            if (printDownloadPath) {
                System.out.println(p.inputPath());
            } else if (printDownloadCommandArgs) {
                System.out.println(
                    String.format(
                        "download -y %s -d %s -p %s",
                        p.yearString(),
                        p.dayString(),
                        p.puzzleNumberString()
                    )
                );
            } else {
                System.out.println(p.toPuzzle().toString());
            }
        });
        return 0;
    }
}

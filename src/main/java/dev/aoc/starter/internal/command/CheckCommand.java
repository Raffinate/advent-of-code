/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal.command;

import com.google.common.collect.Lists;
import dev.aoc.starter.internal.puzzleinputmanager.MissingInputPuzzleProvider;
import dev.aoc.starter.internal.puzzleinputmanager.PuzzleInputManager;
import java.util.Collections;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "check",
    mixinStandardHelpOptions = true,
    description = {
        "Checks for puzzles with missing input files.",
        "Use: 'make fix' to automatically download missing input files.",
        "Use: 'make redownload' to re-download all input files.",
    }
)
public class CheckCommand implements Callable<Integer> {

    @Option(
        names = { "-a", "--all" },
        negatable = true,
        description = "Prints all puzzles with missing inputs."
    )
    boolean all;

    @Option(
        names = { "-f", "--fix" },
        negatable = true,
        description = "Download missing puzzle inputs. Requires TOKEN."
    )
    boolean fix;

    @Option(
        names = { "-c", "--content-check" },
        negatable = true,
        description = "If TRUE verifies input content in addition to file presense. Requires TOKEN."
    )
    boolean verifyContent;

    MissingInputPuzzleProvider missingInputPuzzleProvider;
    PuzzleInputManager puzzleInputManager;

    public CheckCommand(
        MissingInputPuzzleProvider missingInputPuzzleProvider,
        PuzzleInputManager puzzleInputManager
    ) {
        this.missingInputPuzzleProvider = missingInputPuzzleProvider;
        this.puzzleInputManager = puzzleInputManager;
    }

    @Override
    public Integer call() {
        var missing = Lists.newArrayList(
            missingInputPuzzleProvider.find(verifyContent)
        );
        Collections.sort(missing);

        var filtered = all ? missing : missing.stream().limit(1).toList();

        filtered.forEach(p -> {
            if (fix) {
                puzzleInputManager.save(p);
                System.out.println(
                    "Fixed input for " + p.toPuzzle().toString()
                );
            } else {
                System.out.println(p.toPuzzle().toString());
            }
        });
        return 0;
    }
}

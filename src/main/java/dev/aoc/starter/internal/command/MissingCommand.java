/* (C) 2024 */
package dev.aoc.starter.internal.command;

import com.google.common.collect.Lists;
import dev.aoc.starter.internal.puzzleinputmanager.MissingInputPuzzleProvider;
import java.util.Collections;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "missing", mixinStandardHelpOptions = true)
public class MissingCommand implements Callable<Integer> {

    @Option(names = "-a", negatable = true)
    boolean all;

    @Option(names = "-p", negatable = true)
    boolean printDownloadPath;

    @Option(names = "-c", negatable = true)
    boolean printDownloadCommandArgs;

    MissingInputPuzzleProvider missingInputPuzzleProvider;

    public MissingCommand(MissingInputPuzzleProvider missingInputPuzzleProvider) {
        this.missingInputPuzzleProvider = missingInputPuzzleProvider;
    }

    @Override
    public Integer call() {
        var missing = Lists.newArrayList(missingInputPuzzleProvider.call());
        Collections.sort(missing);

        var filtered = all ? missing : missing.stream().limit(1).toList();

        filtered.forEach(p -> {
            if (printDownloadPath) {
                System.out.println(p.inputPath());
            } else if (printDownloadCommandArgs) {
                System.out.println(String.format(
                        "download -y %s -d %s -p %s", p.yearString(), p.dayString(), p.puzzleNumberString()));
            } else {
                System.out.println(p.toPuzzle().toString());
            }
        });
        return 0;
    }
}

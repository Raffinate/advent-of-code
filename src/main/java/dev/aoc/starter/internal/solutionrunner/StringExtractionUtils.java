/* (C) 2024 */
package dev.aoc.starter.internal.solutionrunner;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class StringExtractionUtils {

    private record ScannerIntegerIterator(Scanner s)
        implements Iterator<Integer> {
        @Override
        public boolean hasNext() {
            return s.hasNextInt();
        }

        @Override
        public Integer next() {
            return s.nextInt();
        }
    }

    private static Pattern nonDigits = Pattern.compile("[^\\d]+");

    public static List<Integer> extractNonNegativeIntegers(String s) {
        var scanner = new Scanner(s).useDelimiter(nonDigits);
        var scannerIntegerIterator = new ScannerIntegerIterator(scanner);
        return Lists.newArrayList(scannerIntegerIterator);
    }
}

/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest(args = { "solve", "-y", "1001", "-d", "4", "-l", "1" })
@ExtendWith(OutputCaptureExtension.class)
public class SolveDefaultApplicationTest {

    @Autowired
    CliRunner clr;

    @Test
    public void thatCommandLineRunsTasks(CapturedOutput output) {
        assertEquals(0, clr.getExitCode());
        assertEquals(true, output.getOut().contains("Answer: roch0eiH"));
    }
}

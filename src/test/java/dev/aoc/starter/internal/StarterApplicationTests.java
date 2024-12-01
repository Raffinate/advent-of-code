/* (C) 2024 */
package dev.aoc.starter.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest(args = { "solve", "-y", "1001", "-d", "1", "-p", "1" })
@ExtendWith(OutputCaptureExtension.class)
class StarterApplicationTests {

    @Autowired
    CliRunner clr;

    @Test
    public void thatCommandLineRunnerDoesStuff(CapturedOutput output) {
        assertEquals(0, clr.getExitCode());
        assertEquals(true, output.getOut().contains("Answer: hello from test"));
    }
}

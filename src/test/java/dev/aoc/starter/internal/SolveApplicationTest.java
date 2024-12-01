package dev.aoc.starter.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;

// @SpringBootTest(args = { "solve" })
// @ExtendWith(OutputCaptureExtension.class)
public class SolveApplicationTest {

    @Autowired
    CliRunner clr;

    //@Test
    public void thatCommandLineRunsTasks(CapturedOutput output) {
        assertEquals(0, clr.getExitCode());
        assertEquals(true, output.getOut().contains("Answer: roch0eiH"));
    }
}

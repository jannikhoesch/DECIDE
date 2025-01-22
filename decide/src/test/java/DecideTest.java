import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.decide.Decide;

class DecideTest {
    @Test
    void testDecideOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Decide.main(null);

        assertEquals("Hello world!" + System.lineSeparator(), outputStream.toString());

        System.setOut(originalOut);
    }

    @Test
    void testLIC3() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Main.main(null);

        assertEquals("Hello world!" + System.lineSeparator(), outputStream.toString());

        System.setOut(originalOut);
    }

    @Test
    void testLIC3() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Main.main(null);

        assertEquals("Hello world!" + System.lineSeparator(), outputStream.toString());

        System.setOut(originalOut);
    }
}
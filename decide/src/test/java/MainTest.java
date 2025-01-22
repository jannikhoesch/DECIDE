import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.decide.Main;

class MainTest {
    @Test
    void testMainOutput() {
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
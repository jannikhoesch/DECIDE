import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.decide.Decide;
import com.decide.Parameters;
import com.decide.Point;

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
}
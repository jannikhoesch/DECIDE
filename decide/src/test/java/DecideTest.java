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
    void testDecide() {
        Decide.init();
        boolean launchDecision = Decide.DECIDE();
        assertTrue(launchDecision);
        // assertFalse(launchDecision);
    }
}
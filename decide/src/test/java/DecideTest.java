import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.decide.Decide;
import com.decide.Parameters;
import com.decide.Point;

class DecideTest {

    @Test
    void testInit() {
        // Case 1: Invalid number of points
        int numPoints1 = 1;
        Decide decide = Decide.init(numPoints1);
        assertNull(decide);

        // Case 2: Valid number of points
        int numPoints2 = 100;
        decide = Decide.init(numPoints2);
        assertNotNull(decide);
    }

    @Test
    void testDecideLaunchDecision() {
        Decide decide = Decide.init(100);
        boolean launch = Decide.DECIDE();
        assertTrue(launch);
        // assertFalse(launchDecision);
    }
}
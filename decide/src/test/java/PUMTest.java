import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.decide.Decide;
import com.decide.Parameters;
import com.decide.Point;

class PUMTest {
    @Test
    void testPUMOutput() {
        boolean[] CMV = {true, false, true, false}; // Condition Met Vector
        int[][] LCM = { // Logical Connector Matrix
            {1, 2, 777, 1},
            {2, 1, 777, 2},
            {777, 777, 1, 2},
            {1, 2, 2, 1}
        };
        // Expected PUM result
        boolean[][] expectedPUM = {
            {true, true, true, false},
            {true, false, true, false},
            {true, true, true, true},
            {false, false, true, false}
        };

        boolean[][] actualPUM = Decide.PUM(CMV, LCM);
        assertTrue(java.util.Arrays.deepEquals(expectedPUM, actualPUM), "PUM arrays do not match.");
    }
}
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
            {1, 2, 3, 1},
            {2, 1, 3, 2},
            {3, 3, 1, 2},
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

        boolean[] CMV1 = {true, false}; // Condition Met Vector
        int[][] LCM1 = { // Logical Connector Matrix
            {1, 2},
            {2, 3},
        };
        // Expected PUM result
        boolean[][] expectedPUM1 = {
            {true, true},
            {true,  true}
        };

        boolean[][] actualPUM1 = Decide.PUM(CMV1, LCM1);
        assertTrue(java.util.Arrays.deepEquals(expectedPUM1, actualPUM1), "PUM arrays do not match.");


    }
}
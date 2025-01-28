import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.decide.Decide;
import com.decide.Parameters;

class InputTest {

    @Test
    void testInputTrue() {
        Parameters validParams = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        int NUMPOINTS = 10;
        assertTrue(Decide.input_valid(validParams, NUMPOINTS), "Valid input should return true");
    }

    @Test
    void testInputFalse() {
        int NUMPOINTS = 10;

        // Test LENGTH1 < 0
        Parameters params1 = new Parameters(-1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params1, NUMPOINTS), "LENGTH1 < 0 should return false");

        // Test RADIUS1 < 0
        Parameters params2 = new Parameters(1.0, -1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params2, NUMPOINTS), "RADIUS1 < 0 should return false");

        // Test EPSILON >= Math.PI
        Parameters params3 = new Parameters(1.0, 1.0, Math.PI, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params3, NUMPOINTS), "EPSILON >= Math.PI should return false");

        // Test AREA1 < 0
        Parameters params4 = new Parameters(1.0, 1.0, Math.PI / 2, -1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params4, NUMPOINTS), "AREA1 < 0 should return false");

        // Test Q_PTS > NUMPOINTS
        Parameters params5 = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 11, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params5, NUMPOINTS), "Q_PTS > NUMPOINTS should return false");

        // Test QUADS > 3
        Parameters params6 = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 4, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params6, NUMPOINTS), "QUADS > 3 should return false");

        // Test N_PTS < 3
        Parameters params7 = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 2, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params7, NUMPOINTS), "N_PTS < 3 should return false");

        // Test K_PTS > NUMPOINTS - 2
        Parameters params8 = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 9, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params8, NUMPOINTS), "K_PTS > NUMPOINTS - 2 should return false");

        // Test A_PTS + B_PTS > NUMPOINTS - 3
        Parameters params9 = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 4, 4, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params9, NUMPOINTS), "A_PTS + B_PTS > NUMPOINTS - 3 should return false");

        // Test C_PTS + D_PTS > NUMPOINTS - 3
        Parameters params10 = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 3, 3, 1, 1, 2, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params10, NUMPOINTS), "C_PTS + D_PTS > NUMPOINTS - 3 should return false");

        // Test G_PTS < 1
        Parameters params11 = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 0, 1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params11, NUMPOINTS), "G_PTS < 1 should return false");

        // Test LENGTH2 < 0
        Parameters params12 = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, -1.0, 1.0, 1.0);
        assertFalse(Decide.input_valid(params12, NUMPOINTS), "LENGTH2 < 0 should return false");

        // Test RADIUS2 < 0
        Parameters params13 = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, -1.0, 1.0);
        assertFalse(Decide.input_valid(params13, NUMPOINTS), "RADIUS2 < 0 should return false");

        // Test AREA2 < 0
        Parameters params14 = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, -1.0);
        assertFalse(Decide.input_valid(params14, NUMPOINTS), "AREA2 < 0 should return false");
    }
}

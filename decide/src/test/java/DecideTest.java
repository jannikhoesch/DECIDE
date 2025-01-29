import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import com.decide.Decide;
import com.decide.Parameters;
import com.decide.Point;

class DecideTest {

    @Test
    void testDecide() {

        // Case 1: Invalid input
        int numPoints1 = 1;
        Decide decide = new Decide(numPoints1, null, null, null, null);
        assertThrows(IllegalArgumentException.class, () -> decide.DECIDE(), "Invalid input: NUMPOINTS must be >= 3 and <= 100");

        // Case 2: Return false because criteria are not met
        int numPoints2 = 5;
        Point[] points2 = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 4)
        };

        Parameters parameters2 = new Parameters(
                1.0, // LENGTH1
                1.0, // RADIUS1
                0.5, // EPSILON
                1.0, // AREA1
                2, // Q_PTS
                1, // QUADS
                1.0, // DIST
                3, // N_PTS
                1, // K_PTS
                1, // A_PTS
                1, // B_PTS
                1, // C_PTS
                1, // D_PTS
                1, // E_PTS
                1, // F_PTS
                1, // G_PTS
                1.0, // LENGTH2
                1.0, // RADIUS2
                1.0 // AREA2
        );
        int[][] LCM2 = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j <= i; j++) {
                LCM2[i][j] = LCM2[j][i] = 1;
            }
        }
        boolean[] PUV2 = new boolean[15];
        Arrays.fill(PUV2, true);

        Decide decide2 = new Decide(numPoints2, points2, parameters2, LCM2, PUV2);
        assertFalse(decide2.DECIDE(), "Expected DECIDE to return false");

        // Case 3: Return true because criteria are met
        int numPoints3 = 5;
        Point[] points3 = {
            new Point(0, 0),
            new Point(1, 1),
            new Point(2, 2),
            new Point(3, 3),
            new Point(4, 4)
        };

        Parameters parameters3 = new Parameters(
                1.0, // LENGTH1
                1.0, // RADIUS1
                0.5, // EPSILON
                1.0, // AREA1
                2, // Q_PTS
                1, // QUADS
                1.0, // DIST
                3, // N_PTS
                1, // K_PTS
                1, // A_PTS
                1, // B_PTS
                1, // C_PTS
                1, // D_PTS
                1, // E_PTS
                1, // F_PTS
                1, // G_PTS
                1.0, // LENGTH2
                1.0, // RADIUS2
                1.0 // AREA2
        );
        int[][] LCM3 = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j <= i; j++) {
                LCM3[i][j] = LCM3[j][i] = 1;
            }
        }
        boolean[] PUV3 = new boolean[15];
        Arrays.fill(PUV3, false);

        Decide decide3 = new Decide(numPoints3, points3, parameters3, LCM3, PUV3);
        assertTrue(decide3.DECIDE(), "Expected DECIDE to return true");
    }
}
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.decide.LICConditions;
import com.decide.Point;


public class LICConditionsTest {

    @Test
    void testLIC8() {
        // Test Case 1: Points satisfy the condition
        Point[] pointsCase1 = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 1),
                new Point(3, 0),
                new Point(4, 0)
        };
        int A_PTS1 = 1;
        int B_PTS1 = 1;
        double RADIUS1Case1 = 1.0; // The three points will have a circumcircle radius > 1.0
        int numPoints1 = pointsCase1.length;

        assertTrue(
                LICConditions.LIC8(pointsCase1, A_PTS1, B_PTS1, RADIUS1Case1, numPoints1),
                "Expected LIC8 to return true for points that cannot be contained in a circle of radius 1.0"
        );

        // Test Case 2: Points do not satisfy the condition
        Point[] pointsCase2 = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1),
                new Point(0.5, 0.5)
        };
        int A_PTS2 = 1;
        int B_PTS2 = 1;
        double RADIUS1Case2 = 2.0; // All points can be contained within a circle of radius 2.0
        int numPoints2 = pointsCase2.length;

        assertFalse(
                LICConditions.LIC8(pointsCase2, A_PTS2, B_PTS2, RADIUS1Case2, numPoints2),
                "Expected LIC8 to return false for points that can be contained in a circle of radius 2.0"
        );

        // Test Case 3: NUMPOINTS < 5 -> A_PTS + B_PTS > NUMPOINTS - 3
        Point[] pointsCase3 = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1)
        };
        int A_PTS3 = 1;
        int B_PTS3 = 1;
        double RADIUS1Case3 = 1.0;
        int numPoints3 = pointsCase3.length;

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> LICConditions.LIC8(pointsCase3, A_PTS3, B_PTS3, RADIUS1Case3, numPoints3),
                "Expected IllegalArgumentException to be thrown for A_PTS + B_PTS > NUMPOINTS - 3"
        );
        assertEquals("A_PTS + B_PTS must be less than or equal to NUMPOINTS - 3.", exception.getMessage());


        // Test Case 4: Invalid A_PTS or B_PTS values
        Point[] pointsCase4 = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1),
                new Point(2, 2)
        };
        int A_PTS4 = 0; // Invalid value
        int B_PTS4 = 1;
        double RADIUS1Case4 = 1.0;
        int numPoints4 = pointsCase4.length;

        Exception exception1 = assertThrows(
                IllegalArgumentException.class,
                () -> LICConditions.LIC8(pointsCase4, A_PTS4, B_PTS4, RADIUS1Case4, numPoints4),
                "Expected IllegalArgumentException to be thrown for A_PTS < 1"
        );
        assertEquals("A_PTS and B_PTS must each be greater than or equal to 1.", exception1.getMessage());

        // Test Case 5: A_PTS + B_PTS > NUMPOINTS - 3
        Point[] pointsCase5 = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1),
                new Point(2, 2)
        };
        int A_PTS5 = 2;
        int B_PTS5 = 2; // Invalid as A_PTS + B_PTS > NUMPOINTS - 3
        double RADIUS1Case5 = 1.0;
        int numPoints5 = pointsCase5.length;

        Exception exception2 = assertThrows(
                IllegalArgumentException.class,
                () -> LICConditions.LIC8(pointsCase5, A_PTS5, B_PTS5, RADIUS1Case5, numPoints5),
                "Expected IllegalArgumentException to be thrown for A_PTS + B_PTS > NUMPOINTS - 3"
        );
        assertEquals("A_PTS + B_PTS must be less than or equal to NUMPOINTS - 3.", exception2.getMessage());
    }
}

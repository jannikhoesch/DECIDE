import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.decide.LICConditions;
import com.decide.Point;
import com.decide.Decide;
import com.decide.Parameters;


public class LICConditionsTest {

    @Test
    void testLIC3() {
        // Case 1: An area greater than AREA1 exists
        Point[] points1 = {
                new Point(0, 0),
                new Point(4, 0),
                new Point(0, 3),
                new Point(4, 3)
        };
        assertTrue(LICConditions.LIC3(points1, 5.0, points1.length),
                "Expected LIC3 to return true when a valid area is greater than AREA1");

        // Case 2: No area greater than AREA1
        Point[] points2 = {
                new Point(0, 0),
                new Point(2, 0),
                new Point(1, 1),
                new Point(2, 1)
        };
        assertFalse(LICConditions.LIC3(points2, 5.0, points2.length),
                "Expected LIC3 to return false when no area is greater than AREA1");

        // Case 3: Less than 3 points provided
        Point[] points3 = {
                new Point(0, 0),
                new Point(1, 1)
        };
        assertFalse(LICConditions.LIC3(points3, 5.0, points3.length),
                "Expected LIC3 to return false when fewer than 3 points are provided");

        // Case 4: Negative AREA1 should throw an exception
        Point[] points4 = {
                new Point(0, 0),
                new Point(4, 0),
                new Point(0, 3)
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            LICConditions.LIC3(points4, -1.0, points4.length);
        });
        assertEquals("AREA1 must be greater than or equal to 0.", exception.getMessage());
    }


    // LIC1:
    @Test
    void testLIC1True() {
        Point[] points = {new Point(0, 0), new Point(10, 0), new Point(1, 2), new Point(2, 3)};
        double length = 5;
        int numPoints = 4;
        assertTrue(LICConditions.LIC1(points, length, numPoints));

        points = new Point[]{new Point(0, 0), new Point(10, 0), new Point(2, 1)};
        length = 8;
        numPoints = 3;
        assertTrue(LICConditions.LIC1(points, length, numPoints));

        points = new Point[]{new Point(0, 0), new Point(7, 1), new Point(3, 4)};
        length = 6;
        numPoints = 3;
        assertTrue(LICConditions.LIC1(points, length, numPoints));

        points = new Point[]{new Point(0, 0), new Point(20, 0), new Point(30, 0)};
        length = 15;
        numPoints = 3;
        assertTrue(LICConditions.LIC1(points, length, numPoints));
    }

    @Test
    void testLIC1False() {
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(1, 2), new Point(2, 3)};
        double length = 5;
        int numPoints = 4;
        assertFalse(LICConditions.LIC1(points, length, numPoints));

        points = new Point[]{new Point(0, 0), new Point(2, 2), new Point(4, 4)};
        length = 3;
        numPoints = 3;
        assertFalse(LICConditions.LIC1(points, length, numPoints));

        points = new Point[]{new Point(0, 0), new Point(1, 1), new Point(2, 2)};
        length = 1.5;
        numPoints = 3;
        assertFalse(LICConditions.LIC1(points, length, numPoints));

        points = new Point[]{new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(2, 2)};
        length = 4;
        numPoints = 5;
        assertFalse(LICConditions.LIC1(points, length, numPoints));
    }

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

        // Test Case 6: Points are collinear -> satisfy the condition
        Point[] pointsCase6 = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        int A_PTS6 = 1;
        int B_PTS6 = 1;
        double RADIUS1Case6 = 10.0;
        int numPoints6 = pointsCase6.length;

        assertTrue(
                LICConditions.LIC8(pointsCase6, A_PTS6, B_PTS6, RADIUS1Case6, numPoints6),
                "Expected LIC8 to return true for collinear points that cannot be contained in a circle"
        );
    }
}

import org.junit.jupiter.api.Test;

import com.decide.LICConditions;
import com.decide.Decide;
import com.decide.Parameters;
import com.decide.Point;

import static org.junit.jupiter.api.Assertions.*;

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
}

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.decide.LICConditions;
import com.decide.Parameters;
import com.decide.Point;

public class LICConditionsTest {

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

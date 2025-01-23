import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.decide.LICConditions;
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

    @Test
    void testLIC4() {
        //all three points in the window are in different quads and therefore this test should be true
        Point[] points = {new Point(1, 1), new Point(-1,1), new Point(-1,-1), new Point(1,-1)};
        int QUADS = 2;
        int Q_PTS = 3;
        int numPoints = 4;
        boolean result = LICConditions.LIC4(points, QUADS, Q_PTS, numPoints);
        assertEquals(true, result, "the test should be true");

        //number of quads for each window is 2, which is the same as QUADS and therfore this should be false
        Point[] points2 = {new Point(1, 1), new Point(1,1), new Point(1,-1), new Point(1,-1)};
        QUADS = 2;
        Q_PTS = 3;
        numPoints = 4;
        boolean result2 = LICConditions.LIC4(points2, QUADS, Q_PTS, numPoints);
        assertEquals(false, result2, "the test should be true");

        //testing the edge case of (0,0), this test should be true
        Point[] points3 = {new Point(0, 0), new Point(-1,1), new Point(1,-1), new Point(1,-1)};
        QUADS = 2;
        Q_PTS = 3;
        numPoints = 4;
        boolean result3 = LICConditions.LIC4(points3, QUADS, Q_PTS, numPoints);
        assertEquals(true, result3, "the test should be true");
    }
}

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
    void testLIC9() {
        //we need at least 6 points, so this should be false
        Point[] points = {new Point(0, 0), new Point(0, 0)};
        int C_PTS = 2;
        int D_PTS = 2;
        double EPSILON = 0.14;
        int numPoints = 2;
        boolean result = LICConditions.LIC9(points, C_PTS, D_PTS, EPSILON, numPoints);
        assertEquals(false, result, "the test should be false");


        //the first three points should form an angle of pi/2, so this should be true
        Point[] points2 = {new Point(1, 0), new Point(0, 0), new Point(0, 1), new Point(0, 0), new Point(0, 0), new Point(0, 0)};
        int C_PTS2 = 1;
        int D_PTS2 = 1;
        double EPSILON2 = 0.14;
        int numPoints2 = 6;
        boolean result2 = LICConditions.LIC9(points2, C_PTS2, D_PTS2, EPSILON2, numPoints2);
        assertEquals(true, result2, "the test should be true");


        //the 3rd point is on the vertex, and no other combination should give an angle either, so this should be false
        Point[] points3 = {new Point(1, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)};
        int C_PTS3 = 1;
        int D_PTS3 = 1;
        double EPSILON3 = 0.14;
        int numPoints3 = 6;
        boolean result3 = LICConditions.LIC9(points3, C_PTS3, D_PTS3, EPSILON3, numPoints3);
        assertEquals(false, result3, "the test should be false");


        //the 3 first points are on a straight line so this should be false
        Point[] points4 = {new Point(1, 0), new Point(0, 0), new Point(-1, 0), new Point(-1, 0), new Point(0, 0), new Point(0, 0)};
        int C_PTS4 = 1;
        int D_PTS4 = 1;
        double EPSILON4 = 0.14;
        int numPoints4 = 6;
        boolean result4 = LICConditions.LIC9(points4, C_PTS4, D_PTS4, EPSILON4, numPoints4);
        assertEquals(false, result4, "the test should be false");
    }
}

    



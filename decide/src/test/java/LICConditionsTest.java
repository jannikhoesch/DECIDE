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


        // LIC11:
        @Test
        void testLIC11True() {
            Point[] points = {new Point(0, 0), new Point(3, 10), new Point(14, 8), new Point(8, 0), new Point(-3, 10)};
            Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0,
             0, 0, 0, 0, 0, 0, 0, 0, 0, 
             3, 0, 0, 0);
            int numPoints = 5;
            assertTrue(LICConditions.LIC11(points, parameters, numPoints));
        }
        
        
        @Test
        void testLIC11False() {
            // Test Case 1: There is no points i and j where x_j - x_i < 0
            Point[] points = {new Point(0, 0), new Point(3, 10), new Point(14, 8), new Point(28, 0), new Point(10, 10), new Point(100, 100)};
            Parameters parameters = new Parameters(0, 0, 0, 0, 0, 0,
             0, 0, 0, 0, 0, 0, 0, 0, 
             0, 4, 0, 0, 0);
            int numPoints = 6;
            assertFalse(LICConditions.LIC11(points, parameters, numPoints));
        }
    
}



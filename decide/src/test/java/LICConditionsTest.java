import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.decide.LICConditions;
import com.decide.Point;
import com.decide.Parameters;

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
    // LIC6:
    @Test
    void testLIC6True() {
        // Test Case 1: Points with the condition satisfied and perpendicular
        Point[] points1 = {new Point(0, 0), new Point(3, 10), new Point(14, 8), new Point(8, 0)};
        Parameters parameters1 = new Parameters(0, 0, 0, 0, 0, 0,
         5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        int numPoints1 = 4;
        assertTrue(LICConditions.LIC6(points1, parameters1, numPoints1));
        
        // Test Case 2: First and last point of N_PTS are the same
        Point[] points2 = {new Point(0, 0), new Point(4, 3), new Point(0, 0), new Point(0, 4)};
        Parameters parameters2 = new Parameters(0, 0, 0, 0, 0, 0, 
        2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        int numPoints2 = 4;
        assertTrue(LICConditions.LIC6(points2, parameters2, numPoints2));
        
        
        // Test Case 3: Points form a vertical line
        Point[] points3 = {new Point(0, 0), new Point(20, 4), new Point(0, 8), new Point(0, 12)};
        Parameters parameters3 = new Parameters(0, 0, 0, 0, 0, 0, 
        10, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        int numPoints3 = 4;
        assertTrue(LICConditions.LIC6(points3, parameters3, numPoints3));
        
        // Test Case 4: Line segment has derivative 0
        Point[] points4 = {new Point(0, 0), new Point(1, 5), new Point(10, 0), new Point(0, 0)};
        Parameters parameters4 = new Parameters(0, 0, 0, 0, 0, 0, 
        7, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        int numPoints4 = 4;
        assertTrue(LICConditions.LIC6(points4, parameters4, numPoints4));
        
    }
    @Test
    void testLIC6False() {
        // Test Case 1: Points with the condition satisfied and perpendicular
        Point[] points1 = {new Point(0, 0), new Point(3, 10), new Point(14, 8), new Point(8, 0)};
        Parameters parameters1 = new Parameters(0, 0, 0, 0, 0, 0,
         30, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        int numPoints1 = 4;
        assertFalse(LICConditions.LIC6(points1, parameters1, numPoints1));
        
        // Test Case 2: First and last point of N_PTS are the same
        Point[] points2 = {new Point(0, 0), new Point(1, 1), new Point(0, 0), new Point(0, 1)};
        Parameters parameters2 = new Parameters(0, 0, 0, 0, 0, 0, 
        10, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        int numPoints2 = 4;
        assertFalse(LICConditions.LIC6(points2, parameters2, numPoints2));
        
        // Test Case 3: Points form a vertical line
        Point[] points3 = {new Point(0, 0), new Point(0, 4), new Point(0, 8), new Point(0, 12)};
        Parameters parameters3 = new Parameters(0, 0, 0, 0, 0, 0, 
        10, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        int numPoints3 = 4;
        assertFalse(LICConditions.LIC6(points3, parameters3, numPoints3));

        // Test Case 4: Line segment has derivative 0
        Point[] points4 = {new Point(0, 0), new Point(1, 5), new Point(10, 0), new Point(0, 0)};
        Parameters parameters4 = new Parameters(0, 0, 0, 0, 0, 0, 
        11, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        int numPoints4 = 4;
        assertFalse(LICConditions.LIC6(points4, parameters4, numPoints4));
        
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

    @Test
    void testLIC14() {
        //we need at least 6 points, so this should be false
        Point[] points = {new Point(0, 0), new Point(0, 0)};
        int E_PTS = 2;
        int F_PTS = 2;
        double AREA1 = 0.14;
        double AREA2 = 0.14;
        int numPoints = 2;
        assertFalse(LICConditions.LIC14(points, E_PTS, F_PTS, AREA1, AREA2, numPoints));

        //two triangles
        // {(0, 0), (5, 0), (0, 4)} has area 10
        // {(0, 0), (2, 0), (0, 3)} has area 3 
        points = new Point[]{new Point(0, 0), new Point(0, 0), new Point(5, 0), new Point(2, 0), new Point(0, 4), new Point(0, 3)};
        E_PTS = 2;
        F_PTS = 2;
        AREA1 = 9;
        AREA2 = 4;
        numPoints = 6;
        assertTrue(LICConditions.LIC14(points, E_PTS, F_PTS, AREA1, AREA2, numPoints));
        
        AREA1 = 11;
        AREA2 = 4;
        assertFalse(LICConditions.LIC14(points, E_PTS, F_PTS, AREA1, AREA2, numPoints));
        
        AREA1 = 9;
        AREA2 = 2;
        assertFalse(LICConditions.LIC14(points, E_PTS, F_PTS, AREA1, AREA2, numPoints));
    }
}


    



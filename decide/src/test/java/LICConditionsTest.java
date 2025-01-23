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
    
}



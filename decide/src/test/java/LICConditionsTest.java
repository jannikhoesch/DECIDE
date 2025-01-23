import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.decide.LICConditions;
import com.decide.Parameters;

public class LICConditionsTest {
    
    // LIC1:
    @Test
    void testLIC1True() {
        double[][] points = {{0, 0}, {10, 0}, {1, 2}, {2, 3}};
        double length = 5;
        int numPoints = 4;
        assertTrue(LICConditions.LIC1(points, length, numPoints));
    
        points = new double[][]{{0, 0}, {10, 0}, {2, 1}};
        length = 8;
        numPoints = 3;
        assertTrue(LICConditions.LIC1(points, length, numPoints));
    
        points = new double[][]{{0, 0}, {7, 1}, {3, 4}};
        length = 6;
        numPoints = 3;
        assertTrue(LICConditions.LIC1(points, length, numPoints));
    
        points = new double[][]{{0, 0}, {20, 0}, {30, 0}};
        length = 15;
        numPoints = 3;
        assertTrue(LICConditions.LIC1(points, length, numPoints));
    }
    
    
    @Test
    void testLIC1False() {
        double[][] points = {{0, 0}, {1, 0}, {1, 2}, {2, 3}};
        double length = 5;
        int numPoints = 4;
        assertFalse(LICConditions.LIC1(points, length, numPoints));
    
        points = new double[][]{{0, 0}, {2, 2}, {4, 4}};
        length = 3;
        numPoints = 3;
        assertFalse(LICConditions.LIC1(points, length, numPoints));
    
        points = new double[][]{{0, 0}, {1, 1}, {2, 2}};
        length = 1.5;
        numPoints = 3;
        assertFalse(LICConditions.LIC1(points, length, numPoints));
    
        points = new double[][]{{0, 0}, {1, 0}, {1, 1}, {2, 1}, {2, 2}};
        length = 4;
        numPoints = 5;
        assertFalse(LICConditions.LIC1(points, length, numPoints));
    }
    
    
}

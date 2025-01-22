import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.decide.LICConditions;

class LICConditionsTest {
    
    @Test
    void testLIC4() {
        //all three points in the window are in different quads and therefore this test should be true
        double[][] points = {{1,1}, {-1,1}, {-1,-1}, {1,-1}};
        int QUADS = 2;
        int Q_PTS = 3;
        int numPoints = 4;

        boolean result = LICConditions.LIC4(points, QUADS, Q_PTS, numPoints);
        assertEquals(true, result, "the test should be true");


        //number of quads for each window is 2, which is the same as QUADS and therfore this should be false
        double[][] points2 = {{1,1}, {1,1}, {1,-1}, {1,-1}};
        QUADS = 2;
        Q_PTS = 3;
        numPoints = 4;

        boolean result2 = LICConditions.LIC4(points2, QUADS, Q_PTS, numPoints);
        assertEquals(false, result2, "the test should be true");


        //testing the edge case of (0,0), this test should be true
        double[][] points3 = {{0,0}, {-1,1}, {1,-1}, {1,-1}};
        QUADS = 2;
        Q_PTS = 3;
        numPoints = 4;

        boolean result3 = LICConditions.LIC4(points3, QUADS, Q_PTS, numPoints);
        assertEquals(true, result3, "the test should be true");
    }
}

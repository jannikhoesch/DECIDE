import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.decide.LICConditions;
import com.decide.Point;
import com.decide.Decide;
import com.decide.Parameters;


public class LICConditionsTest {

    @Test
    void testLIC0True() {
        // Test Case 1: Distance between consecutive points is greater than LENGTH1
        Point[] points1 = {new Point(0, 0), new Point(10, 0), new Point(1, 2)};
        double length1 = 5;
        int numPoints1 = points1.length;
        assertTrue(LICConditions.LIC0(points1, length1, numPoints1));
    }

    @Test
    void testLIC0False() {
        // Test Case 1: Distance between consecutive points is less than LENGTH1
        Point[] points1 = {new Point(0, 0), new Point(1, 1), new Point(2, 2)};
        double length1 = 5;
        int numPoints1 = points1.length;
        assertFalse(LICConditions.LIC0(points1, length1, numPoints1));

        // Test Case 2: LENGTH1 is negative
        Point[] points2 = {new Point(0, 0), new Point(3, 4), new Point(6, 8)};
        length1 = -1;
        numPoints1 = points2.length;
        assertFalse(LICConditions.LIC0(points2, length1, numPoints1));
    }
    
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

    @Test
    void testLIC6True() {
        // Test Case 1: Points with the condition satisfied and perpendicular
        Point[] points1 = {new Point(0, 0), new Point(3, 10), new Point(14, 8), new Point(8, 0)};
        double dist1 = 5;
        int N_PTS1 = 3;
        int numPoints1 = 4;
        assertTrue(LICConditions.LIC6(points1, dist1, N_PTS1, numPoints1));

        // Test Case 2: First and last point of N_PTS are the same
        Point[] points2 = {new Point(0, 0), new Point(4, 3), new Point(0, 0), new Point(0, 4)};
        double dist2 = 2;
        int N_PTS2 = 3;
        int numPoints2 = 4;
        assertTrue(LICConditions.LIC6(points2, dist2, N_PTS2, numPoints2));

        // Test Case 3: Points form a vertical line
        Point[] points3 = {new Point(0, 0), new Point(20, 4), new Point(0, 8), new Point(0, 12)};
        double dist3 = 10;
        int N_PTS3 = 3;
        int numPoints3 = 4;
        assertTrue(LICConditions.LIC6(points3, dist3, N_PTS3, numPoints3));

        // Test Case 4: Line segment has derivative 0
        Point[] points4 = {new Point(0, 0), new Point(1, 5), new Point(10, 0), new Point(0, 0)};
        double dist4 = 7;
        int N_PTS4 = 4;
        int numPoints4 = 4;
        assertTrue(LICConditions.LIC6(points4, dist4, N_PTS4, numPoints4));
    }

    @Test
    void testLIC6False() {
        // Test Case 1: Points with the condition satisfied and perpendicular
        Point[] points1 = {new Point(0, 0), new Point(3, 10), new Point(14, 8), new Point(8, 0)};
        double dist1 = 30;
        int N_PTS1 = 4;
        int numPoints1 = 4;
        assertFalse(LICConditions.LIC6(points1, dist1, N_PTS1, numPoints1));

        // Test Case 2: First and last point of N_PTS are the same
        Point[] points2 = {new Point(0, 0), new Point(1, 1), new Point(0, 0), new Point(0, 1)};
        double dist2 = 10;
        int N_PTS2 = 3;
        int numPoints2 = 4;
        assertFalse(LICConditions.LIC6(points2, dist2, N_PTS2, numPoints2));

        // Test Case 3: Points form a vertical line
        Point[] points3 = {new Point(0, 0), new Point(0, 4), new Point(0, 8), new Point(0, 12)};
        double dist3 = 10;
        int N_PTS3 = 3;
        int numPoints3 = 4;
        assertFalse(LICConditions.LIC6(points3, dist3, N_PTS3, numPoints3));

        // Test Case 4: Line segment has derivative 0
        Point[] points4 = {new Point(0, 0), new Point(1, 5), new Point(10, 0), new Point(0, 0)};
        double dist4 = 11;
        int N_PTS4 = 4;
        int numPoints4 = 4;
        assertFalse(LICConditions.LIC6(points4, dist4, N_PTS4, numPoints4));
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

    @Test
    void testLIC11True() {
        Point[] points = {new Point(0, 0), new Point(3, 10), new Point(14, 8), new Point(8, 0), new Point(-3, 10)};
        int G_PTS = 3;
        int numPoints = 5;
        assertTrue(LICConditions.LIC11(points, G_PTS, numPoints));
    }

    @Test
    void testLIC11False() {
        // Test Case 1: There is no points i and j where x_j - x_i < 0
        Point[] points = {new Point(0, 0), new Point(3, 10), new Point(14, 8), new Point(28, 0), new Point(10, 10), new Point(100, 100)};
        int G_PTS = 4;
        int numPoints = 6;
        assertFalse(LICConditions.LIC11(points, G_PTS, numPoints));
    }

    @Test
    void testLIC13() {
        // Test Case 1: Conditions are met (radius > RADIUS1 and radius <= RADIUS2)
        Point[] pointsCase1 = {
                new Point(0, 0),
                new Point(4, 0),
                new Point(0, 3),
                new Point(3, 3),
                new Point(5, 5)
        };
        int A_PTS1 = 1;
        int B_PTS1 = 1;
        double RADIUS1Case1 = 2.0;
        double RADIUS2Case1 = 5.0;
        int numPoints1 = pointsCase1.length;

        assertTrue(
                LICConditions.LIC13(pointsCase1, A_PTS1, B_PTS1, RADIUS1Case1, RADIUS2Case1, numPoints1),
                "Expected LIC13 to return true when the conditions (radius > RADIUS1 and radius <= RADIUS2) are met."
        );

        // Test Case 2: Condition not met (no radius > RADIUS1)
        Point[] pointsCase2 = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1),
                new Point(2, 2)
        };
        int A_PTS2 = 1;
        int B_PTS2 = 1;
        double RADIUS1Case2 = 10.0; // Too large to be exceeded
        double RADIUS2Case2 = 5.0;  // Valid radius limit
        int numPoints2 = pointsCase2.length;

        assertFalse(
                LICConditions.LIC13(pointsCase2, A_PTS2, B_PTS2, RADIUS1Case2, RADIUS2Case2, numPoints2),
                "Expected LIC13 to return false when no circumcircle radius exceeds RADIUS1."
        );

        // Test Case 3: Condition not met (no radius <= RADIUS2)
        Point[] pointsCase3 = {
                new Point(0, 0),
                new Point(10, 0),
                new Point(0, 10),
                new Point(10, 10),
                new Point(20, 15)
        };
        int A_PTS3 = 1;
        int B_PTS3 = 1;
        double RADIUS1Case3 = 5.0;  // Valid radius limit
        double RADIUS2Case3 = 2.0;  // Too small to be satisfied
        int numPoints3 = pointsCase3.length;

        assertFalse(
                LICConditions.LIC13(pointsCase3, A_PTS3, B_PTS3, RADIUS1Case3, RADIUS2Case3, numPoints3),
                "Expected LIC13 to return false when no circumcircle radius is less than or equal to RADIUS2."
        );

        // Test Case 4: NUMPOINTS < 5
        Point[] pointsCase4 = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3)
        };
        int A_PTS4 = 1;
        int B_PTS4 = 1;
        double RADIUS1Case4 = 1.0;
        double RADIUS2Case4 = 2.0;
        int numPoints4 = pointsCase4.length;

        assertFalse(
                LICConditions.LIC13(pointsCase4, A_PTS4, B_PTS4, RADIUS1Case4, RADIUS2Case4, numPoints4),
                "Expected LIC13 to return false when NUMPOINTS is less than 5."
        );
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



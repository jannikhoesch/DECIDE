package com.decide;

import com.decide.Parameters;
import com.decide.Point;

public class LICConditions {
    public static boolean evaluateLIC(int licIndex, Point[] points, Parameters parameters, int numPoints){
        switch(licIndex){
            case 0:
                return LIC0(points, parameters.LENGTH1, numPoints);
            case 1:
                return LIC1(points, parameters.RADIUS1, numPoints);
            case 2:
                return LIC2(points, parameters.EPSILON, numPoints);
            case 3:
                return LIC3(points, parameters.AREA1, numPoints);
            case 4:
                return LIC4(points, parameters.QUADS, parameters.Q_PTS, numPoints);
            case 5:
                return LIC5(points, numPoints);
            case 6:
                return LIC6(points, parameters.DIST, parameters.N_PTS, numPoints);
            case 7:
                return LIC7(points, parameters.LENGTH1, parameters.K_PTS, numPoints);
            case 8:
                return LIC8(points, parameters.A_PTS, parameters.B_PTS, parameters.RADIUS1, numPoints);
            case 9:
                return LIC9(points, parameters.C_PTS, parameters.D_PTS, parameters.EPSILON, numPoints);
            case 10:
                return LIC10(points, parameters.E_PTS, parameters.F_PTS, parameters.AREA1, numPoints);
            case 11:
                return LIC11(points, parameters.G_PTS, numPoints);
            case 12:
                return LIC12(points, parameters.K_PTS, parameters.LENGTH1, parameters.LENGTH2, numPoints);
            case 13:
                return LIC13(points, parameters.A_PTS, parameters.B_PTS, parameters.RADIUS1, parameters.RADIUS2, numPoints);
            case 14:
                return LIC14(points, parameters.E_PTS, parameters.F_PTS, parameters.AREA1, parameters.AREA2, numPoints);
            default:
                return false;
        }
    } 

    /**
     * Determines whether there exists at least one pair of consecutive points in the given array
     * such that the distance between them is greater than a specified length.
     *
     * @param points    An array of Point objects representing the coordinates.
     * @param LENGTH1   The length to compare the distance against. Must be non-negative.
     * @param numPoints The number of points in the array.
     * @return          True if there exists at least one pair of consecutive points with a distance
     *                  greater than LENGTH1, otherwise false.
     */
    public static boolean LIC0(Point[] points, double LENGTH1, int numPoints) {
        if (LENGTH1 < 0) return false;
        for (int i = 0; i < numPoints - 1; i++) {
            double distance = points[i].distance(points[i + 1]);
            if (distance > LENGTH1) return true;
        }
        return false;
    }

    public static boolean LIC1(Point[] points, double length, int numPoints){
        /*
        There exists at least one set of two consecutive data points that are a distance greater than
        the length, LENGTH1, apart. (0 ≤ LENGTH1)

         */
        if (length < 0) return false; // Input validation

        for(int i = 0; i < numPoints - 1; i++){
            double distance = points[i].distance(points[i+1]);
            if (distance > length) return true;
        }
        return false;
    }

    public static boolean LIC3(Point[] points, double AREA1, int numPoints){
        if (AREA1 < 0) { // Input validation
            throw new IllegalArgumentException("AREA1 must be greater than or equal to 0.");
        }

        for(int i = 0; i < numPoints - 2; i++){
            double area = Point.area(points[i], points[i+1], points[i+2]);
            if(area > AREA1) return true;
        }
        return false;
    }

    /**
     * Checks if a set of consecutive data points lay in more quadrants than QUADS quadrants
     * @param points
     * @param QUADS
     * @param Q_PTS
     * @param numPoints
     * @return {boolean}
     */
    public static boolean LIC4(Point[] points, int QUADS, int Q_PTS, int numPoints) {
        for (int i = 0; i + Q_PTS <= numPoints; i++) {
            int count = 0;
            boolean[] quadrants = new boolean[4];

            for (int j = i; j < i + Q_PTS; j++){
                Point p = points[j];
                int q = Point.quadrant(p);
                if (quadrants[q] == false) {
                    quadrants[q] = true;
                    count++;
                }
            }
            if (count > QUADS) {
                return true;
            }
        }
        return false;
    }

    public static boolean LIC6(Point[] points, double DIST, int N_PTS, int numPoints){
        /*
        There exists at least one set of N PTS consecutive data points such that at least one of the
        points lies a distance greater than DIST from the line joining the first and last of these N PTS
        points. If the first and last points of these N PTS are identical, then the calculated distance
        to compare with DIST will be the distance from the coincident point to all other points of
        the N PTS consecutive points. The condition is not met when NUMPOINTS < 3.
        (3 ≤N PTS ≤NUMPOINTS), (0 ≤DIST)
         */
        if (N_PTS < 3 || N_PTS > numPoints) return false; // Input validation
        if (DIST < 0) return false; // Input validation

        if (numPoints < 3) return false;

        for (int i = 0; i + N_PTS <= numPoints; i++){
            Point A = points[i];
            Point B = points[i + N_PTS - 1];

            for (int j = i + 1; j < i + N_PTS - 1; j++){
                Point p = points[j];
                double distance;

                if (A.x == B.x && A.y == B.y) {
                    distance = p.distance(A);
                } else {
                    distance = p.distanceToLine(A, B);
                }
                if (distance > DIST) return true;
            }
        }
        return false;
    }

    public static boolean LIC8(Point[] points, int A_PTS, int B_PTS, double RADIUS1, int numPoints){
        // Input validation
        if (A_PTS < 1 || B_PTS < 1) {
            throw new IllegalArgumentException("A_PTS and B_PTS must each be greater than or equal to 1.");
        }
        if (A_PTS + B_PTS > numPoints - 3) {
            throw new IllegalArgumentException("A_PTS + B_PTS must be less than or equal to NUMPOINTS - 3.");
        }

        if (numPoints < 5) return false;

        for (int i = 0; i + A_PTS + B_PTS < numPoints; i++) {
            Point A = points[i];
            Point B = points[i + A_PTS];
            Point C = points[i + A_PTS + B_PTS];

            double radius = Point.circumradius(A, B, C);
            if (radius > RADIUS1) return true;
        }
        return false;
    }

    /**
     * Checks if a set of three data points separated by C_PTS and D_PTS forms an angle less than PI-EPSILON or
     * greater than PI+EPSILON
     * @param points
     * @param C_PTS
     * @param D_PTS
     * @param EPSILON
     * @param numPoints
     * @return {boolean}
     */
    public static boolean LIC9(Point[] points, int C_PTS, int D_PTS, double EPSILON , int numPoints) {
        if (numPoints < 5) return false;

        for (int i = 0; i + C_PTS + D_PTS < numPoints; i++) {
            Point A = points[i];
            Point B = points[i + C_PTS];
            Point C = points[i + C_PTS + D_PTS];

            if ((A.x == B.x && A.y == B.y) || (C.x == B.x && C.y == B.y)) {
                continue;
            }

            double angle = Point.angle(A, B, C);
            if (angle < Math.PI - EPSILON || angle > Math.PI + EPSILON) {
                return true;
            }
        }
        return false;
    }

    public static boolean LIC11(Point[] points, int G_PTS, int numPoints){
        /*
         * There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
         * exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j )
         * The condition is not met when NUMPOINTS < 3.
         */
        if (numPoints < 3) return false;

        for (int i = 0; i  + G_PTS < numPoints; i++){
            Point A = points[i];
            Point B = points[i + G_PTS];

            if (B.x - A.x < 0) return true;
        }
        return false;
    }

    public static  boolean LIC13(Point[] points, int A_PTS, int B_PTS, double RADIUS1, double RADIUS2, int numPoints){

        if (RADIUS2 < 0) { // Input validation
            return false;
        }

        if (numPoints < 5) return false;

        boolean cond1 = false;
        boolean cond2 = false;

        for (int i = 0; i + A_PTS + B_PTS < numPoints; i++) {
            Point A = points[i];
            Point B = points[i + A_PTS];
            Point C = points[i + A_PTS + B_PTS];

            double radius = Point.circumradius(A, B, C);
            if (radius > RADIUS1) cond1 = true;
            if (radius <= RADIUS2) cond2 = true;
            if (cond1 && cond2) return true;
        }
        return false;
    }

    /**
     * Checks if there is a set of three data points seperated by E_PTS and F_PTS that forms a triangle with area greater than AREA1 and
     * a set that forms a triangle with area less than AREA2
     * @param points
     * @param E_PTS
     * @param F_PTS
     * @param AREA1
     * @param AREA2
     * @param numPoints
     * @return {boolean}
     */
    public static boolean LIC14(Point[] points, int E_PTS, int F_PTS, double AREA1, double AREA2, int numPoints) {
        if (numPoints < 5) return false;

        boolean cond1 = false;
        boolean cond2 = false;

        for (int i = 0; i + E_PTS + F_PTS < numPoints; i++) {
            Point A = points[i];
            Point B = points[i + E_PTS];
            Point C = points[i + E_PTS + F_PTS];

            double area = Point.area(A, B, C);
            if (area > AREA1) cond1 = true;
            if (area < AREA2) cond2 = true;
            if (cond1 && cond2) return true;
        }
        return false;
    }
}

package com.decide;

import com.decide.Parameters;
import com.decide.Point;

import java.util.Dictionary;
import java.util.Hashtable;

public class LICConditions {
    public static boolean evaluateLIC(int licIndex, Point[] points, Parameters parameters, int numPoints){
        switch(licIndex){
            // case 0:
            //     return LIC0(points, parameters, numPoints);
            // case 1:
            //     return LIC1(points, parameters, numPoints);
            // case 2:
            //     return LIC2(points, parameters, numPoints);
            // case 3:
            //     return LIC3(points, parameters, numPoints);
            // case 4:
            //     return LIC4(points, parameters, numPoints);
            // case 5:
            //     return LIC5(points, parameters, numPoints);
            // case 6:
            //     return LIC6(points, parameters, numPoints);
            // case 7:
            //     return LIC7(points, parameters, numPoints);
            // case 8:
            //     return LIC8(points, parameters, numPoints);
            // case 9:
            //     return LIC9(points, parameters, numPoints);
            // case 10:
            //     return LIC10(points, parameters, numPoints);
            // case 11:
            //     return LIC11(points, parameters, numPoints);
            // case 12:
            //     return LIC12(points, parameters, numPoints);
            // case 13:
            //     return LIC13(points, parameters, numPoints);
            // case 14:
            //     return LIC14(points, parameters, numPoints);
            default:
                return false;
        }
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
        // TODO: Refactor
        for (int index = 0; index + Q_PTS <= numPoints; index++) {
            int numberOfQuadrants = 0;
            Dictionary<String, Boolean> quadrants = new Hashtable<>();
            quadrants.put("q1", false);
            quadrants.put("q2", false);
            quadrants.put("q3", false);
            quadrants.put("q4", false);

            for (int i = index; i < index + Q_PTS; i++){
                String quadrant = "";
                if (points[i].x >= 0 && points[i].y >= 0) {
                    quadrant = "q1";
                } else if (points[i].x < 0 && points[i].y >= 0) {
                    quadrant = "q2";
                } else if (points[i].x <= 0 && points[i].y < 0) {
                    quadrant = "q3";
                } else if (points[i].x > 0 && points[i].y < 0) {
                    quadrant = "q4";
                }
                if (quadrants.get(quadrant) == false) {
                    quadrants.put(quadrant, true);
                    numberOfQuadrants++;
                }
            }
            if (numberOfQuadrants > QUADS) {
                return true;
            }
        }
        return false;
    }

    public static boolean LIC6(Point[] points, Parameters parameters, int numPoints){
        // TODO: Refactor
        /*
        There exists at least one set of N PTS consecutive data points such that at least one of the
        points lies a distance greater than DIST from the line joining the first and last of these N PTS
        points. If the first and last points of these N PTS are identical, then the calculated distance
        to compare with DIST will be the distance from the coincident point to all other points of
        the N PTS consecutive points. The condition is not met when NUMPOINTS < 3.
        (3 ≤N PTS ≤NUMPOINTS), (0 ≤DIST)
         */
        int N_PTS = parameters.N_PTS;
        double DIST = parameters.DIST;

        if (numPoints < 3) return false; // Automatically false in this case;

        if (N_PTS < 3 || N_PTS > numPoints) return false;

        if (DIST < 0) return false;

        for (int i = 0; i < numPoints; i++){
            if (i + N_PTS - 1 >= numPoints) return false; // Not enough points left.
            Point startPoint = points[i];
            Point endPoint = points[i + N_PTS - 1];
            if (startPoint.x == endPoint.x && startPoint.y == endPoint.y){ // Same start and end point
                for(int j = i + 1; j < i + N_PTS - 1; j++){
                    double distance = startPoint.distance(points[j]);
                    if (distance > DIST) return true;
                }
            }

            for (int j=i+1; j < i + N_PTS - 1; j++){
                // Check if the point is perpendicular to this line segment
                double angleStart = Point.angle(points[j], startPoint, endPoint); // gets the angle at the start point
                double angleEnd = Point.angle(points[j], endPoint, startPoint); // angle at the end point

                boolean perpendicular = false;
                if (angleStart < Math.PI/2 && angleEnd < Math.PI/2) perpendicular = true;

                double distance = -1;
                if (!perpendicular) {
                    double distanceStart = points[j].distance(startPoint);
                    double distanceEnd = points[j].distance(endPoint);
                    distance = Math.min(distanceStart, distanceEnd);
                }
                else{
                    // Calculate the perpendicular distance using the formula
                    distance = points[j].distanceToLine(startPoint, endPoint);
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

    public static boolean LIC11(Point[] points, Parameters parameters, int numPoints){
        /*
         * There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
         * exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j )
         * The condition is not met when NUMPOINTS < 3.
         */
        if (numPoints < 3) return false;
        int G_PTS = parameters.G_PTS;
         for (int i = 0; i < numPoints; i++){

            if (i + G_PTS + 1 >= numPoints) break; // Not enough points left.
            double x_i = points[i].x;
            double x_j = points[i + G_PTS + 1].x;
            if (x_j - x_i < 0) return true;
        }
        return false;
    }

    public static  boolean LIC13(Point[] points, int A_PTS, int B_PTS, double RADIUS1, double RADIUS2, int numPoints){

        // Input validation
        if (numPoints < 5 || A_PTS < 1 || B_PTS < 1 || RADIUS2 < 0) {
            return false;
        }

        boolean cond1 = false;
        boolean cond2 = false;

        // Iterate through all sets of three points
        for (int i = 0; i < numPoints; i++) {

            // First point
            Point p1 = points[i];

            // Second point
            int j = i + A_PTS + 1;
            Point p2 = points[j];

            // Third point
            int k = j + B_PTS + 1;
            if (k >= numPoints) break; // Ensure indices are within bounds
            Point p3 = points[k];

            // Get radius
            double radius = Point.circumradius(p1, p2, p3);

            // Update and check conditions
            if (radius > RADIUS1) {
                cond1 = true;
            }
            if (radius <= RADIUS2) {
                cond2 = true;
            }
            if (cond1 && cond2) {
                return true;
            }
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
        if (numPoints < 5) {
            return false;
        }
        int index = 0;
        boolean cond1 = false;
        boolean cond2 = false;
        while (index + E_PTS + F_PTS < numPoints) {
            Point A = points[index];
            Point B = points[index + E_PTS];
            Point C = points[index + E_PTS + F_PTS];
            double area = Math.abs(0.5*(A.x*(B.y-C.y)-B.x*(C.y-A.y)+C.x*(A.y-B.y)));
            if (area > AREA1) {
                cond1 = true;
            }
            if (area < AREA2) {
                cond2 = true;
            }
            if (cond1 == true && cond2 == true) {
                return true;
            }
            index++;
        }
        return false;
    }
}

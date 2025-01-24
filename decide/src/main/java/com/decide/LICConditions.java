package com.decide;

import com.decide.Parameters;
import com.decide.Point;

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
        if (length < 0) return false;
        for(int i = 0; i < numPoints - 1; i++){
            double x_1 = points[i].x;
            double y_1 = points[i].y;
            double x_2 = points[i+1].x;
            double y_2 = points[i+1].y;
            // Calculate the euclidean distance
            double distance = Math.sqrt(Math.pow(x_1 - x_2, 2) + Math.pow(y_1 - y_2, 2));
            if (distance > length) return true;
        }
        return false;
    }
    
    public static boolean LIC3(Point[] points, double AREA1, int numPoints){
        if (AREA1 < 0) {
            throw new IllegalArgumentException("AREA1 must be greater than or equal to 0.");
        }

        // Need at least 3 points to form a triangle
        if(numPoints < 3){
            return false;
        }
        // Iterate through all sets of 3 consecutive points
        for(int i = 0; i < numPoints - 2; i++){
            Point p1 = points[i];
            Point p2 = points[i+1];
            Point p3 = points[i+2];

            // Calculate the triangle area
            double a = 0.5 * Math.abs((p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y)));

            // Compare to area1
            if(a > AREA1){
                return true;
            }
        }
        return false;
    }

    public static boolean LIC6(Point[] points, Parameters parameters, int numPoints){
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
}

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
    public static boolean LIC8(Point[] points, int A_PTS, int B_PTS, double RADIUS1, int numPoints){

        // Validate parameters
        if (A_PTS < 1 || B_PTS < 1) {
            throw new IllegalArgumentException("A_PTS and B_PTS must each be greater than or equal to 1.");
        }
        if (A_PTS + B_PTS > numPoints - 3) {
            throw new IllegalArgumentException("A_PTS + B_PTS must be less than or equal to NUMPOINTS - 3.");
        }

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


            // If points are collinear, they cannot form a circle
            double determinant = p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y);
            if (determinant == 0) {
                return true;
            }

            // Calculate the distances between the points
            double distance12 = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
            double distance23 = Math.sqrt(Math.pow(p3.x - p2.x, 2) + Math.pow(p3.y - p2.y, 2));
            double distance31 = Math.sqrt(Math.pow(p1.x - p3.x, 2) + Math.pow(p1.y - p3.y, 2));

            // Calculate the circumcircle radius
            double numerator = distance12 * distance23 * distance31;
            double denominator = Math.abs(determinant) * 2;
            double circumcircleRadius = numerator / denominator;

            // Check if the circumcircle radius exceeds the given RADIUS1
            if (circumcircleRadius > RADIUS1) {
                return true;
            }
        }
        return false;
    }
}

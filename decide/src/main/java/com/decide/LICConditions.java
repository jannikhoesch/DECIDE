package com.decide;

import com.decide.Parameters;
import com.decide.Point;
import org.junit.Test;

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
            case 8:
                 return LIC8(points, parameters.A_PTS, parameters.B_PTS, parameters.RADIUS1, numPoints);
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

            // Check if the circumcircle radius exceeds the given RADIUS1
            double radius = radius(p1, p2, p3);
            if (Double.isNaN(radius) || radius > RADIUS1) {
                return true;
            }
        }
        return false;
    }

    public static double radius(Point p1, Point p2, Point p3){

        // If points are collinear, they cannot form a circle
        double determinant = p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y);
        if (determinant == 0) {
            return Double.NaN;
        }

        // Calculate the distances between the points
        double distance12 = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
        double distance23 = Math.sqrt(Math.pow(p3.x - p2.x, 2) + Math.pow(p3.y - p2.y, 2));
        double distance31 = Math.sqrt(Math.pow(p1.x - p3.x, 2) + Math.pow(p1.y - p3.y, 2));

        // Calculate the circumcircle radius
        double numerator = distance12 * distance23 * distance31;
        double denominator = Math.abs(determinant) * 2;
        double radius = numerator / denominator;

        return radius;
    }
}

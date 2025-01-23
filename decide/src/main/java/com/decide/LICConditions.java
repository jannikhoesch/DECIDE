package com.decide;

import java.util.Dictionary;
import java.util.Hashtable;

public class LICConditions {
    public static boolean evaluateLIC(int licIndex, double[][] points, Parameters parameters, int numPoints){
        switch(licIndex){
            // case 0:
            //     return LIC0(points, parameters, numPoints);
            // case 1:
            //     return LIC1(points, parameters, numPoints);
            // case 2:
            //     return LIC2(points, parameters, numPoints);
            // case 3:
            //     return LIC3(points, parameters, numPoints);
            //case 4:
            //    return LIC4(points, parameters.QUADS, parameters.Q_PTS, numPoints);
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

    /**
     * Checks if a set of consecutive data points lay in more quadrants than QUADS quadrants
     * @param points
     * @param QUADS
     * @param Q_PTS
     * @param numPoints
     * @return {boolean}
     */
    public static boolean LIC4(Point[] points, int QUADS, int Q_PTS, int numPoints) {
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
}

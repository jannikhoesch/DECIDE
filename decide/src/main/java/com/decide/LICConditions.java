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
            case 4:
                return LIC4(points, parameters.QUADS, parameters.Q_PTS, numPoints);
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


    public static boolean LIC4(double[][] points, int QUADS, int Q_PTS, int numPoints) {
        int index = 0;

        while (index + Q_PTS <= numPoints) {
            int numberOfQuadrants = 0;
            Dictionary<String, Boolean> quadrants = new Hashtable<>();
            quadrants.put("q1", false);
            quadrants.put("q2", false);
            quadrants.put("q3", false);
            quadrants.put("q4", false);

            for (int i = index; i < index + Q_PTS; i++){
                if (points[i][0] >= 0 && points[i][1] >= 0) {
                    if (quadrants.get("q1") == false) {
                        quadrants.put("q1", true);
                        numberOfQuadrants++;
                    }
                } else if (points[i][0] < 0 && points[i][1] >= 0) {
                    if (quadrants.get("q2") == false) {
                        quadrants.put("q2", true);
                        numberOfQuadrants++;
                    }
                } else if (points[i][0] <= 0 && points[i][1] < 0) {
                    if (quadrants.get("q3") == false) {
                        quadrants.put("q3", true);
                        numberOfQuadrants++;
                    }
                } else if (points[i][0] > 0 && points[i][1] < 0) {
                    if (quadrants.get("q4") == false) {
                        quadrants.put("q4", true);
                        numberOfQuadrants++;
                    }
                }
            }
            if (numberOfQuadrants > QUADS) {
                return true;
            }
            index++;
        }
        return false;
    }
}

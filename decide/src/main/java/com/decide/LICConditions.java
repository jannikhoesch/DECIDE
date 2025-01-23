package com.decide;

import com.decide.Parameters;
import com.decide.Point;

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


}

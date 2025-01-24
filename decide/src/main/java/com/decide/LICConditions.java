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
}

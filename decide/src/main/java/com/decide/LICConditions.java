package com.decide;

import com.decide.Parameters;

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
            if (startPoint.x == endPoint.x && startPoint.y == endPoint.y){ 
                // They are the same point, we then calculate distance from this point to all other N_PTS-1 points.
                for (int j = i + 1; j < i + N_PTS - 1; j++){
                    double x_1 = startPoint.x;
                    double y_1= startPoint.y;
                    double x_2 = points[j].x;
                    double y_2= points[j].y;

                    // Calculate the Euclidean distance
                    double distance = Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2));
                    if (distance > DIST) return true;
                }
            }

            for (int j = i+1; j < i + N_PTS - 1; j++){
                // Calculate the distance from point j to the line between the first and last point.
                
                double x_1 = startPoint.x;
                double y_1= startPoint.y;
                double x_2 = endPoint.x;
                double y_2 = endPoint.y;
                double x_3 = points[j].x;
                double y_3= points[j].y;

                // Check if point is perpendicular to the line segment by checking if it is inbetween the two lines who are
                // perpendicular to the line segment that go through the two endpoints.
                boolean perpendicular = false;

                // k-value (derivative)
                
                double distance = -1;
                if (y_2 - y_1 != 0 || x_2 - x_1 != 0){ // Only works with non-vertical and non-horizontal lines.
                    double kSegment = (y_2 - y_1)/(x_2 - x_1); // k-value of the line segment
                    double k = -1 / kSegment; 
                    
                    double m_1 = y_1 - k*x_1; // m values for the two lines (for writing on the form y = kx + m)
                    double m_2 = y_2 - k*x_2;

                    // we now have two lines, y = kx + m_1 and  y = kx + m_2
                    //if the point is between the two lines, we know it is perpendicular to the line segment.
                    if ((k*x_3 + m_1 < y_3 && k*x_3 + m_2 > y_3) || (k*x_3 + m_1 > y_3 && k*x_3 + m_2 < y_3)){ 
                        
                        perpendicular = true;
                    }
                    
                }
                else if (y_2 - y_1 != 0){ // if the derivative of the slope is 0 we simply have to check that it x_1 <= x_3 <= x_2 or x_2 <= x_3 <= x_1
                    if ((x_1 < x_3 && x_3 < x_2) || (x_2 < x_3 && x_3 < x_1)) perpendicular = true;
                }
                else{ // if they form a vertical line, we have to do the same check as the earlier else if but for the y value.
                    if ((y_1 < y_3 && y_3 < y_2) || (y_2 < y_3 && y_3 < y_1)) perpendicular = true;
                }
                    

                // if not perpendicular, the point is closest to either of the endpoints.
                if (!perpendicular) {
                    double distanceStart = Math.sqrt(Math.pow(y_3 - y_1, 2) + Math.pow(x_3 - x_1, 2));
                    double distanceEnd = Math.sqrt(Math.pow(y_3 - y_2, 2) + Math.pow(x_3 - x_2, 2));
                    distance = Math.min(distanceStart, distanceEnd);
                }
                else{
                    // Calculate the perpendicular distance using the formula
                    double numerator = Math.abs((y_2 - y_1) * x_3 - (x_2 - x_1) * y_3 + x_2 * y_1 - y_2 * x_1);
                    double denominator = Math.sqrt(Math.pow(y_2 - y_1, 2) + Math.pow(x_2 - x_1, 2));
                    distance = numerator / denominator;
                    
                }
                
                if (distance > DIST) return true;
            
            }
        }
        return false;
    }
}

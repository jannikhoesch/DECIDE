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
            //    return LIC9(Point[] points, int C_PTS, int D_PTS, double EPSILON , int numPoints);
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

    public static boolean LIC9(Point[] points, int C_PTS, int D_PTS, double EPSILON , int numPoints) {
        if (numPoints < 5) {
            return false;
        }

        int index = 0;

        while (index + C_PTS + D_PTS < numPoints) {
            Point A = points[index];
            Point B = points[index + C_PTS];
            Point C = points[index + C_PTS + D_PTS];

            if ((A.x != B.x || A.y != B.y) && (C.x != B.x || C.y != B.y)) {
                double[] BA = {A.x-B.x, A.y-B.y};
                double[] BC = {C.x-B.x, C.y-B.y};
                double normBA = Math.sqrt(Math.pow(BA[0], 2) + Math.pow(BA[1], 2));
                double normBC = Math.sqrt(Math.pow(BC[0], 2) + Math.pow(BC[1], 2));
                double BAdotBC = BA[0]*BC[0] + BA[1]*BC[1];
                double angle = Math.acos(BAdotBC/(normBA*normBC));
                if (angle < Math.PI - EPSILON || angle > Math.PI + EPSILON) {
                    return true;
                }
            }
            index++;
        }
        return false;
    }

}

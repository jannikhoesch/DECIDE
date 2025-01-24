package com.decide;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import com.decide.Parameters;
import com.decide.Point;
import com.decide.LICConditions;

public class Decide {
    //Constants
    private static final int licNumber = 15;

    //Input Variables
    private static int numPoints; // Number of data points
    private static Point[] points; // Planar data points
    private static Parameters parameters; // Struct holding parameters for LIC’s
    private static int[][] LCM; // Logical Connector Matrix
    private static boolean[] PUV; // Preliminary Unlocking Vector

    //Output Variables
    private static boolean[] CMV = new boolean[licNumber]; // Conditions Met Vector
    private static boolean[][] PUM = new boolean[licNumber][licNumber]; // Preliminary Unlocking Matrix
    private static boolean[] FUV = new boolean[licNumber]; // Final Unlocking Vector


    public static void init(){

        // Initialize the input variables
        numPoints = 100;

        // Init planar data points
        points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            points[i] = new Point(
                    ThreadLocalRandom.current().nextInt(-100, 100),
                    ThreadLocalRandom.current().nextInt(-100, 100)
            );
        }

        // Init parameters struct
        parameters = new Parameters(1.0, 1.0, 1.0, 1.0, 1, 1, 1.0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1.0, 1.0, 1.0);
 
        // Init LCM
        LCM = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j <= i; j++) { // Fill lower triangle only
                LCM[i][j] = LCM[j][i] = ThreadLocalRandom.current().nextInt(3);
            }
        }

        // Init PUV
        PUV = new boolean[15];
        for (int i = 0; i < PUV.length; i++) {
            PUV[i] = ThreadLocalRandom.current().nextBoolean();
        }

        // For debugging: Print the input
        System.out.println("numPoints: " + numPoints);
        System.out.println("points: " + Arrays.toString(points));
        System.out.println("parameters: " + parameters);
        System.out.println("LCM: " + Arrays.deepToString(LCM));
        System.out.println("PUV: " + Arrays.toString(PUV));
    }

    public static boolean[][] PUM(boolean[] CMV, int[][] LCM){
        /*
         * LCM (Logical Connector Matrix) The LCM describes how individual LIC’s should be logically
         * combined. For example, the value of LCM[i,j] indicates whether LIC #i should combine
         * with LIC #j by the logical AND, OR, or not at all, the values correspond to:
         * 1 = ANDD
         * 2 = ORR
         * 777 = not at all
         */
        
        boolean[][] PUM = new boolean[CMV.length][CMV.length];

        for (int i = 0; i < CMV.length; i++){
            for (int j = 0; j < CMV.length; j++){
                int operator = LCM[i][j];
                switch (operator){
                    case 1: // ANDD
                        PUM[i][j] = CMV[i] && CMV[j];
                        break;
                    case 2: // ORR
                        PUM[i][j] = CMV[i] || CMV[j];
                        break;
                    case 777: // not at all
                        PUM[i][j] = true;
                        break;
                }
            }
        }
        return PUM;
    }

    public static boolean DECIDE() {
        // Compute the values of the CMV, PUM and FUV and determine if the final decision is true or false
        return false;
    }

    public static void main(String[] args) {
        init();
        DECIDE();

        // Print the output
        System.out.println("Hello world!");
    }

}
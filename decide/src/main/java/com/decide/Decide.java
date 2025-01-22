package com.decide;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class Decide {
    //Constants
    private static final int licNumber = 15;

    //Input Variables
    private static int numPoints;
    private static int[] x;
    private static int[] y;
    //private static Parameters parameters;
    private static int[][] LCM = new int[15][15]; // Logical Connector Matrix
    private static boolean[] PUV = new boolean[15]; // Preliminary Unlocking Vector

    //Output Variables
    private static boolean[] CMV = new boolean[licNumber]; // Conditions Met Vector
    private static boolean[][] PUM = new boolean[licNumber][licNumber]; // Preliminary Unlocking Matrix
    private static boolean[] FUV = new boolean[licNumber]; // Final Unlocking Vector

    public static boolean DECIDE() {
        // Compute the values of the CMV, PUM and FUV and determine if the final decision is true or false
        return false;
    }

    public static void main(String[] args) {
        // Initialize the input variables

        numPoints = 100;

        // Init planar points
        x = new int[numPoints];
        y = new int[numPoints];
        for (int i = 0; i < numPoints; i++) {
            x[i] = ThreadLocalRandom.current().nextInt(-100, 100);
            y[i] = ThreadLocalRandom.current().nextInt(-100, 100);
        }

        // Init LCM
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j <= i; j++) { // Fill lower triangle only
                LCM[i][j] = LCM[j][i] = ThreadLocalRandom.current().nextInt(3);
            }
        }

        // Init PUV
        for (int i = 0; i < PUV.length; i++) {
            PUV[i] = ThreadLocalRandom.current().nextBoolean();
        }

        // For debugging: Print input variables
        System.out.println("NUMPOINTS: " + numPoints);
        System.out.println("X: " + Arrays.toString(x));
        System.out.println("Y: " + Arrays.toString(y));
        //System.out.println("PARAMETERS: " + parameters);
        System.out.println("LCM: " + Arrays.deepToString(LCM));
        System.out.println("PUV: " + Arrays.toString(PUV));


        // Call DECIDE

        // Print the output
        System.out.println("Hello world!");



    }
}
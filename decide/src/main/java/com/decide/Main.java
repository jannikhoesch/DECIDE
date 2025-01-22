package com.decide;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class Main {

    // Input Variables
    static int NUMPOINTS; // Number of planar data points
    static int[] X;    // Array for X-coordinates of points
    static int[] Y;    // Array for Y-coordinates of points

    // Logical Connector Matrix (LCM)
    // 0: NOTUSED, 1: ORR, 2: ANDD
    static int[][] LCM = new int[15][15];

    // Preliminary Unlocking Vector (PUV), 15 elements
    static boolean[] PUV = new boolean[15];


    public static void init_variables() {

        NUMPOINTS = 100;

        // Initialize the X and Y coordinates
        X = new int[NUMPOINTS];
        Y = new int[NUMPOINTS];

        // Init X and Y coordinates with random values
        for (int i = 0; i < NUMPOINTS; i++) {
            X[i] = ThreadLocalRandom.current().nextInt(-100, 100);
            Y[i] = ThreadLocalRandom.current().nextInt(-100, 100);
        }

        // Init LCM with random connectors
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j <= i; j++) {
                LCM[i][j] = LCM[j][i] = ThreadLocalRandom.current().nextInt(3);
            }
        }

        // Init PUV randomly
        for (int i = 0; i < PUV.length; i++) {
            PUV[i] = ThreadLocalRandom.current().nextBoolean();
        }

    }

    public static void main(String[] args) {
        init_variables();

        // For debugging: Print initialized variables
        System.out.println("NUMPOINTS: " + NUMPOINTS);
        System.out.println("X coordinates: " + Arrays.toString(X));
        System.out.println("Y coordinates: " + Arrays.toString(Y));
        System.out.println("LCM: " + Arrays.deepToString(LCM));
        System.out.println("PUV: " + Arrays.toString(PUV));
    }
}
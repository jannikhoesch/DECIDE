package com.decide;

public class Decide {
    //Constants
    private static final int licNumber = 15;

    //Input Variables
    private static int numPoints;
    private static double[][] points;
    private static Parameters parameters;
    private static String[][] LCM; // Logical Connector Matrix
    private static boolean[] PUV; // Preliminary Unlocking Vector

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
        // Call DECIDE
        // Print the output
        System.out.println("Hello world!");
    }
}
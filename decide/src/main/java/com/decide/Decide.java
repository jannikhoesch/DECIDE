package com.decide;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

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
    private static boolean LAUNCH; // Launch decision


    public static Decide init(){

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

        return new Decide();
    }

    public static boolean[][] PUM(boolean[] CMV, int[][] LCM){
        /*
         * LCM (Logical Connector Matrix) The LCM describes how individual LIC’s should be logically
         * combined. For example, the value of LCM[i,j] indicates whether LIC #i should combine
         * with LIC #j by the logical AND, OR, or NOTUSED, the values correspond to:
         * 1 = ANDD
         * 2 = ORR
         * 3 = NOTUSED
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
                    case 3: // NOTUSED
                        PUM[i][j] = true;
                        break;
                }
            }
        }
        return PUM;
    }

    /**
     * Generates the Final Unlocking Vector (FUV) by using the Preliminary Unlocking
     * Vector (PUV) and the Preliminary Unlocking Vector (PUM). 
     * 
     * If PUV[i] is false or if all elements in row i of PUM are true, FUV[i] is true
     * otherwise FUV[i] is false. 
     * @param PUV
     * @param PUM
     * @return {boolean[]}
     */
    public static boolean[] FUV(boolean[] PUV, boolean[][] PUM) {
        boolean[] FUV = new boolean[PUV.length];

        for (int i = 0; i < PUV.length; i++) {
            if (PUV[i] == false) {
                FUV[i] = true;
            } else {
                FUV[i] = true;
                for (int j = 0; j < PUM[i].length; j++) {
                    if (PUM[i][j] == false) {
                        FUV[i] = false;
                        break;
                    }
                }
            }
        }
        return FUV;
    }
    
    /**
     * Makes the final launch decision based on the Final Unlocking Vector (FUV). 
     * The decision to launch requires all elements in FUV to be true.
     * @param FUV
     * @return {boolean}
     */
    public static boolean LAUNCH(boolean[] FUV) {
        for (int i = 0; i < FUV.length; i++) {
            if (FUV[i] == false) {
                return false; // No launch
            }
        }
        return true; // Launch
    }

    public static boolean input_valid(Parameters p, int NUMPOINTS){
        if (p.LENGTH1 >= 0 && // LIC0
            p.RADIUS1 >= 0 && // LIC1
            p.EPSILON >= 0 && p.EPSILON < Math.PI  && // LIC2
            p.AREA1 >= 0   && // LIC3
            2 <= p.Q_PTS   && p.Q_PTS <= NUMPOINTS && 1 <= p.QUADS && p.QUADS <= 3 && // LIC4
            3 <= p.N_PTS   && p.N_PTS <= NUMPOINTS && p.DIST >= 0 && // LIC6 (no check for LIC5)
            1 <= p.K_PTS   && p.K_PTS <= NUMPOINTS - 2 && // LIC7
            p.A_PTS >= 1   && p.B_PTS >= 1 && p.A_PTS + p.B_PTS <= NUMPOINTS - 3 &&//LIC8
            p.C_PTS <= 1   && p.D_PTS <= 1 && p.C_PTS + p.D_PTS <= NUMPOINTS - 3 && //LIC9
            p.E_PTS <= 1   && p.F_PTS <= 1 && p.E_PTS + p.F_PTS <= NUMPOINTS - 3 && //LIC10
            1 <= p.G_PTS   && p.G_PTS <= NUMPOINTS -2 && //LIC11
            p.LENGTH2 >= 0 && //LIC12
            p.RADIUS2 >= 0 && //LIC13
            p.AREA2 >= 0 //LIC14
            ) return true;
        return false;
    }

    public static boolean DECIDE() {
        // 2.1 Calculate CMV
        for (int i = 0; i < licNumber; i++) {
            CMV[i] = LICConditions.evaluateLIC(i, points, parameters, numPoints);
        }

        // 2.2 Calculate PUM
        PUM = PUM(CMV, LCM);

        // 2.3 Calculate FUV
        //FUV = FUV(PUV, PUM);

        // 2.4 Decide launch
        //LAUNCH = LAUNCH(FUV);

        return LAUNCH;
    }

    public static void main(String[] args) {
        Decide decide = init();
        boolean result = decide.DECIDE();
        System.out.println("Final launch decision: " + result);
    }

}
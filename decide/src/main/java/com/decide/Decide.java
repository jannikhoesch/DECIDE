package com.decide;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import com.decide.LICConditions;
import com.decide.Parameters;
import com.decide.Point;

public class Decide {
    //Constants
    private static final int licNumber = 15;

    //Input Variables
    public static int numPoints; // Number of data points
    public static Point[] points; // Planar data points
    public static Parameters parameters; // Struct holding parameters for LIC’s
    public static int[][] LCM; // Logical Connector Matrix
    public static boolean[] PUV; // Preliminary Unlocking Vector

    //Output Variables
    private static boolean[] CMV = new boolean[licNumber]; // Conditions Met Vector
    private static boolean[][] PUM = new boolean[licNumber][licNumber]; // Preliminary Unlocking Matrix
    private static boolean[] FUV = new boolean[licNumber]; // Final Unlocking Vector
    private static boolean LAUNCH; // Launch decision


    public Decide(int numPoints, Point[] points, Parameters parameters, int[][] LCM, boolean[] PUV){
        Decide.numPoints = numPoints;
        Decide.points = points;
        Decide.parameters = parameters;
        Decide.LCM = LCM;
        Decide.PUV = PUV;
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

    public static boolean input_valid(Parameters p, int NUMPOINTS) {
        if (NUMPOINTS < 2 || NUMPOINTS > 100) throw new IllegalArgumentException("Invalid input: NUMPOINTS must be >= 3 and <= 100");
        if (p.LENGTH1 < 0) throw new IllegalArgumentException("Invalid input for LIC0: LENGTH1 must be >= 0");
        if (p.RADIUS1 < 0) throw new IllegalArgumentException("Invalid input for LIC1: RADIUS1 must be >= 0");
        if (p.EPSILON < 0 || p.EPSILON >= Math.PI) throw new IllegalArgumentException("Invalid input for LIC2: 0 <= EPSILON < π");
        if (p.AREA1 < 0) throw new IllegalArgumentException("Invalid input for LIC3: AREA1 must be >= 0");
        if (p.Q_PTS < 2 || p.Q_PTS > NUMPOINTS || p.QUADS < 1 || p.QUADS > 3)
            throw new IllegalArgumentException("Invalid input for LIC4: 2 <= Q_PTS <= NUMPOINTS and 1 <= QUADS <= 3");
        if (p.N_PTS < 3 || p.N_PTS > NUMPOINTS || p.DIST < 0)
            throw new IllegalArgumentException("Invalid input for LIC6: 3 <= N_PTS <= NUMPOINTS and DIST must be >= 0");
        if (p.K_PTS < 1 || p.K_PTS > NUMPOINTS - 2)
            throw new IllegalArgumentException("Invalid input for LIC7: 1 <= K_PTS <= NUMPOINTS - 2");
        if (p.A_PTS < 1 || p.B_PTS < 1 || p.A_PTS + p.B_PTS > NUMPOINTS - 3)
            throw new IllegalArgumentException("Invalid input for LIC8: 1 <= A_PTS, B_PTS and A_PTS + B_PTS <= NUMPOINTS - 3");
        if (p.C_PTS < 1 || p.D_PTS < 1 || p.C_PTS + p.D_PTS > NUMPOINTS - 3)
            throw new IllegalArgumentException("Invalid input for LIC9: 1 <= C_PTS, D_PTS and C_PTS + D_PTS <= NUMPOINTS - 3");
        if (p.E_PTS < 1 || p.F_PTS < 1 || p.E_PTS + p.F_PTS > NUMPOINTS - 3)
            throw new IllegalArgumentException("Invalid input for LIC10: 1 <= E_PTS, F_PTS and E_PTS + F_PTS <= NUMPOINTS - 3");
        if (p.G_PTS < 1 || p.G_PTS > NUMPOINTS - 2)
            throw new IllegalArgumentException("Invalid input for LIC11: 1 <= G_PTS <= NUMPOINTS - 2");
        if (p.LENGTH2 < 0) throw new IllegalArgumentException("Invalid input for LIC12: LENGTH2 must be >= 0");
        if (p.RADIUS2 < 0) throw new IllegalArgumentException("Invalid input for LIC13: RADIUS2 must be >= 0");
        if (p.AREA2 < 0) throw new IllegalArgumentException("Invalid input for LIC14: AREA2 must be >= 0");

        return true;
    }

    public static boolean DECIDE() {
        input_valid(parameters, numPoints);

        // 2.1 Calculate CMV
        for (int i = 0; i < licNumber; i++) {
            CMV[i] = LICConditions.evaluateLIC(i, points, parameters, numPoints);
        }

        // 2.2 Calculate PUM
        PUM = PUM(CMV, LCM);

        // 2.3 Calculate FUV
        FUV = FUV(PUV, PUM);

        // 2.4 Decide launch
        LAUNCH = LAUNCH(FUV);

        return LAUNCH;
    }

    public static void main(String[] args) {

        // Run decide with random input values

        // Initialize the input variables
        numPoints = ThreadLocalRandom.current().nextInt(2, 101);

        // Init planar data points
        points = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            points[i] = new Point(
                    ThreadLocalRandom.current().nextInt(-10, 10),
                    ThreadLocalRandom.current().nextInt(-10, 10)
            );
        }

        // Init parameters struct
        parameters = new Parameters(
                ThreadLocalRandom.current().nextDouble(0.0, 3.0), // LENGTH1
                ThreadLocalRandom.current().nextDouble(0.0, 3.0), // RADIUS1
                ThreadLocalRandom.current().nextDouble(0.0, Math.PI), // EPSILON
                ThreadLocalRandom.current().nextDouble(0.0, 3.0), // AREA1
                ThreadLocalRandom.current().nextInt(2, numPoints + 1), // Q_PTS
                ThreadLocalRandom.current().nextInt(1, 4), // QUADS
                ThreadLocalRandom.current().nextDouble(0.0, 100.0), // DIST
                ThreadLocalRandom.current().nextInt(3, numPoints + 1), // N_PTS
                ThreadLocalRandom.current().nextInt(1, numPoints - 1), // K_PTS
                ThreadLocalRandom.current().nextInt(1, (numPoints-1)/2), // A_PTS
                ThreadLocalRandom.current().nextInt(1, (numPoints-1)/2), // B_PTS
                ThreadLocalRandom.current().nextInt(1, (numPoints-1)/2), // C_PTS
                ThreadLocalRandom.current().nextInt(1, (numPoints-1)/2), // D_PTS
                ThreadLocalRandom.current().nextInt(1, (numPoints-1)/2), // E_PTS
                ThreadLocalRandom.current().nextInt(1, (numPoints-1)/2), // F_PTS
                ThreadLocalRandom.current().nextInt(1, numPoints - 1), // G_PTS
                ThreadLocalRandom.current().nextDouble(0.0, 3.0), // LENGTH2
                ThreadLocalRandom.current().nextDouble(0.0, 3.0), // RADIUS2
                ThreadLocalRandom.current().nextDouble(0.0, 3.0) // AREA2
        );

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

        Decide decide = new Decide(numPoints, points, parameters, LCM, PUV);
        boolean result = decide.DECIDE();
        System.out.println("Final launch decision: " + result);
    }
}
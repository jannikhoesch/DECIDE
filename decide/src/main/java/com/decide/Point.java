package com.decide;

public class Point {
    public double x;
    public double y;

    /**
     * Constructs a point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param p the other point
     * @return the distance between the two points
     */
    public double distance(Point p) {
        return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
    }

    /**
     * Calculates the direction (angle) from this point to another point.
     *
     * @param p the other point
     * @return the direction (angle) in radians
     */
    public double direction(Point p) {
        return Math.atan2(p.y - y, p.x - x);
    }

    /**
     * Calculates the perpendicular distance from this point to a line defined by two other points.
     *
     * @param a the first point defining the line
     * @param b the second point defining the line
     * @return the perpendicular distance from this point to the line
     */
    public double distanceToLine(Point a, Point b) {
        return Math.abs((b.x - a.x) * (a.y - y) - (a.x - x) * (b.y - a.y)) / Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    /**
     * Calculates the angle formed by three points.
     *
     * @param a the first point
     * @param b the second point (vertex of the angle)
     * @param c the third point
     * @return the angle in radians
     */
    public static double angle(Point a, Point b, Point c) {
        double ab = a.distance(b);
        double bc = b.distance(c);
        double ac = a.distance(c);
        return Math.acos((Math.pow(ab, 2) + Math.pow(bc, 2) - Math.pow(ac, 2)) / (2 * ab * bc));
    }

    /**
     * Calculates the area of the triangle formed by three points.
     *
     * @param a the first point
     * @param b the second point
     * @param c the third point
     * @return the area of the triangle
     */
    public static double area(Point a, Point b, Point c) {
        return Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2);
    }

    /**
     * Calculates the circumradius of the triangle formed by three points.
     *
     * @param a the first point
     * @param b the second point
     * @param c the third point
     * @return the circumradius of the triangle
     */
    public static double circumradius(Point a, Point b, Point c) {
        double ab = a.distance(b);
        double bc = b.distance(c);
        double ac = a.distance(c);
        return ab * bc * ac / (4 * area(a, b, c));
    }

    /**
     * Calculates the radius of a circle surrounding all three points on the line.
     *
     * @param a the first point
     * @param b the second point
     * @param c the third point
     * @return the radius of the circle
     */
    public static double circleLineSegment(Point a, Point b, Point c) {
        double d_ab = a.distance(b);
        double d_ac = a.distance(c);
        double d_bc = b.distance(c);
        double maxDist = Math.max(d_ab, Math.max(d_ac, d_bc));
        return maxDist / 2;
    }

    /**
     * Determines the quadrant of the point in the Cartesian coordinate system.
     *
     * @param p the point
     * @return the quadrant number (0 to 3)
     */
    public static int quadrant(Point p) {
        if (p.x >= 0 && p.y >= 0) return 0;
        if (p.x <= 0 && p.y >= 0) return 1;
        if (p.x <= 0 && p.y <= 0) return 2;
        return 3;
    }

    /**
     * Returns a string representation of the point.
     *
     * @return a string representation of the point
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
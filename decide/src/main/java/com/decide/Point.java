package com.decide;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
    }

    public double direction(Point p) {
        return Math.atan2(p.y - y, p.x - x);
    }

    public double distanceToLine(Point a, Point b) {
        return Math.abs((b.x - a.x) * (a.y - y) - (a.x - x) * (b.y - a.y)) / Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    public static double angle(Point a, Point b, Point c) {
        double ab = a.distance(b);
        double bc = b.distance(c);
        double ac = a.distance(c);
        return Math.acos((Math.pow(ab, 2) + Math.pow(bc, 2) - Math.pow(ac, 2)) / (2 * ab * bc));
    }

    public static double area(Point a, Point b, Point c) {
        return Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2);
    }

    public static double circumradius(Point a, Point b, Point c) {
        double ab = a.distance(b);
        double bc = b.distance(c);
        double ac = a.distance(c);
        return ab * bc * ac / (4 * area(a, b, c));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
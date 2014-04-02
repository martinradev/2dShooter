/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry;

import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class MathUtil {

    /**
     *
     */
    public static final double EPS = 0.00005;
    /**
     *
     */
    public static final double BIG_EPS = 0.02;
    /**
     *
     */
    public static final double POSITIVE_INFINITY = Double.MAX_VALUE;
    /**
     *
     */
    public static final double NEGATIVE_INFINITY = Double.MIN_VALUE;

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static double crossProduct(Vector2D a, Vector2D b) {
        return a.getX() * b.getY() - a.getY() * b.getX();
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static double dotProduct(Vector2D a, Vector2D b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static double distance(Vector2D a, Vector2D b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     *
     * @param a
     * @param b
     * @param o
     * @return
     */
    public static double crossProductFromNewOrigin(Vector2D a, Vector2D b, Vector2D o) {
        Vector2D aCopy = new Vector2D(a);
        Vector2D bCopy = new Vector2D(b);
        aCopy.translate(-o.getX(), -o.getY());
        bCopy.translate(-o.getX(), -o.getY());
        return MathUtil.crossProduct(aCopy, bCopy);
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean relativelyEqual(double a, double b) {
        return (a - EPS) <= b && (a + EPS) >= b;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean relativelyEqualBigEps(double a, double b) {
        return (a - BIG_EPS) <= b && (a + BIG_EPS) >= b;
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static double getAngleBetweenPoints(Vector2D a, Vector2D b) {
        return Math.atan2(a.getY() - b.getY(), a.getX() - b.getX());
    }

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static double getAngleBetweenVectors(Vector2D a, Vector2D b) {
        return Math.acos(MathUtil.dotProduct(a, b)
                / (MathUtil.distance(new Vector2D(0, 0), a)
                + MathUtil.distance(new Vector2D(0, 0), b)));
    }
}

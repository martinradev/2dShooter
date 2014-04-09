/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry;

import me.martin.radev.game.virtualcommando.geometry.entity.Line;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class MathUtil {

    /**
     * A small epsilon
     */
    public static final double EPS = 0.00005;
    /**
     * a big epsilon
     */
    public static final double BIG_EPS = 0.0002;
    /**
     * 
     */
    public static final double POSITIVE_INFINITY = Double.MAX_VALUE;
    /**
     *
     */
    public static final double NEGATIVE_INFINITY = Double.MIN_VALUE;

    /**
     * returns the cross product of vectors a and b
     * @param a
     * @param b
     * @return
     */
    public static double crossProduct(Vector2D a, Vector2D b) {
        return a.getX() * b.getY() - a.getY() * b.getX();
    }

    /**
     * returns the dot product of and b
     * @param a
     * @param b
     * @return
     */
    public static double dotProduct(Vector2D a, Vector2D b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }

    /**
     * returns the distance between points a and b
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
     * return the cross product of and b relative to a new origin o
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
     * returns whether a and b are equal in some small range.
     * For example relativelyEqual(0.0000000001, 0.0000000002) will return true 
     * @param a
     * @param b
     * @return
     */
    public static boolean relativelyEqual(double a, double b) {
        return (a - EPS) <= b && (a + EPS) >= b;
    }

    /**
     * returns whether a and b are relatively equal for a bigger epsilon
     * @param a
     * @param b
     * @return
     */
    public static boolean relativelyEqualBigEps(double a, double b) {
        return (a - BIG_EPS) <= b && (a + BIG_EPS) >= b;
    }

    /**
     * returns the angle between two points
     * @param a
     * @param b
     * @return
     */
    public static double getAngleBetweenPoints(Vector2D a, Vector2D b) {
        return Math.atan2(a.getY() - b.getY(), a.getX() - b.getX());
    }

    /**
     * returns the angles between two vectors
     * @param a
     * @param b
     * @return
     */
    public static double getAngleBetweenVectors(Vector2D a, Vector2D b) {
        return Math.acos(MathUtil.dotProduct(a, b)
                / (MathUtil.distance(new Vector2D(0, 0), a)
                + MathUtil.distance(new Vector2D(0, 0), b)));
    }
    
    public static boolean inBounds(Vector2D lowerBound, Vector2D upperBound,
            Vector2D point) {
        if (lowerBound.getX() > point.getX()) return false;
        if (lowerBound.getY() > point.getY()) return false;
        if (upperBound.getX() < point.getX()) return false;
        if (upperBound.getY() < point.getY()) return false;
        return true;
    }
    
    public static boolean linesIntersect(Line a, Line b) {
        return !a.onSameSideOfLine(b.getHead(), b.getTail()) &&
                !b.onSameSideOfLine(a.getHead(), a.getTail());
    }

    
}

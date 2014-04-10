/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import static me.martin.radev.game.virtualcommando.geometry.MathUtil.relativelyEqual;

/**
 *
 * @author Marto
 */
public class Line extends GeometricObject {

    private Vector2D head;
    private Vector2D tail;

    public Line(Vector2D head, Vector2D tail) {
        super();
        if (head.getX() < tail.getX()) {
            this.head = head;
            this.tail = tail;
        } else if (head.getX() == tail.getX()) {
            if (head.getY() < tail.getY()) {
                this.head = head;
                this.tail = tail;
            } else {
                this.head = tail;
                this.tail = head;
            }
        } else {
            this.head = tail;
            this.tail = head;
        }

    }

    @Override
    public Vector2D getCenter() {
        return new Vector2D((head.getX() + tail.getX()) / 2d,
                (head.getY() + tail.getY()) / 2d);
    }

    @Override
    public void translate(double dx, double dy) {
        head.translate(dx, dy);
        tail.translate(dx, dy);
    }

    @Override
    public void rotate(double angle) {
        head.rotate(angle);
        tail.rotate(angle);
    }

    @Override
    public boolean contains(Vector2D v2d) {
        return this.liesOnLine(v2d);
    }

    @Override
    public void relativeRotate(Vector2D center, double angle) {
        head.translate(-center.getX(), -center.getY());
        tail.translate(-center.getX(), -center.getY());
        head.rotate(angle);
        tail.rotate(angle);
    }

    @Override
    public Vector2D[] getBoundingBox() {
        return new Vector2D[]{head, tail};
    }

    public Vector2D getHead() {
        return head;
    }

    public Vector2D getTail() {
        return tail;
    }

    public Vector2D generateVector() {
        return new Vector2D(head.getX() - tail.getX(),
                head.getY() - tail.getY());
    }

    public boolean isParallel(Line l) {
        double areaBetween =
                MathUtil.crossProduct(generateVector(), l.generateVector());
        return MathUtil.relativelyEqual(areaBetween, 0d);
    }

    public double distanceToPoint(Vector2D point) {
        Vector2D P = new Vector2D(point);
        Vector2D A = new Vector2D(this.getHead());
        Vector2D B = new Vector2D(this.getTail());
        P.translate(-A.getX(), -A.getY());
        double crossProduct = MathUtil.crossProduct(P, B);
        return crossProduct / B.getMagnitude();
    }

    public boolean liesOnLine(Vector2D point) {
        double distance = distanceToPoint(point);
        return MathUtil.relativelyEqual(distance, 0d)
                && MathUtil.inBounds(head, tail, point);
    }
    
    public boolean onSameSideOfLine(Vector2D pointA, Vector2D pointB) {
        double crossA = MathUtil.crossProductFromNewOrigin(head, tail, pointA);
        double crossB = MathUtil.crossProductFromNewOrigin(head, tail, pointB);
        return crossA*crossB >= 0;
    }
    
    public double getSlope() {
        double dx = tail.getX() - head.getX();
        double dy = tail.getY() - head.getY();
        if (relativelyEqual(dx, 0d)) {
            if (dy < 0) {
                return MathUtil.NEGATIVE_INFINITY;
            } else {
                return MathUtil.POSITIVE_INFINITY;
            }
        }
        return dy/dx;
    }
    
}

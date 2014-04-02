/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import java.awt.Point;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;

/**
 *
 * @author Marto
 */
public class Vector2D extends GeometricObject {
    
    private double x;
    private double y;
    
    /**
     *
     * @param x
     * @param y
     */
    public Vector2D(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    /**
     *
     * @param u
     */
    public Vector2D(Vector2D u) {
        this.x = u.getX();
        this.y = u.getY();
    }
    
    /**
     *
     * @param p
     */
    public Vector2D(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @param dx
     * @param dy
     */
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     *
     * @param angle
     */
    public void rotate(double angle) {
        double nx = Math.cos(angle)*this.x - Math.sin(angle)*this.y;
        double ny = Math.sin(angle)*this.x + Math.cos(angle)*this.y;
        this.setX(nx);
        this.setY(ny);
    }

    /**
     *
     * @param v2d
     * @return
     */
    public boolean contains(Vector2D v2d) {
        return this.equals(v2d);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        Vector2D v2do = (Vector2D) o;
        return MathUtil.relativelyEqual(v2do.getX(),this.getX())
                && MathUtil.relativelyEqual(v2do.getY(), this.getY());
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "[ " + this.getX() + " : " + this.getY() + " ]";
    }

    /**
     *
     * @return
     */
    public Vector2D getUnitVector() {
        double dist = MathUtil.distance(new Vector2D(0d,0d), this);
        Vector2D temp = new Vector2D(this);
        if (dist == 0.0) return this;
        temp.scale(1d / dist);
        return temp;
    }
    
    /**
     *
     * @param factor
     */
    public void scale(double factor) {
        this.setX(this.getX()*factor);
        this.setY(this.getY()*factor);
    }

    /**
     *
     * @return
     */
    public Vector2D getCenter() {
        return this;
    }

    /**
     *
     * @param center
     * @param angle
     */
    public void relativeRotate(Vector2D center, double angle) {
        this.translate(-center.getX(), -center.getY());
        this.rotate(angle);
        this.translate(center.getX(), center.getY());
    }

    /**
     *
     * @return
     */
    @Override
    public Vector2D[] getBoundingBox() {
        Vector2D [] boundingBox = new Vector2D[2];
        boundingBox[0] = new Vector2D(this);
        boundingBox[1] = new Vector2D(this);
        return boundingBox;
    }
    
    

}

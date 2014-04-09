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
     * Creates a vector with given x and y values
     * @param x
     * @param y
     */
    public Vector2D(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    /**
     * Creates a copy of a vector u
     * @param u
     */
    public Vector2D(Vector2D u) {
        this.x = u.getX();
        this.y = u.getY();
    }
    
    /**
     * Creates a vector from an awt point
     * @param p
     */
    public Vector2D(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * returns the y-coordinate of the vector
     * @return
     */
    public double getY() {
        return y;
    }

    /** 
     * sets the y-coordinate of the vector
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * returns the x-coordinate of the vector
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * sets the x-coordinate of the vector
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * translates the vector by dx and dy
     * @param dx
     * @param dy
     */
    @Override
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * rotates the vector around the origin by a given angle
     * @param angle
     */
    @Override
    public void rotate(double angle) {
        double nx = Math.cos(angle)*this.x - Math.sin(angle)*this.y;
        double ny = Math.sin(angle)*this.x + Math.cos(angle)*this.y;
        this.setX(nx);
        this.setY(ny);
    }

    /**
     * checks whether the vector contains another vector. This is only true
     * when the coordinates of both vectors are equal
     * @param v2d
     * @return
     */
    @Override
    public boolean contains(Vector2D v2d) {
        return this.equals(v2d);
    }

    /**
     * returns true if both vectors have the same coordinates
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
     * returns a tuple of the x and y coordinates
     * @return
     */
    @Override
    public String toString() {
        return "[ " + this.getX() + " : " + this.getY() + " ]";
    }

    /**
     * returns an unit vector of the given vector. The unit vector contains only
     * the direction, but not the magnitude.
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
     * scales the magnitude of the vector by a given factor
     * @param factor
     */
    public void scale(double factor) {
        this.setX(this.getX()*factor);
        this.setY(this.getY()*factor);
    }

    /**
     * returns the vector itself
     * @return
     */
    @Override
    public Vector2D getCenter() {
        return this;
    }

    /**
     * rotates the vector around a new center by a given angle
     * @param center
     * @param angle
     */
    @Override
    public void relativeRotate(Vector2D center, double angle) {
        this.translate(-center.getX(), -center.getY());
        this.rotate(angle);
        this.translate(center.getX(), center.getY());
    }

    /**
     * the bounding box is the vector itself.
     * @return
     */
    @Override
    public Vector2D[] getBoundingBox() {
        Vector2D [] boundingBox = new Vector2D[2];
        boundingBox[0] = new Vector2D(this);
        boundingBox[1] = new Vector2D(this);
        return boundingBox;
    }
    
    public double getMagnitude() {
        return Math.sqrt(x*x + y*y);
    }

}

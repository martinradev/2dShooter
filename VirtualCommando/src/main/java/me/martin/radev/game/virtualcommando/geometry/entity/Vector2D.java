/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import me.martin.radev.game.virtualcommando.geometry.MathUtil;

/**
 *
 * @author Marto
 */
public class Vector2D implements GeometricObject {
    
    private double x;
    private double y;
    
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2D(Vector2D u) {
        this.x = u.getX();
        this.y = u.getY();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public void rotate(double angle) {
        double nx = Math.cos(angle)*this.x - Math.sin(angle)*this.y;
        double ny = Math.sin(angle)*this.x + Math.cos(angle)*this.y;
        this.setX(nx);
        this.setY(ny);
    }

    public boolean contains(Vector2D v2d) {
        return this.equals(v2d);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        Vector2D v2do = (Vector2D) o;
        return MathUtil.relativelyEqual(v2do.getX(),this.getX())
                && MathUtil.relativelyEqual(v2do.getY(), this.getY());
    }

    @Override
    public String toString() {
        return "[ " + this.getX() + " : " + this.getY() + " ]";
    }

    public Vector2D getUnitVector() {
        double dist = MathUtil.distance(new Vector2D(0d,0d), this);
        Vector2D temp = new Vector2D(this);
        if (dist == 0.0) return this;
        temp.scale(1d / dist);
        return temp;
    }
    
    public void scale(double factor) {
        this.setX(this.getX()*factor);
        this.setY(this.getY()*factor);
    }

}

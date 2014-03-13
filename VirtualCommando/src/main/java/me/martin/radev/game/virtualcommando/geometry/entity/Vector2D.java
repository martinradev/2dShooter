/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean contains(Vector2D v2d) {
        return this.equals(v2d);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        Vector2D v2do = (Vector2D) o;
        return v2do.getX() == this.getX() && v2do.getY() == this.getY();
    }

    @Override
    public String toString() {
        return "[ " + this.getX() + " : " + this.getY() + " ]";
    }
    
    
    
    
     
}

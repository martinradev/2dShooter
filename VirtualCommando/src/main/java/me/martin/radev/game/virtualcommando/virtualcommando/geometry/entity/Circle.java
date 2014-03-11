/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.virtualcommando.geometry.entity;

import me.martin.radev.game.virtualcommando.virtualcommando.geometry.MathUtil;

/**
 *
 * @author Marto
 */
public class Circle implements GeometricObject {

    private Vector2D center;
    private double radius;
    
    public Circle(Vector2D center, float radius) {
        this.center = center;
        this.radius = radius;
    }
    
    public void translate(double dx, double dy) {
        this.center.translate(dx, dy);
    }

    public void rotate(double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean contains(Vector2D v2d) {
        if (v2d == null) return false;
        return MathUtil.distance(v2d, this.getCenter()) <= this.getRadius();
    }

    public Vector2D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
    
    
    
    
    
}

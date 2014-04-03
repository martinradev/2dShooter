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
public class Circle extends Ellipse {

    private Vector2D center;
    
    /**
     * Creates a circle with a center and a radius
     * @param center
     * @param radius
     */
    public Circle(Vector2D center, double radius) {
        super(center, radius, radius);
    }

    /**
     * returns true if the circle contains a point
     * @param v2d
     * @return
     */
    @Override
    public boolean contains(Vector2D v2d) {
        if (v2d == null) return false;
        return MathUtil.distance(v2d, this.getCenter()) <= this.getRadius();
    }

    /**
     * rotate doesn't do anything since a rotated circle is still the same circle
     * @param angle
     */
    @Override
    public void rotate(double angle) {
        // do nothing
    }

    /**
     * returns the radius of the circle
     * @return
     */
    public double getRadius() {
        return super.getMajorAxis();
    }
    
    
    
    
    
}

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
     *
     * @param center
     * @param radius
     */
    public Circle(Vector2D center, double radius) {
        super(center, radius, radius);
    }

    /**
     *
     * @param v2d
     * @return
     */
    @Override
    public boolean contains(Vector2D v2d) {
        if (v2d == null) return false;
        return MathUtil.distance(v2d, this.getCenter()) <= this.getRadius();
    }

    /**
     *
     * @param angle
     */
    @Override
    public void rotate(double angle) {
        // do nothing
    }

    /**
     *
     * @return
     */
    public double getRadius() {
        return super.getMajorAxis();
    }
    
    
    
    
    
}

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
    
    public Circle(Vector2D center, float radius) {
        super(center, radius, radius);
    }

    @Override
    public boolean contains(Vector2D v2d) {
        if (v2d == null) return false;
        return MathUtil.distance(v2d, this.getCenter()) <= this.getRadius();
    }

    @Override
    public void rotate(double angle) {
        // do nothing
    }

    public double getRadius() {
        return super.getMajorAxis();
    }
    
    
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

/**
 *
 * @author Marto
 */
public abstract class GeometricObject {
    
    /**
     *
     * @return
     */
    public abstract Vector2D getCenter();
    /**
     *
     * @param dx
     * @param dy
     */
    public abstract void translate(double dx, double dy);
    /**
     *
     * @param angle
     */
    public abstract void rotate(double angle);
    /**
     *
     * @param v2d
     * @return
     */
    public abstract boolean contains(Vector2D v2d);
    /**
     *
     * @param center
     * @param angle
     */
    public abstract void relativeRotate(Vector2D center, double angle);
    /**
     *
     * @return
     */
    public abstract Vector2D [] getBoundingBox(); 

    
    
    
}

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
     * returns the center of the figure
     * @return
     */
    public abstract Vector2D getCenter();
    /**
     * translates the figure
     * @param dx
     * @param dy
     */
    public abstract void translate(double dx, double dy);
    /**
     * rotates the figure by a given angle
     * @param angle
     */
    public abstract void rotate(double angle);
    /**
     * returns true if the figure contains the point
     * @param v2d
     * @return
     */
    public abstract boolean contains(Vector2D v2d);
    /**
     * relatively rotates the figure by a given angle around a given center
     * @param center
     * @param angle
     */
    public abstract void relativeRotate(Vector2D center, double angle);
    /**
     * returns the bounding box.
     * Index 0 is the bottom left corner and 1 is the top right corner.
     * @return
     */
    public abstract Vector2D [] getBoundingBox(); 

    
    
    
}

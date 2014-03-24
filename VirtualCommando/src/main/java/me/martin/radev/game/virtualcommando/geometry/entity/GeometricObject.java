/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

/**
 *
 * @author Marto
 */
public abstract class GeometricObject implements Cloneable {
    
    public abstract Vector2D getCenter();
    public abstract void translate(double dx, double dy);
    public abstract void rotate(double angle);
    public abstract boolean contains(Vector2D v2d);
    public abstract void relativeRotate(Vector2D center, double angle);

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

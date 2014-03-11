/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.virtualcommando.geometry.entity;

/**
 *
 * @author Marto
 */
public interface GeometricObject {
    
    void translate(double dx, double dy);
    void rotate(double angle);
    boolean contains(Vector2D v2d);
    
}

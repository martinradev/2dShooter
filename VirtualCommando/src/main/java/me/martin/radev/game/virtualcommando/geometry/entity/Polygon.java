/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import java.util.List;

/**
 *
 * @author Marto
 */
public class Polygon extends AbstractPolygon {
    
    public Polygon(List<Vector2D> points) {
        super(points);
    }
    
    public Polygon(Vector2D [] points) {
        super(points);
    }

    @Override
    public boolean contains(Vector2D point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void rotate(double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

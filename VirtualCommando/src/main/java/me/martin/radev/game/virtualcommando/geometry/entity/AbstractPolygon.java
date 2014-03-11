/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.virtualcommando.geometry.entity;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Marto
 */
public abstract class AbstractPolygon implements GeometricObject {
    
    private List<Vector2D> points;
    
    public AbstractPolygon(List<Vector2D> points) {
        this.points = points;
    }
    
    public AbstractPolygon(Vector2D [] points) {
        this.points = Arrays.asList(points);
    }

    public void translate(double dx, double dy) {
        for (Vector2D v : points) {
            v.translate(dx, dy);
        }
    }

    public List<Vector2D> getPoints() {
        return points;
    }
    
    public abstract boolean contains(Vector2D point);
    
}

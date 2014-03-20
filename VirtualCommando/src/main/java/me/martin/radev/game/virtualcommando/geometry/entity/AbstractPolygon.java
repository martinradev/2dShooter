/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

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

    public void setPoints(List<Vector2D> points) {
        this.points = points;
    }
    
    public Vector2D getBottomLeftCorner() {
        Vector2D point = new Vector2D(points.get(0));
        for (Vector2D p : points) {
            if (point.getY() > p.getY()) point.setY(p.getY());
            if (point.getX() > p.getX()) point.setX(p.getX());
        }
        return point;
    }
    
    public Vector2D getTopRightCorner() {
        Vector2D point = new Vector2D(points.get(0));
        for (Vector2D p : getPoints()) {
            if (point.getY() < p.getY()) point.setY(p.getY());
            if (point.getX() < p.getX()) point.setX(p.getX());
        }
        return point;
    }
    
}

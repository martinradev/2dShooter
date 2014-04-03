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
public abstract class AbstractPolygon extends GeometricObject {
    
    private List<Vector2D> points;
    
    /**
     * constructs an abstract polygon given a list of points. The points must be
     * in counterclockwise direction.
     * @param points
     */
    public AbstractPolygon(List<Vector2D> points) {
        super();
        this.points = points;
    }
    
    /**
     * constructs an abstract polygon given an array of points.
     * The points must be in counterclockwise direction.
     * @param points
     */
    public AbstractPolygon(Vector2D [] points) {
        this.points = Arrays.asList(points);
    }

    /**
     * translates the object with an offset of dx,dy
     * @param dx
     * @param dy
     */
    @Override
    public void translate(double dx, double dy) {
        for (Vector2D v : points) {
            v.translate(dx, dy);
        }
    }

    /**
     * returns a list of the points forming the object
     * @return
     */
    public List<Vector2D> getPoints() {
        return points;
    }

    /**
     * sets the points of the figure
     * @param points
     */
    public void setPoints(List<Vector2D> points) {
        this.points = points;
    }
    
    /**
     * returns the bottom left corner of the bounding box for this figure
     * @return
     */
    public Vector2D getBottomLeftCorner() {
        Vector2D point = new Vector2D(points.get(0));
        for (Vector2D p : points) {
            if (point.getY() > p.getY()) point.setY(p.getY());
            if (point.getX() > p.getX()) point.setX(p.getX());
        }
        return point;
    }
    
    /**
     * returns top right corner for the bounding box of the figure
     * @return
     */
    public Vector2D getTopRightCorner() {
        Vector2D point = new Vector2D(points.get(0));
        for (Vector2D p : getPoints()) {
            if (point.getY() < p.getY()) point.setY(p.getY());
            if (point.getX() < p.getX()) point.setX(p.getX());
        }
        return point;
    }
    
}

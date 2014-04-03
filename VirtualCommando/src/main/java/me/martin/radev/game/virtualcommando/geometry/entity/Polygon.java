/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import java.util.List;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;

/**
 *
 * @author Marto
 */
public class Polygon extends AbstractPolygon {
    
    /**
     * Creates a polygon given a list of points. The points must be in 
     * counterclockwise direction.
     * @param points
     */
    public Polygon(List<Vector2D> points) {
        super(points);
    }
    
    /**
     * Creates a polygon given an array of points. The points must be in 
     * counterclockwise direction.
     * @param points
     */
    public Polygon(Vector2D [] points) {
        super(points);
    }

    /**
     * Checks whether the polygon contains a point. This is computed by 
     * examining the cross product of the points and the selected point.
     * It will work only for convex polygons.
     * @param point
     * @return
     */
    @Override
    public boolean contains(Vector2D point) {
        List<Vector2D> points = super.getPoints();
        int size = super.getPoints().size();
        for (int i = 0; i < size; ++i) {
            if (MathUtil.crossProductFromNewOrigin(
                   point, points.get((i+1)%size), points.get(i)) < 0) return false;
        }
        return true;
    }
    
    /**
     * rotates the figure by an angle
     * @param angle
     */
    @Override
    public void rotate(double angle) {
        for (Vector2D v2d : super.getPoints()) {
            v2d.rotate(angle);
        }
    }

    /**
     * returns the center of the polygon. It is computed by taking the 
     * average of all x and y values of the points;
     * @return
     */
    @Override
    public Vector2D getCenter() {
        double dx = 0d, dy =0d;
        List<Vector2D> points = this.getPoints();
        for (Vector2D p : points) {
            dx += p.getX();
            dy += p.getY();
        }
        double size = (double)points.size();
        return new Vector2D(dx/ size, dy / size);
    }

    /**
     * Rotates the polygon around a new center by a given angle.
     * @param center
     * @param angle
     */
    @Override
    public void relativeRotate(Vector2D center, double angle) {
        for (Vector2D v2d : super.getPoints()) {
            v2d.relativeRotate(center, angle);
        }
    }

    /**
     * returns the string of the polygon. This is just a list representation
     * of all of the vertices.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vector2D v2d : super.getPoints()) {
            sb.append(v2d).append(", ");
        }
        return sb.toString();
    }

    /**
     * returns the bounding box of the polygon
     * @return
     */
    @Override
    public Vector2D[] getBoundingBox() {
        Vector2D [] boundingBox = new Vector2D[2];
        double minX = MathUtil.POSITIVE_INFINITY;
        double minY = MathUtil.POSITIVE_INFINITY;
        double maxX = MathUtil.NEGATIVE_INFINITY;
        double maxY = MathUtil.NEGATIVE_INFINITY;
        for (Vector2D v2d : super.getPoints()) {
            if (minX > v2d.getX()) minX = v2d.getX();
            if (minY > v2d.getY()) minY = v2d.getY();
            if (maxX < v2d.getX()) maxX = v2d.getX();
            if (maxY < v2d.getY()) maxY = v2d.getY();
        }
        boundingBox[0] = new Vector2D(minX, minY);
        boundingBox[1] = new Vector2D(maxX, maxY);
        return boundingBox;
    }
    
    
    
    
}

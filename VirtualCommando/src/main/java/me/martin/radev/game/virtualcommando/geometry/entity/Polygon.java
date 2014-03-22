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
    
    public Polygon(List<Vector2D> points) {
        super(points);
    }
    
    public Polygon(Vector2D [] points) {
        super(points);
    }

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
    
    @Override
    public void rotate(double angle) {
        for (Vector2D v2d : super.getPoints()) {
            v2d.rotate(angle);
        }
    }

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

    @Override
    public void relativeRotate(Vector2D center, double angle) {
        for (Vector2D v2d : super.getPoints()) {
            v2d.relativeRotate(center, angle);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vector2D v2d : super.getPoints()) {
            sb.append(v2d + ", ");
        }
        return sb.toString();
    }
    
    
    
    
}

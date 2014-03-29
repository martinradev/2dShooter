/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import java.util.List;
import me.martin.radev.game.virtualcommando.Global;
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
        /*
        double offsetX = Global.getGame().getScreen().getOffsetX();
        double offsetY = Global.getGame().getScreen().getOffsetY();
        point = new Vector2D(point);
        point.translate(-offsetX, -offsetY);
        */
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

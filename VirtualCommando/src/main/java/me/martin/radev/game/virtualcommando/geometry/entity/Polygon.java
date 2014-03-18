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
        for (int i = 0; i <= size; ++i) {
            if (MathUtil.crossProductFromNewOrigin(
                   point, points.get((i+1)%size), points.get(i)) > 0) return false;
        }
        return true;
    }
    
    public void rotate(double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

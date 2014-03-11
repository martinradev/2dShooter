/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.virtualcommando.geometry.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marto
 */
public class Rectangle extends AbstractPolygon {

    public Rectangle(Vector2D bottomLeftCorner, Vector2D bottomRightCorner) {
        super(new Vector2D[] {bottomLeftCorner, bottomRightCorner});
        List<Vector2D> points = new ArrayList<Vector2D>();
    }

    @Override
    public boolean contains(Vector2D point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void translate(double dx, double dy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void rotate(double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

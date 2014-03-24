/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

/**
 *
 * @author Marto
 */
public class Ellipse extends GeometricObject {

    private Vector2D center;
    private double majorAxis;
    private double minorAxis;
    
    public Ellipse(Vector2D center, double majorAxis, double minorAxis) {
        super();
        this.center = center;
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
    }

    public Vector2D getCenter() {
        return center;
    }

    public double getMajorAxis() {
        return majorAxis;
    }

    public double getMinorAxis() {
        return minorAxis;
    }
    
    public void translate(double dx, double dy) {
        center.translate(dx, dy);
    }

    public void rotate(double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean contains(Vector2D v2d) {
        if (v2d == null) return false;
        double d1 = Math.pow(this.getCenter().getX() - v2d.getX(),2);
        double d2 = Math.pow(this.getCenter().getY() - v2d.getY(),2);
        double result = (d1 / (majorAxis*majorAxis)) + (d2 / (minorAxis*minorAxis));
        return result <= 1;
    }

    public void relativeRotate(Vector2D center, double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

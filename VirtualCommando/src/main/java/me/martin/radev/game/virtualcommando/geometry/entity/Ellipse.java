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
    
    /**
     *
     * @param center
     * @param majorAxis
     * @param minorAxis
     */
    public Ellipse(Vector2D center, double majorAxis, double minorAxis) {
        super();
        this.center = center;
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
    }

    /**
     *
     * @return
     */
    public Vector2D getCenter() {
        return center;
    }

    /**
     *
     * @return
     */
    public double getMajorAxis() {
        return majorAxis;
    }

    /**
     *
     * @return
     */
    public double getMinorAxis() {
        return minorAxis;
    }
    
    /**
     *
     * @param dx
     * @param dy
     */
    @Override
    public void translate(double dx, double dy) {
        center.translate(dx, dy);
    }

    /**
     *
     * @param angle
     */
    @Override
    public void rotate(double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param v2d
     * @return
     */
    @Override
    public boolean contains(Vector2D v2d) {
        if (v2d == null) return false;
        double d1 = Math.pow(0.5*majorAxis + this.getCenter().getX() - v2d.getX(),2);
        double d2 = Math.pow(0.5*minorAxis + this.getCenter().getY() - v2d.getY(),2);
        double result = (d1 / (0.25*majorAxis*majorAxis)) + (d2 / (0.25*minorAxis*minorAxis));
        return result <= 1d;
    }

    /**
     *
     * @param center
     * @param angle
     */
    @Override
    public void relativeRotate(Vector2D center, double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public Vector2D[] getBoundingBox() {
        Vector2D [] boundingBox = new Vector2D[2];
        double minX = this.getCenter().getX() - this.getMajorAxis();
        double minY = this.getCenter().getY() - this.getMinorAxis();
        boundingBox[0] = new Vector2D(minX, minY);
        double maxX = this.getCenter().getX() + this.getMajorAxis();
        double maxY = this.getCenter().getY() + this.getMinorAxis();
        boundingBox[1] = new Vector2D(maxX, maxY);
        return boundingBox;
    }
    
    
    
}

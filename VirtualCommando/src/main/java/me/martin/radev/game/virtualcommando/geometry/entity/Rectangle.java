/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

/**
 *
 * @author Marto
 */
public class Rectangle extends Polygon {
    
    private double width;
    private double height;

    /**
     * Creates a rectangle with a bottom left corner, width and height
     * @param bottomLeftCorner
     * @param width
     * @param height
     */
    public Rectangle(Vector2D bottomLeftCorner, double width, double height) {
        super(new Vector2D[]{bottomLeftCorner,
            new Vector2D(bottomLeftCorner.getX(), bottomLeftCorner.getY() + height),
            new Vector2D(bottomLeftCorner.getX() + width, bottomLeftCorner.getY() + height),
            new Vector2D(bottomLeftCorner.getX() + width, bottomLeftCorner.getY())});
        this.width = width;
        this.height = height;
    }
    
    public Rectangle(Vector2D bottomLeftCorner, Vector2D topRightCorner) {
        super(new Vector2D[]{bottomLeftCorner,
            new Vector2D(bottomLeftCorner.getX(), topRightCorner.getY()),
            new Vector2D(topRightCorner.getX(), topRightCorner.getY()),
            new Vector2D(topRightCorner.getX(), bottomLeftCorner.getY())});
        this.width = topRightCorner.getX() - bottomLeftCorner.getX();
        this.height = topRightCorner.getY() - bottomLeftCorner.getY();
    }

    /**
     * Creates a rectangle with a bottom left corner, width and height
     * @param xCoord
     * @param yCoord
     * @param width
     * @param height
     */
    public Rectangle(double xCoord, double yCoord,
            double width, double height) {
        super(new Vector2D[]{new Vector2D(xCoord, yCoord),
            new Vector2D(xCoord, yCoord + height),
            new Vector2D(xCoord + width, yCoord + height),
            new Vector2D(xCoord + width, yCoord)});
        this.width = width;
        this.height = height;
    }

    /**
     * returns the height of the rectangle
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * returns the with of the polygon
     * @return
     */
    public double getWidth() {
        return width;
    }
    
    /*
    @Override
    public boolean contains(Vector2D point) {
        Vector2D bottomLeft = getBottomLeftCorner();
        if (point.getX() < bottomLeft.getX() || 
                point.getY() < bottomLeft.getY()) return false;
        if (point.getX() > bottomLeft.getX()+getWidth() ||
                point.getY() > bottomLeft.getY()+getHeight()) return false;
        return true;
    }
    */
    
    
}

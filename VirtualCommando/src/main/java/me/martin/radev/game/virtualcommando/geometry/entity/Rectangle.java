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

    private Vector2D bottomLeftCorner;
    private double width;
    private double height;
    
    public Rectangle(Vector2D bottomLeftCorner, double width, double height) {
        super(new Vector2D[] {bottomLeftCorner, 
        new Vector2D(bottomLeftCorner.getX() + width, bottomLeftCorner.getY()),
        new Vector2D(bottomLeftCorner.getX() + width, bottomLeftCorner.getY() + height),
        new Vector2D(bottomLeftCorner.getX(), bottomLeftCorner.getY() + height)});
        this.bottomLeftCorner = bottomLeftCorner;
        this.width = width;
        this.height = height;
    }
    
    public Rectangle(double xCoord, double yCoord, 
            double width, double height) {
        super (new Vector2D[] {new Vector2D(xCoord, yCoord),
        new Vector2D(xCoord + width, yCoord),
        new Vector2D(xCoord + width, yCoord + height),
        new Vector2D(xCoord, yCoord + height)});
        this.bottomLeftCorner = new Vector2D(xCoord, yCoord);
        this.width = width;
        this.height = height;
    }

    @Override
    public void translate(double dx, double dy) {
        super.translate(dx, dy);
        System.out.println(getBottomLeftCorner());
    }
    
    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
    
    
}

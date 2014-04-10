/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import me.martin.radev.game.virtualcommando.geometry.entity.Ellipse;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 * GraphicalEllipse is a wrapper for the geometric ellipse class. 
 * @author Marto
 */
public class GraphicalEllipse extends GraphicalObject {
    
    /**
     * Creates an ellipse from a center, majorAxis, minorAxis and color
     * @param center
     * @param majorAxis
     * @param minorAxis
     * @param color
     */
    public GraphicalEllipse(Vector2D center, double majorAxis, double minorAxis, Color color) {
        super(new Ellipse(center, majorAxis, minorAxis), color);
    }
    
    /**
     * creates a graphical ellipse from a center defined by xCoord and yCoord,
     * a major axis, minor axis and a color
     * @param xCoord
     * @param yCoord
     * @param majorAxis
     * @param minorAxis
     * @param color
     */
    public GraphicalEllipse(double xCoord, double yCoord, 
            double majorAxis, double minorAxis, Color color) {
        super(new Ellipse(new Vector2D(xCoord, yCoord), majorAxis, minorAxis), color);
    }
    
    /**
     * renders the ellipse on a {@link Graphics2D} object with a given x offset and
     * y offset
     * @param g2d
     * @param xOffset
     * @param yOffset
     */
    @Override
    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        g2d.setColor(super.getColor());
        Ellipse body = this.getBody();
        Vector2D center = body.getCenter();
        g2d.fillOval((int)center.getX() + xOffset, (int)center.getY() + yOffset, 
                (int)body.getMajorAxis(), (int)body.getMinorAxis());
    }

    /**
     * returns the body of the ellipse. The body is a geometrical object in the
     * geometric world of the game.
     * @return
     */
    @Override
    public Ellipse getBody() {
        return (Ellipse)super.getBody(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

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
 *
 * @author Marto
 */
public class GraphicalEllipse implements GraphicalObject {

    private Ellipse body;
    private Color color;
    
    public GraphicalEllipse(Vector2D center, double majorAxis, double minorAxis, Color color) {
        body = new Ellipse(center, majorAxis, minorAxis);
        this.color = color;
    }
    
    public GraphicalEllipse(double xCoord, double yCoord, 
            double majorAxis, double minorAxis, Color color) {
        body = new Ellipse(new Vector2D(xCoord, yCoord), majorAxis, minorAxis);
        this.color = color;
    }
    
    public GeometricObject getBody() {
        return body;
    }

    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        g2d.setColor(color);
        Vector2D center = body.getCenter();
        g2d.fillOval((int)center.getX() + xOffset, (int)center.getY() + yOffset, 
                (int)body.getMajorAxis(), (int)body.getMinorAxis());
    }
    
}

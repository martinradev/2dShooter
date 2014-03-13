/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.geometry.entity.Rectangle;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class GraphicalRectangle implements GraphicalObject {
    
    private Rectangle body;
    private Color color;
    
    public GraphicalRectangle(Vector2D bottomLeft, double width, double height, Color color) {
        body = new Rectangle(bottomLeft, width, height);
        this.color = color;
    }
    
    public GraphicalRectangle(double xCoord, double yCoord, double width, double height, Color color) {
        body = new Rectangle(xCoord, yCoord, width, height);
        this.color = color;
    }
    
    public GeometricObject getBody() {
        return this.body;
    }

    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        g2d.setColor(color);
        Vector2D bottomLeft = body.getBottomLeftCorner();
        g2d.fillRect(xOffset + (int)bottomLeft.getX(), 
                yOffset + (int)bottomLeft.getY(), (int)body.getWidth(), (int)body.getHeight());
    }


}

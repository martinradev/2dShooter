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
public class GraphicalRectangle extends GraphicalPolygon {

    private Rectangle body;
    private Color color;

    public GraphicalRectangle(Vector2D bottomLeft, double width, double height, Color color) {
        super(new Vector2D[]{new Vector2D(bottomLeft.getX(), bottomLeft.getY()),
            new Vector2D(bottomLeft.getX() + width, bottomLeft.getY()),
            new Vector2D(bottomLeft.getX() + width, bottomLeft.getY() + height),
            new Vector2D(bottomLeft.getX(), bottomLeft.getY() + height)}, color);
        body = new Rectangle(bottomLeft, width, height);
        this.color = color;
    }

    public GraphicalRectangle(double xCoord, double yCoord, double width, double height, Color color) {
        super(
                new Vector2D[]{new Vector2D(xCoord, yCoord),
            new Vector2D(xCoord + width, yCoord),
            new Vector2D(xCoord + width, yCoord + height),
            new Vector2D(xCoord, yCoord + height)}, color);
        body = new Rectangle(xCoord, yCoord, width, height);
        this.color = color;
    }

    @Override
    public GeometricObject getBody() {
        return super.getBody();
    }
    
    
    
    /*
     public void render(Graphics2D g2d, int xOffset, int yOffset) {
     g2d.setColor(color);
     Vector2D bottomLeft = body.getBottomLeftCorner();
     g2d.fillRect(xOffset + (int)bottomLeft.getX(), 
     yOffset + (int)bottomLeft.getY(), (int)body.getWidth(), (int)body.getHeight());
     }
     * */

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.geometry.entity.Polygon;
import me.martin.radev.game.virtualcommando.geometry.entity.Rectangle;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class GraphicalRectangle extends GraphicalPolygon {

    private Color color;
    private Sprite sprite;

    public GraphicalRectangle(Vector2D bottomLeft, double width, double height, Color color) {
        super(new Vector2D[]{new Vector2D(bottomLeft.getX(), bottomLeft.getY()),
            new Vector2D(bottomLeft.getX() + width, bottomLeft.getY()),
            new Vector2D(bottomLeft.getX() + width, bottomLeft.getY() + height),
            new Vector2D(bottomLeft.getX(), bottomLeft.getY() + height)}, color);
        //body = new Rectangle(bottomLeft, width, height);
        this.color = color;
    }

    public GraphicalRectangle(double xCoord, double yCoord, double width, double height, Color color) {
        super(
                new Vector2D[]{new Vector2D(xCoord, yCoord),
            new Vector2D(xCoord + width, yCoord),
            new Vector2D(xCoord + width, yCoord + height),
            new Vector2D(xCoord, yCoord + height)}, color);
        //body = new Rectangle(xCoord, yCoord, width, height);
        this.color = color;
    }

    @Override
    public Polygon getBody() {
        return super.getBody();
    }
    
    @Override
    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        super.render(g2d, xOffset, yOffset);
        if (sprite != null) {
            Vector2D sprV2d = new Vector2D(super.getBody().getBottomLeftCorner());
            
            g2d.drawImage(sprite.getImage(), (int)sprV2d.getX(), 
                    (int)sprV2d.getY(), null);
                    
        } 
    }
    
    public void render(Graphics2D g2d, int xOffset, int yOffset, double angle) {
        super.render(g2d, xOffset, yOffset);
        if (sprite != null) {
            Vector2D sprV2d = new Vector2D(super.getBody().getCenter());
            g2d.translate(sprV2d.getX(), sprV2d.getY());
            g2d.rotate(angle);
            g2d.drawImage(sprite.getImage(), -sprite.getWidth()/2, 
                    -sprite.getHeight()/2, null);
            g2d.rotate(-angle);
            g2d.translate(-sprV2d.getX(), -sprV2d.getY());        
        }
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import me.martin.radev.game.virtualcommando.geometry.entity.Rectangle;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 * A graphical rectangle wraps a geometric rectangle. The graphical rectangle is a rectangle which gets rendered. 
 * @author Marto
 */
public class GraphicalRectangle extends GraphicalObject {
    
    private TexturePaint texture;
    // should check this TODO
    private Sprite sprite;

    /**
     * Creates a graphical rectangle with a bottom left corner, width height
     * and a color
     * @param bottomLeft
     * @param width
     * @param height
     * @param color
     */
    public GraphicalRectangle(Vector2D bottomLeft, double width, double height, Color color) {
        super(new Rectangle(bottomLeft, width, height), color);
    }

    /**
     * Creates a graphical rectangle with the coordinates of its bottom left
     * corner, width, height and color
     * @param xCoord
     * @param yCoord
     * @param width
     * @param height
     * @param color
     */
    public GraphicalRectangle(double xCoord, double yCoord, double width, double height, Color color) {
        super(new Rectangle(xCoord, yCoord, width, height), color);
    }

    /**
     * renders the the rectangle on a {@link Graphics2D} object with a given offset
     * @param g2d
     * @param xOffset
     * @param yOffset
     */
    @Override
    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        Vector2D sprV2d = new Vector2D(super.getBody().getCenter());
        g2d.translate(sprV2d.getX(), sprV2d.getY());
        if (animation != null) {
            Sprite spr = animation.getCurrent();
            texture = new TexturePaint((BufferedImage)
                    spr.getImage(),
                    new Rectangle2D.Double(0, 
                    0, spr.getWidth(), 
                    spr.getHeight()));
            g2d.setPaint(texture);
            g2d.fillRect(- (int)this.getBody().getWidth() / 2,
                    -(int)this.getBody().getHeight() / 2,
                    (int)this.getBody().getWidth(),(int)this.getBody().getHeight());
        } else {
            g2d.setColor(super.getColor());
            g2d.fillRect(- (int)this.getBody().getWidth() / 2,
                    -(int)this.getBody().getHeight() / 2,
                    (int)this.getBody().getWidth(),(int)this.getBody().getHeight());
        }
        g2d.translate(-sprV2d.getX(), -sprV2d.getY());
    }

    /**
     * returns the graphical rectangle on a {@link Graphics2D} object with a given offset and angle of rotation
     * @param g2d
     * @param xOffset
     * @param yOffset
     * @param angle
     */
    public void render(Graphics2D g2d, int xOffset, int yOffset, double angle) {
        Vector2D sprV2d = new Vector2D(super.getBody().getCenter());
        g2d.translate(sprV2d.getX(), sprV2d.getY());
        g2d.rotate(angle);
        if (sprite != null) {
            g2d.drawImage(sprite.getImage(), -sprite.getWidth() / 2,
                -sprite.getHeight() / 2, null);
        } else {
            g2d.setColor(super.getColor());
            g2d.fillRect(- (int)this.getBody().getWidth() / 2,
                    -(int)this.getBody().getHeight() / 2,
                    (int)this.getBody().getWidth(),(int)this.getBody().getHeight());
        }
        
        /*
        g2d.setColor(Color.yellow);
        g2d.drawRect(-sprite.getWidth() / 2,
                -sprite.getHeight() / 2,
                sprite.getWidth(), sprite.getHeight());
                */
        g2d.rotate(-angle);
        g2d.translate(-sprV2d.getX(), -sprV2d.getY());

    }

    /**
     * returns a body of the graphical object
     * @return
     */
    @Override
    public Rectangle getBody() {
        return (Rectangle) super.getBody(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    
    
    
}

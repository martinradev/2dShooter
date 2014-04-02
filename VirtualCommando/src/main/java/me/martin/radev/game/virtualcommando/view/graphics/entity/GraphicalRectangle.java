/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import me.martin.radev.game.virtualcommando.geometry.entity.Rectangle;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class GraphicalRectangle extends GraphicalObject {

    private Sprite sprite;

    /**
     *
     * @param bottomLeft
     * @param width
     * @param height
     * @param color
     */
    public GraphicalRectangle(Vector2D bottomLeft, double width, double height, Color color) {
        super(new Rectangle(bottomLeft, width, height), color);
    }

    /**
     *
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
     *
     * @param g2d
     * @param xOffset
     * @param yOffset
     */
    @Override
    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        Vector2D sprV2d = new Vector2D(super.getBody().getCenter());
        g2d.translate(sprV2d.getX()+xOffset, sprV2d.getY());
        if (sprite != null) {
            g2d.drawImage(sprite.getImage(), -sprite.getWidth() / 2,
                    -sprite.getHeight() / 2, null);
        } else {
            g2d.setColor(super.getColor());
            g2d.fillRect(- (int)this.getBody().getWidth() / 2,
                    -(int)this.getBody().getHeight() / 2,
                    (int)this.getBody().getWidth(),(int)this.getBody().getHeight());
        }
        g2d.translate(-sprV2d.getX(), -sprV2d.getY());
    }

    /**
     *
     * @param g2d
     * @param xOffset
     * @param yOffset
     * @param angle
     */
    public void render(Graphics2D g2d, int xOffset, int yOffset, double angle) {
        Vector2D sprV2d = new Vector2D(super.getBody().getCenter());
        g2d.translate(sprV2d.getX(), sprV2d.getY());
        g2d.rotate(angle);
        g2d.drawImage(sprite.getImage(), -sprite.getWidth() / 2,
                -sprite.getHeight() / 2, null);
        g2d.setColor(Color.yellow);
        g2d.drawRect(-sprite.getWidth() / 2,
                -sprite.getHeight() / 2,
                sprite.getWidth(), sprite.getHeight());
        g2d.rotate(-angle);
        g2d.translate(-sprV2d.getX(), -sprV2d.getY());

    }

    /**
     *
     * @param sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     *
     * @return
     */
    @Override
    public Rectangle getBody() {
        return (Rectangle) super.getBody(); //To change body of generated methods, choose Tools | Templates.
    }
}

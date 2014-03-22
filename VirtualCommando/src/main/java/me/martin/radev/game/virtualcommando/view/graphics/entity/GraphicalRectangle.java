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

    public GraphicalRectangle(Vector2D bottomLeft, double width, double height, Color color) {
        super(new Rectangle(bottomLeft, width, height), color);
    }

    public GraphicalRectangle(double xCoord, double yCoord, double width, double height, Color color) {
        super(new Rectangle(xCoord, yCoord, width, height), color);
    }

    @Override
    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        Vector2D sprV2d = new Vector2D(super.getBody().getCenter());
        g2d.translate(sprV2d.getX(), sprV2d.getY());
        g2d.drawImage(sprite.getImage(), -sprite.getWidth() / 2,
                -sprite.getHeight() / 2, null);
        g2d.setColor(Color.yellow);
        g2d.drawRect(-sprite.getWidth() / 2,
                -sprite.getHeight() / 2,
                sprite.getWidth(), sprite.getHeight());
        g2d.translate(-sprV2d.getX(), -sprV2d.getY());
    }

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

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public Rectangle getBody() {
        return (Rectangle) super.getBody(); //To change body of generated methods, choose Tools | Templates.
    }
}

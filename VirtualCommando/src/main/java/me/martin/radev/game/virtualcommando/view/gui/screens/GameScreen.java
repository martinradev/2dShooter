/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Point2D;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.map.MapInterface;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;

/**
 *
 * @author Marto
 */
public class GameScreen extends Screen {

    private MapInterface map;
    private int offsetX;
    private int offsetY;

    public GameScreen(MapInterface map, int width, int height) {
        super();
        this.setSize(width, height);
        this.map = map;
        this.offsetX = 0;
        this.offsetY = 0;
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        map.render(g2d, this.getOffsetX(), this.getOffsetY());
        for (Player p : Global.getGame().getPlayers()) {
            p.processMovement();
            ((GraphicalRectangle)p.getgObject()).render(g2d, 0, 0, p.getAngleOffset());
            g2d.setColor(Color.yellow);
            g2d.fillOval((int) p.getgObject().getBody().getCenter().getX(),
                    (int) p.getgObject().getBody().getCenter().getY(),
                    1, 1);

        }
    }
}

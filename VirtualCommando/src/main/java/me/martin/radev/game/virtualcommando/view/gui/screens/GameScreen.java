/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import me.martin.radev.game.virtualcommando.map.MapInterface;

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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    RenderingHints.VALUE_ANTIALIAS_ON);
        map.render(g2d, this.getOffsetX(), this.getOffsetY());
    }
    
}

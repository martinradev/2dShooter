/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
        map.render((Graphics2D)g, this.getOffsetX(), this.getOffsetY());
    }
    
}

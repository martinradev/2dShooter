/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map;

import java.awt.Graphics2D;
import java.util.List;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public abstract class TiledMap {
    
    /**
     *
     */
    protected double width;
    /**
     *
     */
    protected double height;
    
    /**
     *
     */
    public TiledMap() {
        
    }
    
    /**
     *
     * @param g2d
     * @param xOffset
     * @param yOffset
     */
    public abstract void render(Graphics2D g2d, int xOffset, int yOffset);
    /**
     *
     * @return
     */
    public abstract List<GraphicalObject> getObjects();
    /**
     *
     * @return
     */
    public abstract List<GraphicalObject> getRespawnPoints();
    
    /**
     *
     * @return
     */
    public double getWidth() {
        return width;
    }
    
    /**
     *
     * @return
     */
    public double getHeight() {
        return height;
    }

    
    
}

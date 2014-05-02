/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map;

import java.awt.Graphics2D;
import java.util.List;
import me.martin.radev.game.virtualcommando.structures.Graph;
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
     * renders the on a graphics2d object with a given x offset and y offset.
     * @param g2d
     * @param xOffset
     * @param yOffset
     */
    public abstract void render(Graphics2D g2d, int xOffset, int yOffset);
    /**
     * returns the static objects
     * @return
     */
    public abstract List<GraphicalObject> getObjects();
    /**
     * returns the respawn points for the map
     * @return
     */
    public abstract List<GraphicalObject> getRespawnPoints();
    
    /**
     *
     * @return
     */
    public abstract List<GraphicalObject> getDecorationObjects();
    
    /**
     *
     * @return
     */
    public abstract Graph getWaypointsGraph();
    
    /**
     * returns the width of the map
     * @return
     */
    public double getWidth() {
        return width;
    }
    
    /**
     * returns the height of the map
     * @return
     */
    public double getHeight() {
        return height;
    }

    
    
}

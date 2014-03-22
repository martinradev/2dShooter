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
public interface MapInterface {
    
    public void render(Graphics2D g2d, int xOffset, int yOffset);
    public List<GraphicalObject> getObjects();
    
}

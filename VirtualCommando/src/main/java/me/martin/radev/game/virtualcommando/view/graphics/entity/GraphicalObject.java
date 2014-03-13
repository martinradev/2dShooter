/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Graphics2D;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;

/**
 *
 * @author Marto
 */
public interface GraphicalObject {
    
    public GeometricObject getBody();
    public void render(Graphics2D g2d, int xOffset, int yOffset);
    
}

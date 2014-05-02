/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.graphics;

import java.awt.Graphics2D;

/**
 *
 * @author Marto
 */
public interface Renderable {

    /**
     * renders on graphics
     * @param g2d
     * @param xOffset
     * @param yOffset
     */
    void render(Graphics2D g2d, int xOffset, int yOffset);
}

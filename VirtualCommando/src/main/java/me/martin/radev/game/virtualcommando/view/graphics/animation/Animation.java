/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.animation;

import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;

/**
 * An Animation interface. The animation consists of a series of sprites run in a 
 * specific order.
 * @author Marto
 */
public interface Animation {
    
    /**
     * updates the animation by doing a specific action
     */
    public void update();
    /**
     * gets the next sprite for the animation
     */
    public void next();
    /**
     * returns the current sprite
     * @return
     */
    public Sprite getCurrent();
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.animation;

import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;

/**
 *
 * @author Marto
 */
public interface Animation {
    
    /**
     *
     */
    public void update();
    /**
     *
     */
    public void next();
    /**
     *
     * @return
     */
    public Sprite getCurrent();
    
}

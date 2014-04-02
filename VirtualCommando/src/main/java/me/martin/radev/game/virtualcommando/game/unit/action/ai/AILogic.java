/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit.action.ai;

import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public interface AILogic {
    
    /**
     *
     * @return
     */
    public Vector2D getDirection();
    /**
     *
     * @return
     */
    public float getRotationAngle();
    /**
     *
     * @return
     */
    public boolean shouldShoot();
    /**
     *
     * @return
     */
    public Vector2D directionOfShooting();
    
}

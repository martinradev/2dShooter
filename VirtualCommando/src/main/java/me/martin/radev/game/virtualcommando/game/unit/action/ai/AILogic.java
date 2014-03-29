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
    
    public Vector2D getDirection();
    public float getRotationAngle();
    public boolean shouldShoot();
    public Vector2D directionOfShooting();
    
}

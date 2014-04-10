/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit.action.ai;

import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public interface AILogic {
    
    /**
     * returns a direction of movement
     * @return
     */
    public Vector2D getDirection();
    /**
     * returns an angle of rotation. The vector is a unit vector.
     * @return
     */
    public float getRotationAngle();
    /**
     * returns true if the player should shoot
     * @return
     */
    public boolean shouldShoot();
    /**
     * return the direction of shooting. The vector is a unit vector.
     * @param player 
     * @return
     */
    public Vector2D directionOfShooting(Player player);
    
}

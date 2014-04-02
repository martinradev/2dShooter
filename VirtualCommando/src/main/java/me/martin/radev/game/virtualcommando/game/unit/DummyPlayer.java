/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class DummyPlayer extends Player {

    /**
     * Creates a dummy player. The dummy player doesn't do anything on it's own.
     * It receives commands from another object like a {@link GameServerSynchronizer}
     * @param name
     * @param posX
     * @param posY
     * @param currentHealth
     * @param maxHealth
     * @param curAmmo
     * @param maxAmmo
     * @param respawnTime
     */
    public DummyPlayer(String name, double posX, double posY,
            int currentHealth, int maxHealth, int curAmmo, int maxAmmo,
            double respawnTime) {
        super(name, PlayerType.NormalPlayer.getMaxHealth(),
                new Vector2D(posX, posY), PlayerType.NormalPlayer.getWidth(),
                PlayerType.NormalPlayer.getHeight(),
                new Color(1f, 0f, 0f, .0f));
        super.setRespawnTime(respawnTime);
        super.setCurrentHealth(currentHealth);
        super.setMaxHealth(maxHealth);
        super.getWeapon().setTotalAmmu(maxAmmo);
        super.getWeapon().setCurrentAmmuCount(curAmmo);
    }
    
    /**
     *
     */
    @Override
    public void processMovement() {
        
    }

    /**
     *
     */
    @Override
    public void processRotation() {
        
    }

    /**
     *
     */
    @Override
    public void processShooting() {
        
    }
    
}

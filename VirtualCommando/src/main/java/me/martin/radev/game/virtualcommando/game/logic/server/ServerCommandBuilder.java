/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server;

import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class ServerCommandBuilder {
    
    /**
     *
     */
    public ServerCommandBuilder() {
        
    }
    
    // add|del player|object [optional] coordX coordY curHealth maxHealth curBullets maxBullets respawnTime
    /**
     *
     * @param p
     * @return
     */
    public String getAddPlayerCommand(Player p) {
        String command;
        command = "add player " + p.getName() 
                + " " + p.getBody().getCenter().getX() + " "
                + p.getBody().getCenter().getY() + " "
                + p.getCurrentHealth() + " " + p.getMaxHealth()
                + " " + p.getWeapon().getCurrentAmmuCount() + " " + p.getWeapon().getTotalAmmu() + " "
                + p.getRespawnTime();
        return command;
    }
    
    /**
     *
     * @param mapName
     * @return
     */
    public String getMapCommand(String mapName) {
        String command;
        command = "map " + mapName;
        return command;
    }
    
    /**
     *
     * @param p
     * @return
     */
    public String getPlayerStatusCommand(Player p) {
        String command;
        command = "update player " + p.getName() 
                + " " + p.getBody().getCenter().getX() + " "
                + p.getBody().getCenter().getY() + " "
                + p.getCurrentHealth() + " " + p.getMaxHealth()
                + " " + p.getWeapon().getCurrentAmmuCount() + " " + p.getWeapon().getTotalAmmu() + " "
                + p.getRespawnTime();
        return command;
    }
    
    /**
     *
     * @param p
     * @param direction
     * @return
     */
    public String getPlayerMovementCommand(Player p, Vector2D direction) {
        String command;
        command = "move player " + p.getName() + " " + direction.getX() + " " + direction.getY();
        return command;
    }
    
    /**
     *
     * @param p
     * @param direction
     * @return
     */
    public String getPlayerShootingCommand(Player p, Vector2D direction) {
        String command;
        command = "shoot player " + p.getName() + " " + direction.getX() + " " + direction.getY();
        return command;
    }
    
    /**
     *
     * @param p
     * @param angle
     * @return
     */
    public String getPlayerRotationCommand(Player p, double angle) {
        String command;
        command = "rotate player " + p.getName() + " " + angle;
        return command;
    }
    
}

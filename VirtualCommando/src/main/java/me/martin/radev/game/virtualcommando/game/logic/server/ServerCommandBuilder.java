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
     * The class is responsible for building server commands like adding a player,
     * deleting a player, etc
     */
    public ServerCommandBuilder() {
        
    }
    
    // add|del player|object [optional] coordX coordY curHealth maxHealth curBullets maxBullets respawnTime
    /**
     * return an add player command for a {@link Player} object.
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
    
    public String getRespawnPlayerCommand(Player p) {
        String command;
        command = "respawn player " + p.getName() 
                + " " + p.getBody().getCenter().getX() + " "
                + p.getBody().getCenter().getY() + " "
                + p.getCurrentHealth() + " " + p.getMaxHealth()
                + " " + p.getWeapon().getCurrentAmmuCount() + " " + p.getWeapon().getTotalAmmu() + " "
                + p.getRespawnTime();
        return command;
    }
    
    /**
     * returns a map command for a given map name. This command should be handled so that
     * it loads the map.
     * @param mapName
     * @return
     */
    public String getMapCommand(String mapName) {
        String command;
        command = "map " + mapName;
        return command;
    }
    
    /**
     * returns a command with information about the player like 
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
     * returns a server command for the movement of a player p in a given direction
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
     * returns a command for a shooting of player with a given direction
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
     * returns a command for the rotation of a player with a given angle
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

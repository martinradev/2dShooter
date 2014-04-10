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
    
    private String removePrecision(double d) {
        return String.format("%.2f", d);
    }
    
    // add|del player|object [optional] coordX coordY curHealth maxHealth curBullets maxBullets respawnTime
    /**
     * return an add player command for a {@link Player} object.
     * @param p
     * @return
     */
    public String getAddPlayerCommand(Player p) {
        String command;
        command = "ap " + p.getName() 
                + " " + (int)p.getBody().getCenter().getX() + " "
                + (int)p.getBody().getCenter().getY() + " "
                + p.getCurrentHealth() + " " + p.getMaxHealth()
                + " " + p.getWeapon().getCurrentAmmuCount() + " " + p.getWeapon().getTotalAmmu() + " "
                + removePrecision(p.getRespawnTime());
        return command;
    }
    
    public String getRespawnPlayerCommand(Player p) {
        String command;
        command = "gp " + p.getName() 
                + " " + (int)p.getBody().getCenter().getX() + " "
                + (int)p.getBody().getCenter().getY() + " "
                + p.getCurrentHealth() + " " + p.getMaxHealth()
                + " " + p.getWeapon().getCurrentAmmuCount() + " " + p.getWeapon().getTotalAmmu() + " "
                + removePrecision(p.getRespawnTime());
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
        command = "m " + mapName;
        return command;
    }
    
    /**
     * returns a command with information about the player like 
     * @param p
     * @return
     */
    public String getPlayerStatusCommand(Player p) {
        String command;
        command = "up " + p.getName() 
                + " " + (int)p.getBody().getCenter().getX() + " "
                + (int)p.getBody().getCenter().getY() + " "
                + p.getCurrentHealth() + " " + p.getMaxHealth()
                + " " + p.getWeapon().getCurrentAmmuCount() + " " + p.getWeapon().getTotalAmmu() + " "
                + removePrecision(p.getRespawnTime());
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
        command = "mp " + p.getName() + " " + removePrecision(direction.getX()) 
                + " " + removePrecision(direction.getY());
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
        command = "sp " + p.getName() + " " + removePrecision(direction.getX())
                + " " + removePrecision(direction.getY());
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
        command = "rp " + p.getName() + " " + removePrecision(angle);
        return command;
    }
    
}

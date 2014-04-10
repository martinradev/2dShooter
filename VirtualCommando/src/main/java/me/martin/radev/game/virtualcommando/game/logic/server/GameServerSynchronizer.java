/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server;

import java.util.List;
import me.martin.radev.game.virtualcommando.game.logic.server.protocols.ServerProtocol;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.unit.ServerPlayer;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class GameServerSynchronizer {
    
    private List<ServerPlayer> players;
    private ServerCommandBuilder commandBuilder;
    
    /**
     * Creates a game synchronizer. The game synchronizer is responsible 
     * for synchronizing the concurrency between players in the server
     * @param players
     * @param commandBuilder
     */
    public GameServerSynchronizer(List<ServerPlayer> players,
            ServerCommandBuilder commandBuilder) {
        this.players = players;
        this.commandBuilder = commandBuilder;
    }
    
    /**
     * updates the location of a player on the screen for every other server player
     * @param player
     */
    public void updatePlayer(Player player) {
        for (ServerPlayer pl : players) {
            if (pl != player) {
                ServerProtocol protocol = pl.getProtocol();
                String command = commandBuilder.getPlayerStatusCommand(player);
                protocol.sendCommand(command);
            }
        }
    }
    
    /**
     * notifies that a player has shot in a direction.
     * @param player
     * @param direction
     */
    public void shootPlayer(Player player, Vector2D direction) {
        for (ServerPlayer pl : players) {
            if (pl != player) {
                ServerProtocol protocol = pl.getProtocol();
                String command = 
                        commandBuilder.getPlayerShootingCommand(player, direction);
                protocol.sendCommand(command);
            }
        }
    }
    
    /**
     * notifies for rotation of a player. Every player will be called accordingly.
     * @param player
     * @param angle
     */
    public void rotatePlayer(Player player, double angle) {
        for (ServerPlayer pl : players) {
            if (pl != player) {
                ServerProtocol protocol = pl.getProtocol();
                String command = 
                        commandBuilder.getPlayerRotationCommand(player, angle);
                protocol.sendCommand(command);
            }
        }
    }
    
    /**
     * sends the appropriate the commands about the respawn of player
     * @param player
     */
    public void respawnPlayer(Player player) {
        for (ServerPlayer pl : players) {
            
                ServerProtocol protocol = pl.getProtocol();
                String command = 
                        commandBuilder.getRespawnPlayerCommand(player);
                protocol.sendCommand(command);

        }
    }
    
}

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
public class GameServerSynchronyzer {
    
    private List<ServerPlayer> players;
    private ServerCommandBuilder commandBuilder;
    
    public GameServerSynchronyzer(List<ServerPlayer> players,
            ServerCommandBuilder commandBuilder) {
        this.players = players;
        this.commandBuilder = commandBuilder;
    }
    
    public void updatePlayer(Player player) {
        System.out.println(players.size());
        for (ServerPlayer pl : players) {
            if (pl != player) {
                ServerProtocol protocol = pl.getProtocol();
                String command = commandBuilder.getPlayerStatusCommand(player);
                protocol.sendCommand(command);
            }
        }
    }
    
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
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server;

import me.martin.radev.game.virtualcommando.game.logic.server.protocols.ServerPlayerProtocol;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.unit.ServerPlayer;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class GameServer implements Runnable {

    private String port;
    private String password;
    private ServerSocket server;
    private ServerCommandBuilder commandBuilder;
    private GameServerSynchronizer serverSync;
    private List<ServerPlayer> serverPlayers;

    /**
     * Creates a GameServer. The GameServer is responsible for handling part of the logic
     * for the multi player server.
     * @param port
     * @param password
     */
    public GameServer(String port, String password) {
        this.port = port;
        this.password = password;
        this.commandBuilder = new ServerCommandBuilder();
        this.serverPlayers = new LinkedList<>();
        this.serverSync = new GameServerSynchronizer(serverPlayers, commandBuilder);
        
        try {
            server = new ServerSocket(Integer.parseInt(port));
        } catch (IOException ex) {
            Global.getExceptionHandler().notificate(
                    ExceptionHelper.IOException.getTitle(),
                    ExceptionHelper.IOException.getMessage());
        }
    }

    /**
     *
     */
    @Override
    public void run() {
        if (server == null) {
            return;
        }
        while (true) {
            try {
                Socket socket = server.accept();
                processConnection(socket);
            } catch (IOException ex) {
                Global.getExceptionHandler().notificate(
                        ExceptionHelper.IOException.getTitle(),
                        ExceptionHelper.IOException.getMessage());
            }
        }
    }

    private void processConnection(Socket sock) {
        
        // TODO
        ServerPlayerProtocol spp = new ServerPlayerProtocol(sock);
        new Thread(spp).start();
        synchronizeGame(spp);
    }
    
    private void synchronizeGame(ServerPlayerProtocol spp) {
        // send map info
        spp.sendCommand(commandBuilder.getMapCommand(
                Global.getGame().getMapName()));

        for (Entry<String, Player> entry : Global.getGame().getPlayers().entrySet()) {
            spp.sendCommand(commandBuilder.getAddPlayerCommand(entry.getValue()));
        }
    }
    
    /**
     * Notifies for movement of player. The command will be sent to every other
     * player connected to the server
     * @param p
     * @param direction
     */
    public void notifyForMovement(Player p, Vector2D direction) {
        for (ServerPlayer sp : serverPlayers) {
            sp.getProtocol().sendCommand(
                    commandBuilder.getPlayerMovementCommand(p, direction));
        }
    }
    
    /**
     * Notifies if a player has fired. The command will be sent to every other player.
     * @param p
     * @param direction
     */
    public void notifyForShooting(Player p, Vector2D direction) {
        for (ServerPlayer sp : serverPlayers) {
            sp.getProtocol().sendCommand(
                    commandBuilder.getPlayerShootingCommand(p, direction));
        }
    }
    
    /**
     * Notifies for rotation of a player. All other players in the server are called.
     * @param p
     * @param angle
     */
    public void notifyForRotation(Player p, double angle) {
        for (ServerPlayer sp : serverPlayers) {
            sp.getProtocol().sendCommand(
                    commandBuilder.getPlayerRotationCommand(p, angle));
        }
    }

    /**
     * returns a {@link List} of all the players in the server
     * @return
     */
    public List<ServerPlayer> getServerPlayers() {
        return serverPlayers;
    }

    /**
     * returns a {@link GameServerSynchronizer}
     * @return
     */
    public GameServerSynchronizer getServerSync() {
        return serverSync;
    }

    public ServerCommandBuilder getCommandBuilder() {
        return commandBuilder;
    }
    
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server.protocols;

import java.net.Socket;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.logic.respawn.DummyRespawner;
import me.martin.radev.game.virtualcommando.game.logic.server.ServerCommandBuilder;
import me.martin.radev.game.virtualcommando.game.unit.DummyPlayer;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class GameConcurrencyProtocol extends ServerProtocol {

    private ServerCommandBuilder commandBuilder;

    /**
     * Creates a game concurrency protocol. This protocol is used for the client
     * who is connected on the server. It accordingly receives information about the states 
     * of the other players of the server and sends information about the state of the main player.
     * @param connection
     * @param commandBuilder
     */
    public GameConcurrencyProtocol(Socket connection,
            ServerCommandBuilder commandBuilder) {
        super(connection);
        this.commandBuilder = commandBuilder;
    }

    /**
     * processes a command from the server
     * @param command
     */
    @Override
    protected void processCommand(String command) {
        String[] tokens = command.split(" ");
        if (tokens[0].charAt(0) == 'a') {
            if (tokens[0].charAt(1) == 'p') {
                processAddPlayer(tokens);
            }
        } else if (tokens[0].charAt(0) == 'e') {
            if (tokens[0].charAt(1) == 'p') {
                
            }
        } else if (tokens[0].charAt(0) == 'g') {
            if (tokens[0].charAt(1) == 'p') {
                processRespawnPlayer(tokens);
            }
        } else if (tokens[0].charAt(0) == 'm') {
        } else if (tokens[0].charAt(0) == 'u') {
            if (tokens[0].charAt(1) == 'p') {
                processUpdatePlayer(tokens);
            }
        } else if (tokens[0].charAt(0) == 's') {
            if (tokens[0].charAt(1) == 'p') {
                processShootPlayer(tokens);
            }
        } else if (tokens[0].charAt(0) == 'r') {
            if (tokens[0].charAt(1) == 'p') {
                processRotatePlayer(tokens);
            }
        }
    }

    private void processAddPlayer(String[] tokens) {
        String playerName = tokens[1];
        Double xPosition = Double.parseDouble(tokens[2]);
        Double yPosition = Double.parseDouble(tokens[3]);
        int currentHealth = Integer.parseInt(tokens[4]);
        int maxHealth = Integer.parseInt(tokens[5]);
        int currentAmmo = Integer.parseInt(tokens[6]);
        int maxAmmo = Integer.parseInt(tokens[7]);
        double respawnTime = Double.parseDouble(tokens[8]);
        DummyPlayer player = new DummyPlayer(playerName, xPosition, yPosition,
                currentHealth, maxHealth, currentAmmo, maxAmmo, respawnTime);
        Global.getGame().addPlayer(player);
    }
    
    private void processRespawnPlayer(String [] tokens) {
        Player p = Global.getGame().getPlayers().get(tokens[1]);
        ((DummyRespawner)Global.getGame().getRespawner()).respawn(p);
            processUpdatePlayer(tokens);
        
    }
    
    private void processUpdatePlayer(String [] tokens) {
        Player p = Global.getGame().getPlayers().get(tokens[1]);
        if (p == null) {
            processAddPlayer(tokens);
            return;
        }
        Double xPosition = Double.parseDouble(tokens[2]);
        Double yPosition = Double.parseDouble(tokens[3]);
        int currentHealth = Integer.parseInt(tokens[4]);
        int maxHealth = Integer.parseInt(tokens[5]);
        int currentAmmo = Integer.parseInt(tokens[6]);
        int maxAmmo = Integer.parseInt(tokens[7]);
        double respawnTime = Double.parseDouble(tokens[8]);
        p.getBody().translate(-p.getBody().getCenter().getX(),
                -p.getBody().getCenter().getY());
        p.getBody().translate(xPosition, yPosition);
        p.setCurrentHealth(currentHealth);
        p.setMaxHealth(maxHealth);
        p.getWeapon().setCurrentAmmuCount(currentAmmo);
        p.getWeapon().setTotalAmmu(maxAmmo);
        p.setRespawnTime(respawnTime);
    }
    
   
    private void processShootPlayer(String [] tokens) {
        Player p = Global.getGame().getPlayers().get(tokens[1]);
        if (p == null) {
            return;
        }
        double directionX = Double.parseDouble(tokens[2]);
        double directionY = Double.parseDouble(tokens[3]);
        p.shoot(new Vector2D(directionX, directionY));
    }
    

    private void processRotatePlayer(String [] tokens) {
        Player p = Global.getGame().getPlayers().get(tokens[1]);
        if (p == null) {
            return;
        }
        double angle = Double.parseDouble(tokens[2]);
        p.rotate(angle);
    }

    /**
     * updates the player on the server. Accordingly it generates an
     * update command and sends information to the server
     * @param p
     */
    public void updatePlayer(Player p) {
        String command = commandBuilder.getPlayerStatusCommand(p);
        sendCommand(command);
    }

    /**
     * Generates a shoot player command and sends it to the server
     * @param p
     * @param direction
     */
    public void shootPlayer(Player p, Vector2D direction) {
        String command =
                commandBuilder.getPlayerShootingCommand(p, direction);
        sendCommand(command);
    }
    
    /**
     * Generates a rotate player command and sends it to the server
     * @param p
     * @param angle
     */
    public void rotatePlayer(Player p, double angle) {
        String command =
                commandBuilder.getPlayerRotationCommand(p, angle);
        sendCommand(command);
    }
    
    /**
     * sends a command about the respawn of the player
     * @param p
     */
    public void respawnPlayer(Player p) {
        String command =
                commandBuilder.getRespawnPlayerCommand(p);
        sendCommand(command);
    }
    
}

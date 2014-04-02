/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server.protocols;

import java.net.Socket;
import me.martin.radev.game.virtualcommando.Global;
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
        if (tokens[0].equals("add")) {
            if (tokens[1].equals("player")) {
                processAddPlayer(tokens);
            }
        } else if (tokens[0].equals("remove")) {
            if (tokens[1].equals("player")) {
                
            }
        } else if (tokens[0].equals("map")) {
        } else if (tokens[0].equals("update")) {
            if (tokens[1].equals("player")) {
                processUpdatePlayer(tokens);
            }
        } else if (tokens[0].equals("shoot")) {
            if (tokens[1].equals("player")) {
                processShootPlayer(tokens);
            }
        } else if (tokens[0].equals("rotate")) {
            if (tokens[1].equals("player")) {
                processRotatePlayer(tokens);
            }
        }
    }

    private void processAddPlayer(String[] tokens) {
        String playerName = tokens[2];
        Double xPosition = Double.parseDouble(tokens[3]);
        Double yPosition = Double.parseDouble(tokens[4]);
        int currentHealth = Integer.parseInt(tokens[5]);
        int maxHealth = Integer.parseInt(tokens[6]);
        int currentAmmo = Integer.parseInt(tokens[7]);
        int maxAmmo = Integer.parseInt(tokens[8]);
        double respawnTime = Double.parseDouble(tokens[9]);
        DummyPlayer player = new DummyPlayer(playerName, xPosition, yPosition,
                currentHealth, maxHealth, currentAmmo, maxAmmo, respawnTime);
        Global.getGame().addPlayer(player);
    }
    
    private void processUpdatePlayer(String [] tokens) {
        Player p = Global.getGame().getPlayers().get(tokens[2]);
        if (p == null) {
            processAddPlayer(tokens);
            return;
        }
        Double xPosition = Double.parseDouble(tokens[3]);
        Double yPosition = Double.parseDouble(tokens[4]);
        int currentHealth = Integer.parseInt(tokens[5]);
        int maxHealth = Integer.parseInt(tokens[6]);
        int currentAmmo = Integer.parseInt(tokens[7]);
        int maxAmmo = Integer.parseInt(tokens[8]);
        double respawnTime = Double.parseDouble(tokens[9]);
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
        Player p = Global.getGame().getPlayers().get(tokens[2]);
        if (p == null) {
            return;
        }
        double directionX = Double.parseDouble(tokens[3]);
        double directionY = Double.parseDouble(tokens[4]);
        p.shoot(new Vector2D(directionX, directionY));
    }
    

    private void processRotatePlayer(String [] tokens) {
        Player p = Global.getGame().getPlayers().get(tokens[2]);
        if (p == null) {
            return;
        }
        double angle = Double.parseDouble(tokens[3]);
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
}

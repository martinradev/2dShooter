/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server.protocols;

import java.net.Socket;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.logic.MultiPlayerGame;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.unit.ServerPlayer;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class ServerPlayerProtocol extends ServerProtocol {

    private Player player;
    private boolean connected;

    /**
     * Creates a server player protocol. This is protocol is connected to every
     * player in the game. This is the client protocol on the server side.
     *
     * @param connection
     */
    public ServerPlayerProtocol(Socket connection) {
        super(connection);
        connected = true;
    }

    /**
     * Processes a command.
     *
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
        //double respawnTime = Double.parseDouble(tokens[9]);
        double respawnTime = 0d;
        player = new ServerPlayer(playerName, new Vector2D(xPosition, yPosition), this);
        Global.getGame().addPlayer(player);
    }

    private void processUpdatePlayer(String[] tokens) {
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
        if (!(xPosition == 0d && yPosition == 0d)) {
            p.getBody().translate(-p.getBody().getCenter().getX(),
                    -p.getBody().getCenter().getY());
            p.getBody().translate(xPosition, yPosition);
        }

        p.setCurrentHealth(currentHealth);
        p.setMaxHealth(maxHealth);
        p.getWeapon().setCurrentAmmuCount(currentAmmo);
        p.getWeapon().setTotalAmmu(maxAmmo);
        p.setRespawnTime(respawnTime);
        ((MultiPlayerGame) Global.getGame()).getServer()
                .getServerSync().updatePlayer(p);
    }

    private void processShootPlayer(String[] tokens) {
        Player p = Global.getGame().getPlayers().get(tokens[1]);
        if (p == null) {
            return;
        }
        double directionX = Double.parseDouble(tokens[2]);
        double directionY = Double.parseDouble(tokens[3]);
        Vector2D direction = new Vector2D(directionX, directionY);
        p.shoot(direction);
        ((MultiPlayerGame) Global.getGame()).getServer()
                .getServerSync().shootPlayer(p, direction);
    }

    private void processRotatePlayer(String[] tokens) {
        Player p = Global.getGame().getPlayers().get(tokens[1]);
        if (p == null) {
            return;
        }
        double angle = Double.parseDouble(tokens[2]);
        p.rotate(angle);
        ((MultiPlayerGame) Global.getGame()).getServer()
                .getServerSync().rotatePlayer(p, angle);
    }

    /**
     * Disconnects a player
     */
    @Override
    public void disconnect() {
        super.disconnect();
        if (player != null) {
            Global.getGame().removePlayer(player);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server.protocols;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.logic.MultiPlayerGame;
import me.martin.radev.game.virtualcommando.game.unit.DummyPlayer;
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

    public ServerPlayerProtocol(Socket connection) {
        super(connection);
        connected = true;
    }

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
        player = new ServerPlayer(playerName, new Vector2D(xPosition, yPosition), this);
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
        ((MultiPlayerGame)Global.getGame()).getServer()
                .getServerSync().updatePlayer(p);
    }
    
    public void processShootPlayer(String [] tokens) {
        Player p = Global.getGame().getPlayers().get(tokens[2]);
        if (p == null) {
            return;
        }
        double directionX = Double.parseDouble(tokens[3]);
        double directionY = Double.parseDouble(tokens[4]);
        Vector2D direction = new Vector2D(directionX, directionY);
        p.shoot(direction);
        ((MultiPlayerGame)Global.getGame()).getServer()
                .getServerSync().shootPlayer(p, direction);
    }
    
    public void processRotatePlayer(String [] tokens) {
        Player p = Global.getGame().getPlayers().get(tokens[2]);
        if (p == null) {
            return;
        }
        double angle = Double.parseDouble(tokens[3]);
        p.rotate(angle);
        ((MultiPlayerGame)Global.getGame()).getServer()
                .getServerSync().rotatePlayer(p, angle);
    }

    @Override
    public void disconnect() {
        super.disconnect();
        if (player != null) Global.getGame().removePlayer(player);
    }
    
    
    
}

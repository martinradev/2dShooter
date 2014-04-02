/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import me.martin.radev.game.virtualcommando.game.logic.server.GameServer;
import me.martin.radev.game.virtualcommando.game.unit.DummyPlayer;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.unit.ServerPlayer;

/**
 *
 * @author Marto
 */
public class MultiPlayerGame extends Game {
    
    private GameServer server;
    
    /**
     *
     * @param port
     * @param password
     */
    public MultiPlayerGame(String port,
            String password) {
        super("Desert");

        mainPlayer = new MyPlayer();
        addPlayer(mainPlayer);
                
        new Thread(server = new GameServer(port, password)).start();
        
        startGame();
    }

    /**
     *
     * @return
     */
    public GameServer getServer() {
        return server;
    }

    /**
     *
     * @param p
     */
    @Override
    public void addPlayer(Player p) {
        super.addPlayer(p);
        if (p.getClass() == ServerPlayer.class) {
            System.out.println("added to server players");
            server.getServerPlayers().add((ServerPlayer)p);
        }
    }

    /**
     *
     * @param p
     */
    @Override
    public void removePlayer(Player p) {
        super.removePlayer(p);
        if (p.getClass() == ServerPlayer.class) {
            server.getServerPlayers().remove(p);
        }
    }
    
    
    
}

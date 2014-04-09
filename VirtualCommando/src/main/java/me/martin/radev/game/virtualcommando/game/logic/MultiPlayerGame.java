/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import me.martin.radev.game.virtualcommando.game.logic.respawn.RandomRespawner;
import me.martin.radev.game.virtualcommando.game.logic.server.GameServer;
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
     * Creates a multiplayer game based on a port and password. The port must be free
     * in order to start the game.
     * @param port
     * @param password
     */
    public MultiPlayerGame(String port,
            String password) {
        super("Desert", RandomRespawner.class);

        mainPlayer = new MyPlayer();
        addPlayer(mainPlayer);
                
        new Thread(server = new GameServer(port, password)).start();
        
        startGame();
    }

    /**
     * returns a game server instance
     * @return
     */
    public GameServer getServer() {
        return server;
    }

    /**
     * adds a {@link Player} to the game
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
     * removes a {@link Player} from the game
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

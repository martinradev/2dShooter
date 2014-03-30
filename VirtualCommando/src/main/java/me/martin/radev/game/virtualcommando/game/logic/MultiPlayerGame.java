/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import me.martin.radev.game.virtualcommando.game.logic.server.ServerConnectionListener;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;

/**
 *
 * @author Marto
 */
public class MultiPlayerGame extends Game {
    
    private ServerConnectionListener server;
    
    public MultiPlayerGame(String port,
            String password) {
        super("Desert");
        
        new Thread(server = new ServerConnectionListener(port, password)).start();
        
        startGame();
    }
    
}

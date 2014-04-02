/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import me.martin.radev.game.virtualcommando.game.unit.Bot;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;

/**
 *
 * @author Marto
 */
public class SinglePlayerGame extends Game {
    
    private int numberOfBots;
    
    /**
     * Creates a single player game. Accordingly the appropriate number of bots
     * are added to the game.
     * @param numberOfBots
     */
    public SinglePlayerGame(int numberOfBots) {
        super("Desert");
        this.numberOfBots = numberOfBots;
        
        mainPlayer = new MyPlayer();
        respawner.addPlayer(mainPlayer);
        players.put(mainPlayer.getName(), mainPlayer);
        
        for (int i = 0; i < numberOfBots; ++i) {
            Bot bot = new Bot("Bot " + i);
            respawner.addPlayer(bot);
        }

        startGame();
    }
    
}

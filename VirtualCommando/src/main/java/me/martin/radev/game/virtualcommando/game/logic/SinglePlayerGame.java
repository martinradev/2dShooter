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
    
    public SinglePlayerGame(int numberOfBots) {
        super("Desert");
        this.numberOfBots = numberOfBots;
        
        for (int i = 0; i < numberOfBots; ++i) {
            Bot bot = new Bot();
            respawner.addPlayer(bot);
            //this.addPlayer(bot);
        }
        
        
        startGame();
    }
    
}

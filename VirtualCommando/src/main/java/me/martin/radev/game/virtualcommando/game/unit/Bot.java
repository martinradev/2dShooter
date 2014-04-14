/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.action.ai.AILogic;
import me.martin.radev.game.virtualcommando.game.unit.action.ai.GoodAILogic;
import me.martin.radev.game.virtualcommando.game.unit.action.ai.RandomAILogic;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class Bot extends Player  {

    private AILogic ai;
    
    /**
     * Creates a bot with a given name. The bot is controlled by AI.
     * @param name
     */
    public Bot(String name) {
        super(name, PlayerType.EasyBot.getMaxHealth(),
                new Vector2D(150, 150), PlayerType.EasyBot.getWidth(),
                PlayerType.EasyBot.getHeight(),
                new Color(1f, 0f, 0f, .0f));
        //ai = new RandomAILogic();
        ai = new GoodAILogic(this, Global.getGame().getMap().getWaypointsGraph());
    }
    
    /**
     * processes the movement of the bot. The commands are returned from the AI logic.
     */
    @Override
    public void processMovement() {
        Vector2D direction = ai.getDirection();
        super.move(direction);
    }

    /**
     * processes the rotation of the bot. The commands are returned from the AI logic.
     */
    @Override
    public void processRotation() {
        super.rotate(ai.getRotationAngle());
    }

    /**
     * processes the shooting of the bot. The commands are returned from the AI logic.
     */
    @Override
    public void processShooting() {
        if (ai.shouldShoot()) {
            super.shoot(ai.directionOfShooting(this));
        }
    }
    
    
    
}

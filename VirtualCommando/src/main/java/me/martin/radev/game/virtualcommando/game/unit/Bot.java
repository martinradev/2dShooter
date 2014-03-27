/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.game.unit.action.ai.AILogic;
import me.martin.radev.game.virtualcommando.game.unit.action.ai.RandomAILogic;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class Bot extends Player  {

    private AILogic ai;
    
    public Bot() {
        super(PlayerType.EasyBot.getMaxHealth(),
                new Vector2D(150, 150), PlayerType.EasyBot.getWidth(),
                PlayerType.EasyBot.getHeight(),
                new Color(1f, 0f, 0f, .0f));
        ai = new RandomAILogic();
    }
    
    @Override
    public void processMovement() {
        Vector2D direction = ai.getDirection();
        super.move(direction);
    }

    @Override
    public void processRotation() {
        super.rotate(ai.getRotationAngle());
    }
    
}

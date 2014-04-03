/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit.action.ai;

import java.util.Random;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 * Random logic for the bots.
 * @author Marto
 */
public class RandomAILogic implements AILogic {

    private Random rand;
    private final int epsAccuracy = 100;
    
    /**
     *
     */
    public RandomAILogic() {
        rand = new Random();
    }
    
    /**
     * returns a random a direction for the player
     * @return
     */
    @Override
    public Vector2D getDirection() {
        int yDirection = rand.nextInt(2*epsAccuracy)- epsAccuracy;
        int xDirection = rand.nextInt(2*epsAccuracy) - epsAccuracy;
        return new Vector2D(xDirection, yDirection).getUnitVector();
    }

    /**
     * returns a random angl of rotation
     * @return
     */
    @Override
    public float getRotationAngle() {
        return (float) (2f*Math.PI*rand.nextFloat());
    }

    /**
     * returns a random decision whether the player should shoot
     * @return
     */
    @Override
    public boolean shouldShoot() {
        float num = rand.nextFloat();
        return num > 0.98;
    }

    /**
     * return a random unit vector for shooting
     * @return
     */
    @Override
    public Vector2D directionOfShooting() {
        int yDirection = rand.nextInt(2*epsAccuracy) - epsAccuracy;
        int xDirection = rand.nextInt(2*epsAccuracy) - epsAccuracy;
        if (MathUtil.relativelyEqual(yDirection, 0d) &&
                 MathUtil.relativelyEqual(xDirection, 0d)) {
            xDirection = 1;
            yDirection = 1;
            System.out.println("zero");
        }
        return new Vector2D(xDirection, yDirection).getUnitVector();
    }

}

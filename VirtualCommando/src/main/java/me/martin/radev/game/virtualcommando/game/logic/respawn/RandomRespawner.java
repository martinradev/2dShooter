/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.respawn;

import java.util.List;
import java.util.Random;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class RandomRespawner extends Respawner {
    
    private Random randomGenerator;
    
    public RandomRespawner(List<GraphicalObject> respawnPlaces) {
        super(respawnPlaces);
        randomGenerator = new Random();
    }

    @Override
    public Vector2D getPosition() {
        int index = randomGenerator.nextInt(super.respawnPlaces.size());
        return super.respawnPlaces.get(index).getBody().getCenter();
    }
    
}

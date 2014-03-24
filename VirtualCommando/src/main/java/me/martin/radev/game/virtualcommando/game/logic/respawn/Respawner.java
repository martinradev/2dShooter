/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.respawn;

import java.util.List;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public abstract class Respawner {
    
    protected List<GraphicalObject> respawnPlaces;
    
    public Respawner(List<GraphicalObject> respawnPlaces) {
        this.respawnPlaces = respawnPlaces;
    }
    
    public abstract Vector2D getPosition();
    
}

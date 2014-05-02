/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.respawn;

import java.util.ArrayList;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 * DummyRespawner is used for {@link ConnectedToServerGame}. It doesn't have any logic implemented.
 * @author Marto
 */
public class DummyRespawner extends Respawner {
    
    /**
     *
     * @param list
     */
    public DummyRespawner(ArrayList<GraphicalObject> list) {
        super(list);
    }

    @Override
    public Vector2D getPosition() {
        return new Vector2D(0,0);
    }

    @Override
    public void processRespawnQueue() {
        // do nothing
    }

    /**
     * respawns the player p
     * @param p
     */
    @Override
    public void respawn(Player p) {
        super.respawn(p);
    }
    
    
    
}

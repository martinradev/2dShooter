/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import java.util.List;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import me.martin.radev.game.virtualcommando.game.interaction.CollisionDetection;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.entity.Polygon;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class GameFlow {
    
    private GameEntityContainer gameEntities;
    
    public GameFlow(GameEntityContainer gameEntities) {
        this.gameEntities = gameEntities;
    }
    
    public void processGameFlow() {
        this.processObjectMovement();
    }
    
    public void processObjectMovement() {
        List<GraphicalObject> players = gameEntities.getPlayers();
        for (int i = 0; i < players.size(); ++i) {
            Player p = (Player)players.get(i);
            p.processMovement();
            p.processRotation();
        }
    }
    
    public boolean isPlayerColliding(Player p) {
        for (GraphicalObject go : gameEntities.getMapObjects()) {
            if (CollisionDetection.doCollide(go.getBody(), (Polygon)p.getBody())) {
                return true;
            }
        }
        return false;
    }

}

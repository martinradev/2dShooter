/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import java.util.List;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 * Decided whether one player can see another player
 * @author Marto
 */
public class VisibilityIdentifier {
    
    private List<GraphicalObject>  mapObjects;
    private final double VISION_RADIUS = 285d;
    
    /**
     *
     */
    public VisibilityIdentifier() {
        mapObjects = Global.getGame().getGameEntities().getMapObjects();
    }
    
    /**
     *
     * @param pointOfView
     * @param otherObject
     * @return
     */
    public boolean canSeeObject(GraphicalObject pointOfView, 
            GraphicalObject otherObject) {
        double distance = MathUtil.distance(pointOfView.getBody().getCenter(),
                otherObject.getBody().getCenter());
        return distance <= VISION_RADIUS;
    }

    /**
     *
     * @return
     */
    public double getVISION_RADIUS() {
        return VISION_RADIUS;
    }
    
    
    
}

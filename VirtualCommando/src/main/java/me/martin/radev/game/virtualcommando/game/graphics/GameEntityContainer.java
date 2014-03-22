/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.graphics;

import java.util.LinkedList;
import java.util.List;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class GameEntityContainer {
    
    private List<GraphicalObject> players;
    private List<GraphicalObject> mapObjects;
    private List<GraphicalObject> bullets;
    
    public GameEntityContainer() {
        players = new LinkedList<>();
        mapObjects = new LinkedList<>();
        bullets = new LinkedList<>();
    }
    
    public void addPlayer(GraphicalObject p) {
        players.add(p);
    }
    
    public void addAllPlayers(List<GraphicalObject> players) {
        this.players.addAll(players);
    }
    
    public void addMapObject(GraphicalObject go) {
        mapObjects.add(go);
    }
    
    public void addAllMapObjects(List<GraphicalObject> mapObjects) {
        this.mapObjects.addAll(mapObjects);
    }
    
    public void addBullet(GraphicalObject b) {
        bullets.add(b);
    }
    
    public void addAllBullets(List<GraphicalObject> bullets) {
        this.bullets.addAll(bullets);
    }

    public List<GraphicalObject> getBullets() {
        return bullets;
    }

    public List<GraphicalObject> getMapObjects() {
        return mapObjects;
    }

    public List<GraphicalObject> getPlayers() {
        return players;
    }
    
}

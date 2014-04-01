/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class SimpleObjectMap extends TiledMap{
    
    private List<GraphicalObject> staticObjects;
    private List<GraphicalObject> respawnPoints;
    
    public SimpleObjectMap(List<GraphicalObject> objectsList, 
            List<GraphicalObject> respawnPoints, double width, double height) {
        staticObjects = objectsList;
        this.respawnPoints = respawnPoints;
        this.width = width;
        this.height = height;
    }
    
    public SimpleObjectMap(double width, double height) {
        staticObjects = new ArrayList<>();
        respawnPoints = new ArrayList<>();
        this.width = width;
        this.height = height;
    }
    
    public List<GraphicalObject> getStaticObjects() {
        return staticObjects;
    }

    @Override
    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        for (GraphicalObject go : staticObjects) {
            go.render(g2d, xOffset, yOffset);
        }
    }
    
    public void addStaticObjects(GraphicalObject go) {
        staticObjects.add(go);
    }
    
    public void addStaticObjects(List<GraphicalObject> goList) {
        staticObjects.addAll(goList);
    }
    
    public void addRespawnPoint(GraphicalObject v2d) {
        respawnPoints.add(v2d);
    }
    
    public void addRespawnPoint(List<GraphicalObject> v2d) {
        respawnPoints.addAll(v2d);
    }

    @Override
    public List<GraphicalObject> getObjects() {
        return staticObjects;
    }

    @Override
    public List<GraphicalObject> getRespawnPoints() {
        return respawnPoints;
    }
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class SimpleObjectMap extends TiledMap{
    
    private List<GraphicalObject> staticObjects;
    private List<GraphicalObject> respawnPoints;
    
    /**
     * Creates a SimpleObjectMap from a list of static graphical objects,
     * list of respawn points, a given width and height
     * @param objectsList
     * @param respawnPoints
     * @param width
     * @param height
     */
    public SimpleObjectMap(List<GraphicalObject> objectsList, 
            List<GraphicalObject> respawnPoints, double width, double height) {
        staticObjects = objectsList;
        this.respawnPoints = respawnPoints;
        this.width = width;
        this.height = height;
    }
    
    /**
     * creates a map with a given width and height
     * @param width
     * @param height
     */
    public SimpleObjectMap(double width, double height) {
        staticObjects = new ArrayList<>();
        respawnPoints = new ArrayList<>();
        this.width = width;
        this.height = height;
    }
    
    /**
     * returns the static objects on the map
     * @return
     */
    public List<GraphicalObject> getStaticObjects() {
        return staticObjects;
    }

    /**
     * renders the map on a graphics2d object with a given x offset and 
     * y offset
     * @param g2d
     * @param xOffset
     * @param yOffset
     */
    @Override
    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        for (GraphicalObject go : staticObjects) {
            go.render(g2d, xOffset, yOffset);
        }
    }
    
    /**
     * adds a static object to the map
     * @param go
     */
    public void addStaticObjects(GraphicalObject go) {
        staticObjects.add(go);
    }
    
    /**
     * add static objects to the map
     * @param goList
     */
    public void addStaticObjects(List<GraphicalObject> goList) {
        staticObjects.addAll(goList);
    }
    
    /**
     * adds a respawn point to the map
     * @param v2d
     */
    public void addRespawnPoint(GraphicalObject v2d) {
        respawnPoints.add(v2d);
    }
    
    /**
     * adds repawn points to the map
     * @param v2d
     */
    public void addRespawnPoints(List<GraphicalObject> v2d) {
        respawnPoints.addAll(v2d);
    }

    /**
     * returns all static objects on the map
     * @return
     */
    @Override
    public List<GraphicalObject> getObjects() {
        return staticObjects;
    }

    /**
     * returns all respawn points on the map
     * @return
     */
    @Override
    public List<GraphicalObject> getRespawnPoints() {
        return respawnPoints;
    }
    
    
    
}

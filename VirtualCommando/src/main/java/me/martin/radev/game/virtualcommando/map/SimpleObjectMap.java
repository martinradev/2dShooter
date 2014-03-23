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
    
    public SimpleObjectMap(List<GraphicalObject> objectsList, double width, double height) {
        super();
        staticObjects = objectsList;
        this.width = width;
        this.height = height;
    }
    
    public SimpleObjectMap( double width, double height) {
        staticObjects = new ArrayList<GraphicalObject>();
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
    
    public void add(GraphicalObject go) {
        staticObjects.add(go);
    }

    @Override
    public List<GraphicalObject> getObjects() {
        return staticObjects;
    }
    
    
    
}

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
public class SimpleObjectMap implements Map{
    
    private List<GraphicalObject> staticObjects;
    
    public SimpleObjectMap(List<GraphicalObject> objectsList) {
        staticObjects = objectsList;
    }
    
    public SimpleObjectMap() {
        staticObjects = new ArrayList<GraphicalObject>();
    }
    
    public List<GraphicalObject> getStaticObjects() {
        return staticObjects;
    }

    public void render(Graphics2D g2d) {
        for (GraphicalObject go : staticObjects) {
            go.render(g2d);
        }
    }
    
    public void add(GraphicalObject go) {
        staticObjects.add(go);
    }
    
}

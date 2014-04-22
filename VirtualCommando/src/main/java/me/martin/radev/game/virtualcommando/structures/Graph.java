/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.structures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class Graph<Node> {
    
    private HashMap<Node, ArrayList<Node>> neighbourList;
    
    public Graph() {
        neighbourList = new HashMap<>();
    }
    
    public void addNode(Node node, ArrayList<Node> neighbours) {
        neighbourList.put(node, neighbours);
    }
    
    public ArrayList<Node> getNeighbours(Node node) {
        return neighbourList.get(node);
    }
    
    public Node getNodeCopy(Node go) {
        for (Node n : neighbourList.keySet()) {
            if (n == null) continue;
            if (n.equals(go)) return n;
        }
        return null;
    }
    
    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        g2d.setColor(Color.RED);
        for (Entry<Node, ArrayList<Node>> list : neighbourList.entrySet()) {
            GraphicalObject from = (GraphicalObject)list.getKey();
            for (Node nb : list.getValue()) {
                GraphicalObject to = (GraphicalObject)nb;
                g2d.drawLine((int)from.getBody().getCenter().getX(),
                        (int)from.getBody().getCenter().getY(), 
                        (int)to.getBody().getCenter().getX(),
                        (int)to.getBody().getCenter().getY());
            }
        }
    }
    
}

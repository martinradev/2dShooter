/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.structures;

import java.util.ArrayList;
import java.util.HashMap;

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
    
}

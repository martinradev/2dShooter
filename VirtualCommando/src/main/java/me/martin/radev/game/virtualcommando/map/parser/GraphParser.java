/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.martin.radev.game.virtualcommando.structures.Graph;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Marto
 */
public class GraphParser implements 
        Parser<Graph<GraphicalObject>> {

    private List<GraphicalObject> nodeList;
    
    /**
     *
     * @param nodes
     */
    public GraphParser(List<GraphicalObject> nodes) {
        nodeList = nodes;
    }
    
    private GraphicalObject getNodeByName(String name) {
        for (GraphicalObject go : nodeList) {
            if (go.getName().equals(name)) {
                return go;
            }
        }
        return null;
    }
    
    @Override
    public Map<String, String> parseProperties(NodeList properties) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Graph<GraphicalObject> parseObject(Element el, String mapFolder) {
        Graph<GraphicalObject> graph = new Graph<>();
        NodeList xmlNodes = el.getElementsByTagName("node");
        for (int i = 0; i < xmlNodes.getLength(); ++i) {
            Element nodeEl = (Element)xmlNodes.item(i);
            GraphicalObject nodeGo = getNodeByName(nodeEl.getAttribute("value"));
            ArrayList<GraphicalObject> neighbours = new ArrayList<>();
            NodeList xmlNeighbours = nodeEl.getElementsByTagName("neighbour");
            for (int j = 0; j < xmlNeighbours.getLength(); ++j) {
                Element nbEl = (Element)xmlNeighbours.item(j);
                GraphicalObject nbGo = getNodeByName(nbEl.getAttribute("value"));
                neighbours.add(nbGo);
            }
            graph.addNode(nodeGo, neighbours);
        }
        return graph;
    }
    
}

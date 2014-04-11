/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map.parser;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalEllipse;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalPolygon;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Marto
 */
public class ObjectParser implements Parser {

    private final String pointDelimiter = " ";
    private final String coordDelimiter = ",";

    /**
     * parses an object from an xml element
     *
     * @param el
     * @return
     */
    @Override
    public GraphicalObject parseObject(Element el, String mapFolder) {
        GraphicalObject gObject = null;
        Color color = null;
        double xCoord = Double.parseDouble(el.getAttribute("x"));
        double yCoord = Double.parseDouble(el.getAttribute("y"));
        String width = el.getAttribute("width");
        String height = el.getAttribute("height");
        NodeList propertiesContainer = el.getElementsByTagName("properties");
        NodeList properties = null;

        Sprite sprite = null;
        NodeList children = el.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node node = children.item(i);
            if (node.getNodeName().equals("backgroundTexture")) {
                String srcName = ((Element) node).getAttribute("src");
                sprite = (Sprite) Global.getAssetManager().load(AssetType.Sprite,
                        mapFolder + "sprites/" + srcName);
            }
        }

        Map<String, String> mapProperties = this.parseProperties(properties);
        if (mapProperties.containsKey("color")) {
            color = Color.decode(mapProperties.get("color"));
        } else {
            color = Color.BLACK;
        }

        Class objectType = this.getObjectType(el);

        if (width != null && height != null && !width.isEmpty() && !height.isEmpty()) {
            double widthDouble = Double.parseDouble(width);
            double heightDouble = Double.parseDouble(height);

            if (objectType == GraphicalEllipse.class) {
                gObject = new GraphicalEllipse(xCoord, yCoord, widthDouble, heightDouble, color);
            } else if (objectType == GraphicalRectangle.class) {
                gObject = new GraphicalRectangle(xCoord, yCoord, widthDouble, heightDouble, color);
            }
        } else {
            if (objectType == GraphicalPolygon.class) {
                List<Vector2D> points = this.getPolygonPoints(el, xCoord, yCoord);
                gObject = new GraphicalPolygon(points, color);
            } else if (objectType == Vector2D.class) {
                gObject = new GraphicalEllipse(xCoord, yCoord, 1d, 1d, Color.white);
            }

        }
        gObject.setSprite(sprite);
        return gObject;
    }

    /**
     * return the object type of an element. The element can be an ellipse, a
     * polyline, a vector
     *
     * @param el
     * @return
     */
    protected Class getObjectType(Element el) {
        if (el.getElementsByTagName("ellipse").getLength() != 0) {
            return GraphicalEllipse.class;
        }
        if (el.getElementsByTagName("polyline").getLength() != 0) {
            return GraphicalPolygon.class;
        }
        if (el.getElementsByTagName("vector2d").getLength() != 0) {
            return Vector2D.class;
        }
        return GraphicalRectangle.class;
    }

    private List<Vector2D> getPolygonPoints(Element el, double xStart, double yStart) {
        NodeList obj = el.getElementsByTagName("polyline");
        Element polyline = (Element) obj.item(0);
        String points = polyline.getAttribute("points");
        List<Vector2D> pointList = this.parsePointsString(points);
        for (Vector2D point : pointList) {
            point.translate(xStart, yStart);
        }
        return pointList;
    }

    private List<Vector2D> parsePointsString(String s) {
        List<Vector2D> pointList = new ArrayList<>();
        String[] points = s.split(pointDelimiter);
        for (String p : points) {
            String[] pointCoords = p.split(coordDelimiter);
            double x = Double.parseDouble(pointCoords[0]);
            double y = Double.parseDouble(pointCoords[1]);
            pointList.add(new Vector2D(x, y));
        }
        return pointList;
    }

    /**
     * parses the attributes for a given node in the dom tree
     *
     * @param properties
     * @return
     */
    @Override
    public Map<String, String> parseProperties(NodeList properties) {
        Map<String, String> propertiesMap = new HashMap<String, String>();
        if (properties == null) {
            return propertiesMap;
        }
        for (int i = 0; i < properties.getLength(); ++i) {
            Node node = properties.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elNode = (Element) node;
                propertiesMap.put(elNode.getAttribute("name"), elNode.getAttribute("value"));
            }
        }
        return propertiesMap;
    }

    /**
     * parses all objects in a node list
     *
     * @param nList
     * @return
     */
    public List<GraphicalObject> parseObjectContainer(NodeList nList,
            String mapFolder) {
        List<GraphicalObject> goList = new LinkedList<>();
        for (int i = 0; i < nList.getLength(); ++i) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                GraphicalObject go = this.parseObject((Element) node,
                        mapFolder);
                goList.add(go);
            }
        }
        return goList;
    }
}

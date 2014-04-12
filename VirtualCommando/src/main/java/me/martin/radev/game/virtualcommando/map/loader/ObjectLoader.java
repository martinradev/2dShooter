/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map.loader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import me.martin.radev.game.virtualcommando.exception.ExceptionHandler;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.map.TiledMap;
import me.martin.radev.game.virtualcommando.map.SimpleObjectMap;
import me.martin.radev.game.virtualcommando.map.parser.ObjectParser;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Marto
 */
public class ObjectLoader implements Loader {

    private ExceptionHandler exceptionHandler;
    private ObjectParser parser;

    /**
     * Creates an object loader. The object loader handles loading of the 
     * map and the objects in the map.
     * @param exceptionHandler
     */
    public ObjectLoader(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        this.parser = new ObjectParser();
    }

    /**
     * loads the tiled map from an xml file
     * @param xmlFile
     * @return
     */
    @Override
    public TiledMap load(File xmlFile) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            try {
                Document doc = dBuilder.parse(xmlFile);
                Element mapElement = (Element) doc.getElementsByTagName("map").item(0);

                String width = mapElement.getAttribute("width");
                String height = mapElement.getAttribute("height");
                String tileWidth = mapElement.getAttribute("tilewidth");
                String tileHeight = mapElement.getAttribute("tileheight");


                float widthInteger = Float.parseFloat(width);
                float heightInteger = Float.parseFloat(height);
                float tileWidthInteger = Float.parseFloat(tileWidth);
                float tileHeightInteger = Float.parseFloat(tileHeight);

                double totalWidth = widthInteger * tileWidthInteger;
                double totalHeight = heightInteger * tileHeightInteger;

                SimpleObjectMap som = 
                        new SimpleObjectMap(totalWidth, totalHeight);
                

                NodeList nList = this.getListInContainer("objectgroup", "object", doc.getDocumentElement());
                List<GraphicalObject> mapObjectList = parser.parseObjectContainer(nList, 
                        "maps/" + xmlFile.getParentFile().getName() + "/");
                som.addStaticObjects(mapObjectList);
                
                NodeList respawnList = this.getListInContainer("respawngroup", "object", doc.getDocumentElement());
                List<GraphicalObject> mapRespawnList = parser.parseObjectContainer(respawnList, 
                        "maps/" + xmlFile.getParentFile().getName() + "/");
                som.addRespawnPoints(mapRespawnList);
                
                
                NodeList decorationList = 
                        this.getListInContainer("decorationgroup", "object", doc.getDocumentElement());
                List<GraphicalObject> mapDecorationList = 
                        parser.parseObjectContainer(decorationList, 
                        "maps/" + xmlFile.getParentFile().getName() + "/");
                som.addDecorationObjects(mapDecorationList);
                return som;
            } catch (SAXException ex) {
                exceptionHandler.notificate(ExceptionHelper.SAXException.getTitle(),
                        ExceptionHelper.SAXException.getMessage());
            } catch (IOException ex) {
                exceptionHandler.notificate(ExceptionHelper.IOException.getTitle(),
                        ExceptionHelper.IOException.getMessage());
            }
        } catch (ParserConfigurationException ex) {
            exceptionHandler.notificate(ExceptionHelper.ParserConfigurationException.getTitle(),
                    ExceptionHelper.ParserConfigurationException.getMessage());
        }
        return null;
    }
    
    private NodeList getListInContainer(String containerName, String nodeName, Element el) {
        NodeList objectGroupList = el.getElementsByTagName(containerName);
        Element objectGroupElement = (Element)objectGroupList.item(0);
        return objectGroupElement.getElementsByTagName(nodeName);
    }
    
}

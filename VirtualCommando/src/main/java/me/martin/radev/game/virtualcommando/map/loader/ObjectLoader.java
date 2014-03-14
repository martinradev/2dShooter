/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map.loader;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import me.martin.radev.game.virtualcommando.exception.ExceptionHandler;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.map.MapInterface;
import me.martin.radev.game.virtualcommando.map.SimpleObjectMap;
import me.martin.radev.game.virtualcommando.map.parser.ObjectParser;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Marto
 */
public class ObjectLoader implements Loader {

    private ExceptionHandler exceptionHandler;
    private ObjectParser parser;

    public ObjectLoader(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        this.parser = new ObjectParser();
    }

    public MapInterface load(String file) {
        File xmlFile = null;
        try {

            xmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            try {
                Document doc = dBuilder.parse(xmlFile);
                Element mapElement = (Element) doc.getElementsByTagName("map").item(0);

                String width = mapElement.getAttribute("width");
                String height = mapElement.getAttribute("height");
                String tileWidth = mapElement.getAttribute("tilewidth");
                String tileHeight = mapElement.getAttribute("tileheight");


                int widthInteger = Integer.parseInt(width);
                int heightInteger = Integer.parseInt(height);
                int tileWidthInteger = Integer.parseInt(tileWidth);
                int tileHeightInteger = Integer.parseInt(tileHeight);

                double totalWidth = widthInteger * tileWidthInteger;
                double totalHeight = heightInteger * tileHeightInteger;

                SimpleObjectMap som = new SimpleObjectMap(totalWidth, totalHeight);

                NodeList nList = doc.getElementsByTagName("object");
                for (int i = 0; i < nList.getLength(); ++i) {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        GraphicalObject go = parser.parseObject((Element) node);
                        som.add(go);
                    }
                }
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
}

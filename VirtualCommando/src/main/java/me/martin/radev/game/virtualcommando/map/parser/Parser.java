/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map.parser;

import java.util.Map;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Marto
 */
public interface Parser {
    
    Map<String, String> parseProperties(NodeList properties);
    GraphicalObject parseObject(Element el);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map.loader;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import me.martin.radev.game.virtualcommando.exception.ExceptionHandler;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.map.Map;
import me.martin.radev.game.virtualcommando.map.SimpleObjectMap;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Marto
 */
public class ObjectLoader implements Loader {

    private ExceptionHandler exceptionHandler;
    
    public ObjectLoader(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }
    
    public Map load(String file) {
        try {
            SimpleObjectMap som = new SimpleObjectMap();
            File xmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            try {
                Document doc = dBuilder.parse(xmlFile);
            } catch (SAXException ex) {
                exceptionHandler.notificate(ExceptionHelper.SAXException.getTitle(), 
                    ExceptionHelper.SAXException.getMessage());
            } catch (IOException ex) {
                exceptionHandler.notificate(ExceptionHelper.IOException.getTitle(), 
                    ExceptionHelper.IOException.getMessage());
            }
            return som;
        } catch (ParserConfigurationException ex) {
            exceptionHandler.notificate(ExceptionHelper.ParserConfigurationException.getTitle(), 
                    ExceptionHelper.ParserConfigurationException.getMessage());
        }
        return null;
    }

}

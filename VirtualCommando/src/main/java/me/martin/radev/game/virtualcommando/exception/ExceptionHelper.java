/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.exception;

/**
 *
 * @author Marto
 */
public enum ExceptionHelper {
    
    ParserConfigurationException("Parsing error", "We were not able to parse the document."),
    SAXException("Parsing error", "The file is not xml format. Please check the format."),
    IOException("Cannot open file", "Please check file for rights.");
    
    private String title;
    private String message;
    
    private ExceptionHelper(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
    
    
    
}

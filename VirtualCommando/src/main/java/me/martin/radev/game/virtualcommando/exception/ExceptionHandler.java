/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.exception;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Marto
 */
public class ExceptionHandler {
    
    private JFrame frame;
    
    /**
     *
     * @param frame
     */
    public ExceptionHandler(JFrame frame) {
        this.frame = frame;
    }
    
    /**
     * creates a dialog with a title and message
     * @param title
     * @param message
     */
    public void notificate(String title, String message) {
        JOptionPane.showMessageDialog(frame, title, message, JOptionPane.ERROR_MESSAGE);
    }
    
}

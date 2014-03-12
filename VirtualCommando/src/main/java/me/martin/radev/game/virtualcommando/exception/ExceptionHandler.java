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
    
    public ExceptionHandler(JFrame frame) {
        this.frame = frame;
    }
    
    public void notificate(String title, String message) {
        JOptionPane.showMessageDialog(frame, title, message, JOptionPane.ERROR_MESSAGE);
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.entity.buttons;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author Marto
 */
public class ScreenButton extends JButton {
    
    public ScreenButton(String text, int width, int height, int fontSize) {
        super(text);
        this.setSize(new Dimension(width, height));
        this.setFont(new Font("serif", Font.PLAIN, fontSize));
    }
    
}

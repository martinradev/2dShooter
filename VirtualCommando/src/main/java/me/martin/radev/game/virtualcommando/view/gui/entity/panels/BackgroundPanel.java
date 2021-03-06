/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.entity.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;

/**
 *
 * @author Marto
 */
public class BackgroundPanel extends JPanel{
    
    private Sprite sprite;
    
    /**
     *
     * @param spr
     */
    public BackgroundPanel(Sprite spr) {
        this.sprite = spr;
        this.setOpaque(false);
    }
    

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xBegin = 0;
        if (this.getAlignmentX() == JComponent.CENTER_ALIGNMENT) {
            xBegin = (this.getWidth()-sprite.getWidth())/2;
        }
        g.drawImage(sprite.getImage(), xBegin, 0, null);
    }
    
    
    
}

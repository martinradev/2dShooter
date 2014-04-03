/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens.gamescreen;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Marto
 */
public class HealthBar extends JPanel {
    
    private double percent;
    private int borderWidth = 2;
    private final int startingWidth = 150;
    private final int startingHeight = 20;
    private final int leftOffset = 10;
    private final int bottomOffset = 10;
    
    /**
     *
     * @param parentWidth
     * @param parentHeight
     */
    public HealthBar(int parentWidth, int parentHeight) {
        super();
        setBorder(BorderFactory.createLineBorder(Color.RED, borderWidth));
        this.setBounds(leftOffset, 
                (parentHeight-bottomOffset)-startingHeight
                , startingWidth, startingHeight);
        this.setBackground(Color.white);
        percent = 1d;
    }

    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.green);
        int endX = (int)(percent*super.getWidth());
        g.fillRect(0, 0, endX, super.getHeight());
    }

    /**
     *
     * @param percent
     */
    public void setPercent(double percent) {
        this.percent = percent;
    }

    /**
     *
     * @return
     */
    public double getPercent() {
        return percent;
    }
    
    
    
}

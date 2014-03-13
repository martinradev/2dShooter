/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.map.MapInterface;

/**
 *
 * @author Marto
 */
public class TestGraphicsPanel extends JPanel {
    
    private MapInterface map;
    
    public TestGraphicsPanel(MapInterface map, int width, int height) {
        super();
        this.setSize(width, height);
        this.map = map;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.render((Graphics2D)g, -300, 0);
        /*
        ((Graphics2D)g).setColor(Color.BLACK);
        ((Graphics2D)g).fillRect(100, 100, 300, 300);
        */
    }
    
}

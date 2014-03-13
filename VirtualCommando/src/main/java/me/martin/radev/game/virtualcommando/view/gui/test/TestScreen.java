/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.map.MapInterface;

/**
 *
 * @author Marto
 */
public class TestScreen extends JFrame {
    
    
    private MapInterface map;
    
    public TestScreen(MapInterface map) {
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();  
        setSize(800, 600);
        setVisible(true);
        //map.render((Graphics2D)frame.getContentPane().getGraphics());
        
        TestGraphicsPanel tgp = new TestGraphicsPanel(map, getWidth(), getHeight());
        this.add(tgp);
    }

    
    
    
}

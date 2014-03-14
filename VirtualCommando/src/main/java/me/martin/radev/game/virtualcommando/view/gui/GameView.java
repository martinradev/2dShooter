/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.view.gui.screens.GameScreen;
import me.martin.radev.game.virtualcommando.view.gui.screens.Screen;

/**
 *
 * @author Marto
 */
public class GameView extends JFrame {
    
    private JPanel screen;
    
    public GameView(int width, int height) {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);
        screen = new JPanel();
        add(screen);
    }
    
    public void setGameScreen(Screen scr) {
        this.remove(screen);
        this.screen = scr;
        // TODO
        ((GameScreen)scr).setOffsetX(100);
        ((GameScreen)scr).setOffsetY(100);
        this.add(screen);
    }
    
}

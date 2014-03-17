/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHandler;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetManager;
import me.martin.radev.game.virtualcommando.view.gui.screens.MenuScreen;
import me.martin.radev.game.virtualcommando.view.gui.screens.Screen;

/**
 *
 * @author Marto
 */
public class GameView extends JFrame {
    
    private JPanel screen;
    private ExceptionHandler exceptionHandler;
    private AssetManager assetManager;
    
    public GameView(int width, int height) {
        super();
        initializeFrame(width, height);
    }
    
    private void initializeFrame(int width, int height) {
        exceptionHandler = new ExceptionHandler(this);
        assetManager = new AssetManager(exceptionHandler);
        Global.setExceptionHandler(exceptionHandler);
        Global.setAssetManager(assetManager);
        this.setScreen(new MenuScreen(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(new BorderLayout());
        setVisible(true);
        
    }
    public void setScreen(Screen scr) {
        if (screen != null) this.remove(screen);
        this.screen = scr;
        this.getContentPane().add(screen, BorderLayout.CENTER);
    }
    
    
    
}

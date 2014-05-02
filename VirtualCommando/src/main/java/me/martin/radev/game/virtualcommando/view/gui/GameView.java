/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHandler;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetManager;
import me.martin.radev.game.virtualcommando.view.gui.dialogs.UsernameDialog;
import me.martin.radev.game.virtualcommando.view.gui.screens.GameScreen;
import me.martin.radev.game.virtualcommando.view.gui.screens.MenuScreen;
import me.martin.radev.game.virtualcommando.view.gui.screens.Screen;

/**
 * The GameView class is the main gui of the game. It contains the current screen
 * which can be a {@link GameScreen}, {@link MenuScreen}, etc
 * @author Marto
 */
public class GameView extends JFrame {
    
    private JPanel screen;
    private ExceptionHandler exceptionHandler;
    private AssetManager assetManager;
    
    /**
     * creates a frame with a given width and height
     * @param width
     * @param height
     */
    public GameView(int width, int height) {
        super("Virtual Commando 1.0");
        Global.setFrame(this);
        initializeFrame(width, height);
    }
    
    /**
     *
     */
    public void openUsernameDialog() {
        new UsernameDialog(this);
    }
    
    private void initializeFrame(int width, int height) {
        
        exceptionHandler = new ExceptionHandler(this);
        assetManager = new AssetManager(exceptionHandler);
        Global.setExceptionHandler(exceptionHandler);
        Global.setAssetManager(assetManager);
        this.setScreen(new MenuScreen(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLayout(new BorderLayout());
        setVisible(true);
        Global.setWindowWidth(this.getWidth());
        Global.setWindowHeight(this.getHeight());
    }
    /**
     * sets a screen on the frame
     * @param scr
     */
    public void setScreen(Screen scr) {
        if (screen != null) this.remove(screen);
        this.screen = scr;
        this.getContentPane().add(screen, BorderLayout.CENTER);
        this.screen.repaint();
    }

    /**
     * validates the screen by recomputing all of its children components
     */
    @Override
    public void validate() {
        if (screen.getClass() == GameScreen.class) return;
        super.validate();
        this.screen.validate();
    }

    /**
     * returns the current screen
     * @return
     */
    public JPanel getScreen() {
        return screen;
    }
    
    
}

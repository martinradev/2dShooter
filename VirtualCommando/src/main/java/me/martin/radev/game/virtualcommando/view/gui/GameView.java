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
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetManager;
import me.martin.radev.game.virtualcommando.view.gui.dialogs.UsernameDialog;
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
    
    /**
     *
     * @param width
     * @param height
     */
    public GameView(int width, int height) {
        super("Virtual Commando 1.0");
        Global.setFrame(this);
        initializeFrame(width, height);
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
        
    }
    /**
     *
     * @param scr
     */
    public void setScreen(Screen scr) {
        if (screen != null) this.remove(screen);
        this.screen = scr;
        this.getContentPane().add(screen, BorderLayout.CENTER);
        this.screen.repaint();
    }

    /**
     *
     */
    @Override
    public void validate() {
        super.validate();
        Global.setWindowWidth(this.getWidth());
        Global.setWindowHeight(this.getHeight());
        double scalingFactor = (double)this.getWidth() / (double)Global.getDefaultWindowWidth();
        Global.setScalingFactor(scalingFactor);
        this.screen.validate();
    }

    /**
     *
     * @return
     */
    public JPanel getScreen() {
        return screen;
    }
    
    
}

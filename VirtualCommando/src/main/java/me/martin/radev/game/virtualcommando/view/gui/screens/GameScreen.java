/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLayeredPane;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import me.martin.radev.game.virtualcommando.game.graphics.statistics.Statistics;
import me.martin.radev.game.virtualcommando.view.gui.screens.gamescreen.AmmoBar;
import me.martin.radev.game.virtualcommando.view.gui.screens.gamescreen.GameScreenMap;
import me.martin.radev.game.virtualcommando.view.gui.screens.gamescreen.HealthBar;
/**
 *
 * @author Marto
 */
public class GameScreen extends Screen {

    private GameEntityContainer gameEntities;
    private int offsetX;
    private int offsetY;
    private GameScreenMap gameScreenMap;
    private HealthBar healthBar;
    private AmmoBar ammoBar;
    private Statistics scoreBoard;
    private JLayeredPane container;
    private int width;
    private int height;
    /**
     *
     * @param gameEntities
     * @param width
     * @param height
     */
    public GameScreen(GameEntityContainer gameEntities, int width, int height) {
        super();
        
        this.gameEntities = gameEntities;
        this.width = width;
        this.height = height;
        this.offsetX = 0;
        this.offsetY = 0;
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        healthBar = new HealthBar(Global.getFrame().getContentPane().getWidth(), Global.getFrame().getContentPane().getHeight());
        
        
        ammoBar = new AmmoBar(260, 114, Global.getFrame().getContentPane().getWidth(), Global.getFrame().getContentPane().getHeight());
        
        
        gameScreenMap = new GameScreenMap(gameEntities, width, height);

        scoreBoard = new Statistics();

        container = new JLayeredPane();
        container.setSize(width, height);
        container.add(ammoBar);
        container.add(healthBar);
        container.setVisible(true);
        this.setSize(new Dimension(width, height));
        this.add(container);
        
    }   
    
    /**
     *
     * @return
     */
    public AmmoBar getAmmoBar() {
        return ammoBar;
    }

    /**
     *
     * @return
     */
    public HealthBar getHealthBar() {
        return healthBar;
    }

    /**
     *
     * @return
     */
    public GameScreenMap getGameScreenMap() {
        return gameScreenMap;
    }
    
    /**
     *
     */
    public void showScoreBoard() {
        scoreBoard.setVisible(true);
    }
    
    /**
     *
     */
    public void hideScoreBoard() {
        scoreBoard.setVisible(false);
    }

    /**
     *
     * @return
     */
    public Statistics getScoreBoard() {
        return scoreBoard;
    }

    /**
     *
     * @return
     */
    public JLayeredPane getContainer() {
        return container;
    }

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameScreenMap.render(g);
        if (scoreBoard.isVisible()) {
            scoreBoard.render(g);
        }
    }

    
    
}

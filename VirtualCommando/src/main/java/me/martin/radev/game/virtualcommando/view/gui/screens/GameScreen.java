/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens;

import java.awt.Dimension;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
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

    public GameScreen(GameEntityContainer gameEntities, int width, int height) {
        super();
        this.setSize(new Dimension(width, height));
        this.gameEntities = gameEntities;
        
        
        this.offsetX = 0;
        this.offsetY = 0;
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        healthBar = new HealthBar(Global.getFrame().getContentPane().getWidth(), Global.getFrame().getContentPane().getHeight());
        this.add(healthBar);
        
        ammoBar = new AmmoBar(260, 114, Global.getFrame().getContentPane().getWidth(), Global.getFrame().getContentPane().getHeight());
        this.add(ammoBar);
        
        gameScreenMap = new GameScreenMap(gameEntities, width, height);
        this.add(gameScreenMap);

    }
    
    public AmmoBar getAmmoBar() {
        return ammoBar;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public GameScreenMap getGameScreenMap() {
        return gameScreenMap;
    }
    
    
    
}

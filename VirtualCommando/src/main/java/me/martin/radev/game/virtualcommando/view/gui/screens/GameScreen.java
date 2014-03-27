/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
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
    private GameScreenMap gsm;
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
        
        gsm = new GameScreenMap(gameEntities, width, height);
        this.add(gsm);

    }

    public void setOffsetX(int offsetX) {
        gsm.setOffsetX(offsetX);
    }

    public void setOffsetY(int offsetY) {
        gsm.setOffsetY(offsetY);
    }

    public int getOffsetX() {
        return gsm.getOffsetX();
    }

    public int getOffsetY() {
        return gsm.getOffsetY();
    }
    
    public void relativeTranslate(Vector2D direction) {
        gsm.relativeTranslate(direction);
    }

    public AmmoBar getAmmoBar() {
        return ammoBar;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }
    
    
    
}

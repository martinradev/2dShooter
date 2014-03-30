/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.action.PlayerMouseKeyBoardAction;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class MyPlayer extends Player {

    private PlayerMouseKeyBoardAction actionListener;
    
    public MyPlayer() {
        super(PlayerType.NormalPlayer.getMaxHealth(),
                new Vector2D(100d, 100d), PlayerType.NormalPlayer.getWidth(),
                PlayerType.NormalPlayer.getHeight(),
                new Color(1f, 0f, 0f, .0f));
        init();
    }

    private void init() {
        actionListener = new PlayerMouseKeyBoardAction(this);
        Global.getFrame().getScreen().addMouseListener(actionListener.getMouseListener());
        Global.getFrame().getScreen().addKeyListener(actionListener.getKeyListener());
        Global.getFrame().getScreen().addMouseMotionListener(actionListener.getMouseMotionListener());
        Global.getGame().getScreen().getAmmoBar().setTotalAmmo(
                    this.weapon.getTotalAmmu());
        Global.getGame().getScreen().getAmmoBar().setCurrentAmmo(this.weapon.getCurrentAmmuCount());
        
    }

    @Override
    public void processMovement() {
        actionListener.processMovement();
    }

    @Override
    public void processRotation() {
        actionListener.processRotation(actionListener.getCurrentPoint());
    }

    @Override
    public void shoot(Vector2D direction) {
        super.shoot(direction);
        Global.getGame().getScreen().getAmmoBar().setCurrentAmmo(
                this.weapon.getCurrentAmmuCount());
    }

    @Override
    public void processShooting() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        Global.getGame().getScreen().getHealthBar().setPercent(
                (double)super.currentHealth/(double)super.maxHealth);
    }

    @Override
    public void regenerateFully() {
        super.regenerateFully();
        Global.getGame().getScreen().getHealthBar().setPercent(1d);
    }
    
}

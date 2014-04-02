/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import java.awt.event.KeyEvent;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.Settings;
import me.martin.radev.game.virtualcommando.game.unit.action.PlayerMouseKeyBoardAction;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class MyPlayer extends Player {

    private PlayerMouseKeyBoardAction actionListener;

    /**
     *
     */
    public MyPlayer() {
        super(Settings.NAME, PlayerType.NormalPlayer.getMaxHealth(),
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

    /**
     *
     */
    @Override
    public void processMovement() {
        Vector2D direction = new Vector2D(0d, 0d);
        for (Integer key : actionListener.getKeysToProcess()) {
            if (key == KeyEvent.VK_LEFT) {
                direction.translate(-1d, 0);
            }
            if (key == KeyEvent.VK_RIGHT) {
                direction.translate(1d, 0);
            }
            if (key == KeyEvent.VK_UP) {
                direction.translate(0, -1d);
            }
            if (key == KeyEvent.VK_DOWN) {
                direction.translate(0, 1d);
            }
        }
        direction = direction.getUnitVector();
        if (direction.getX() != 0 || direction.getY() != 0) {
            move(direction);
            processRotation();
        } else {
            stopMovement();
        }
    }

    /**
     *
     */
    @Override
    public void processRotation() {
        Vector2D mousePosition = new Vector2D(actionListener.getCurrentPoint());
        Vector2D offset = Global.getGame().getScreen().getGameScreenMap().getScreenOffset();
        mousePosition.translate(offset.getX(), offset.getY());
        Vector2D playerPosition = new Vector2D(this.getBody().getCenter());
        double angle = MathUtil.getAngleBetweenPoints(mousePosition, playerPosition) + Math.PI / 2d;
        rotate(angle);
    }

    /**
     *
     * @param direction
     */
    @Override
    public void shoot(Vector2D direction) {
        super.shoot(direction);
        Global.getGame().getScreen().getAmmoBar().setCurrentAmmo(
                this.weapon.getCurrentAmmuCount());
    }

    /**
     *
     */
    @Override
    public void processShooting() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param damage
     */
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        Global.getGame().getScreen().getHealthBar().setPercent(
                (double) super.currentHealth / (double) super.maxHealth);
    }

    /**
     *
     */
    @Override
    public void regenerateFully() {
        super.regenerateFully();
        Global.getGame().getScreen().getHealthBar().setPercent(1d);
    }
}

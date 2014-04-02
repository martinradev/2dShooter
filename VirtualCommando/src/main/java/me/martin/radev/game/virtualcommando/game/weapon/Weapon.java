/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.animation.Animation;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;

/**
 *
 * @author Marto
 */
public abstract class Weapon {

    private Animation shootingAnimation;
    private Sprite staticWeaponAnimation;
    private int currentAmmuCount;
    private int totalAmmu;
    private Bullet bulletType;

    /**
     *
     * @param bulletType
     * @param totalAmmu
     */
    public Weapon(Bullet bulletType, int totalAmmu) {
        this.bulletType = bulletType;
        this.totalAmmu = this.currentAmmuCount = totalAmmu;
    }
    
    /**
     *
     */
    protected void decrementAmmuAcount() {
        if (currentAmmuCount > 0) --currentAmmuCount;
    }

    /**
     *
     * @return
     */
    public int getCurrentAmmuCount() {
        return currentAmmuCount;
    }

    /**
     *
     * @return
     */
    public int getTotalAmmu() {
        return totalAmmu;
    }

    /**
     *
     * @param currentAmmuCount
     */
    public void setCurrentAmmuCount(int currentAmmuCount) {
        this.currentAmmuCount = currentAmmuCount;
    }

    /**
     *
     * @param totalAmmu
     */
    public void setTotalAmmu(int totalAmmu) {
        this.totalAmmu = totalAmmu;
    }
    
    

    /**
     *
     * @param direction
     * @param position
     * @param player
     * @return
     */
    public abstract Bullet produceBullet(Vector2D direction, Vector2D position, Player player);
}

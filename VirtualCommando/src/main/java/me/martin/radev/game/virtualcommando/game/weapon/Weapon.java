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

    public Weapon(Bullet bulletType, int totalAmmu) {
        this.bulletType = bulletType;
        this.totalAmmu = this.currentAmmuCount = totalAmmu;
    }
    
    protected void decrementAmmuAcount() {
        if (currentAmmuCount > 0) --currentAmmuCount;
    }

    public int getCurrentAmmuCount() {
        return currentAmmuCount;
    }

    public int getTotalAmmu() {
        return totalAmmu;
    }

    public void setCurrentAmmuCount(int currentAmmuCount) {
        this.currentAmmuCount = currentAmmuCount;
    }

    public void setTotalAmmu(int totalAmmu) {
        this.totalAmmu = totalAmmu;
    }
    
    

    public abstract Bullet produceBullet(Vector2D direction, Vector2D position, Player player);
}

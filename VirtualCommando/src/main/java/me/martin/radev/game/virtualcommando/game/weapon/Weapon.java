/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon;

import java.io.File;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.sound.SoundEffect;
import me.martin.radev.game.virtualcommando.view.graphics.animation.Animation;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;

/**
 * Creates a weapon
 * @author Marto
 */
public abstract class Weapon {

    private Animation shootingAnimation;
    private Sprite staticWeaponAnimation;
    private int currentAmmuCount;
    private int totalAmmu;
    private Bullet bulletType;
    protected SoundEffect shootingEffect;
    protected SoundEffect noAmmoEffect;

    /**
     *
     * @param bulletType
     * @param totalAmmu
     */
    public Weapon(Bullet bulletType, int totalAmmu, SoundEffect shootingEffect) {
        this.bulletType = bulletType;
        this.totalAmmu = this.currentAmmuCount = totalAmmu;
        this.shootingEffect = shootingEffect;
        this.noAmmoEffect = new SoundEffect(
                (File) Global.getAssetManager().load(
                AssetType.Sound, "sounds/no_ammo.wav"));
    }
    
    /**
     * Decrements the ammo count after shooting. The ammo count cannot be less than 0
     */
    protected void decrementAmmuAcount() {
        if (currentAmmuCount > 0) --currentAmmuCount;
    }

    /**
     * return the current ammo acount
     * @return
     */
    public int getCurrentAmmuCount() {
        return currentAmmuCount;
    }

    /**
     * returns the total ammo of the weapon
     * @return
     */
    public int getTotalAmmu() {
        return totalAmmu;
    }

    /**
     * sets the current number of bullets
     * @param currentAmmuCount
     */
    public void setCurrentAmmuCount(int currentAmmuCount) {
        this.currentAmmuCount = currentAmmuCount;
    }

    /**
     * sets the upper bound for ammo count
     * @param totalAmmu
     */
    public void setTotalAmmu(int totalAmmu) {
        this.totalAmmu = totalAmmu;
    }
    
    

    /**
     * produces a bullet with a given direction, position to start from and a
     * given owner. The direction must be a unit vector. Setting the owner, means
     * that the bullet cannot hit the player.
     * @param direction
     * @param position
     * @param player
     * @return
     */
    public abstract Bullet produceBullet(Vector2D direction, Vector2D position, Player player);
    
    protected float getFireVolume(Vector2D position) {
        float distance = ((float)MathUtil.distance(position, Global.getGame().getMainPlayer().getBody().getCenter()));
        distance += 70f;
        double gain = 70f/distance;
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20f);
        return dB;
    }
    
}

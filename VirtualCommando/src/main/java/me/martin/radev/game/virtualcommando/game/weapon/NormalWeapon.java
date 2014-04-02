/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon;

import java.io.File;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.NormalBullet;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.sound.SoundEffect;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;

/**
 *
 * @author Marto
 */
public class NormalWeapon extends Weapon {
    
    private static final int totalAmmuCapacity = 5000;
    
    /**
     *
     */
    public NormalWeapon() {
        super(new NormalBullet(), NormalWeapon.totalAmmuCapacity);
    }

    /**
     *
     * @param direction
     * @param position
     * @param player
     * @return
     */
    @Override
    public Bullet produceBullet(Vector2D direction, Vector2D position, Player player) {
        if (super.getCurrentAmmuCount() == 0) return null;
        Bullet b = new NormalBullet();
        b.setDirection(direction);
        b.setPosition(position);
        b.setOwner(player);
        super.decrementAmmuAcount();
        return b;
    }
    
    
    
}

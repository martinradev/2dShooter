/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon;

import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.NormalBullet;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class NormalWeapon extends Weapon {
    
    private static final int totalAmmuCapacity = 50;
    
    public NormalWeapon() {
        super(new NormalBullet(), NormalWeapon.totalAmmuCapacity);
    }

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

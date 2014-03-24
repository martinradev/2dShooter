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
    
    public NormalWeapon() {
        super(new NormalBullet());
    }

    @Override
    public Bullet produceBullet(Vector2D direction, Vector2D position, Player player) {
        Bullet b = new NormalBullet();
        b.setDirection(direction);
        b.setPosition(position);
        b.setOwner(player);
        return b;
    }
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon;

import me.martin.radev.game.virtualcommando.game.weapon.bullet.NormalBullet;

/**
 *
 * @author Marto
 */
public class NormalWeapon extends Weapon {
    
    public NormalWeapon() {
        super(new NormalBullet());
    }
    
}

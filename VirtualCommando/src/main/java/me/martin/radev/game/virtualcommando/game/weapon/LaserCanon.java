/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon;

import java.io.File;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Laser;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.NormalBullet;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.sound.SoundEffect;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;

/**
 *
 * @author Marto
 */
public class LaserCanon extends Weapon {
    
    private static final int totalAmmuCapacity = 100;
    
    /**
     *
     */
    public LaserCanon() {
        super(new Laser(0d), LaserCanon.totalAmmuCapacity,
                new SoundEffect(
                (File) Global.getAssetManager().load(
                AssetType.Sound, "sounds/normalweapon/shoot.wav")));
    }

    /**
     * produces a normal bullet
     * @param direction
     * @param position
     * @param player
     * @return
     */
    @Override
    public Bullet produceBullet(Vector2D direction, Vector2D position, Player player) {
        float dB = super.getFireVolume(position);
        if (super.getCurrentAmmuCount() == 0) {
            super.noAmmoEffect.play(dB);
            return null;
        }
        double angle = 
                MathUtil.getAngleBetweenVectors(direction, new Vector2D(0,1));
        Bullet b = new Laser(angle);
        b.setDirection(direction);
        b.setPosition(position);
        b.setOwner(player);
        super.decrementAmmuAcount();
        super.shootingEffect.play(dB);
        return b;
    }
}

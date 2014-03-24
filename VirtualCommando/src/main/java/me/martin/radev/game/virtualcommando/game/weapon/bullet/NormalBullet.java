/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon.bullet;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;

/**
 *
 * @author Marto
 */
public class NormalBullet extends Bullet {
    
    private static final int DAMAGE = 5;
    private static final double VELOCITY = 2.5d;
    private static final double BULLET_WIDTH = 5d;
    private static final double BULLET_HEIGHT = 5d;
    
    public NormalBullet() {
        super(NormalBullet.DAMAGE, NormalBullet.VELOCITY);
        GraphicalObject go = new GraphicalRectangle(0d, 0d,
                NormalBullet.BULLET_WIDTH, NormalBullet.BULLET_HEIGHT, Color.BLUE);
        super.setObject(go);
    }
    
    public NormalBullet getCopy(Vector2D direction, Player owner) {
        NormalBullet b = new NormalBullet();
        b.setDirection(direction);
        b.setOwner(owner);
        return b;
    }
    
}

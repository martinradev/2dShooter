/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon.bullet;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;

/**
 *
 * @author Marto
 */
public class NormalBullet extends Bullet {
    
    private static final double DAMAGE = 5d;
    private static final double VELOCITY = 5d;
    private static final double BULLET_WIDTH = 5d;
    private static final double BULLET_HEIGHT = 5d;
    
    public NormalBullet() {
        super(NormalBullet.DAMAGE, NormalBullet.VELOCITY);
        GraphicalObject go = new GraphicalRectangle(0d, 0d,
                NormalBullet.BULLET_WIDTH, NormalBullet.BULLET_HEIGHT, Color.BLUE);
        super.setObject(go);
    }
    
}

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
 * A normal bullet. This is the most basic bullet in the game.
 * @author Marto
 */
public class NormalBullet extends Bullet {
    
    private static final int DAMAGE = 10;
    private static final double VELOCITY = 8.5d;
    private static final double BULLET_WIDTH = 5d;
    private static final double BULLET_HEIGHT = 5d;
    
    /**
     *
     */
    public NormalBullet() {
        super(NormalBullet.DAMAGE, NormalBullet.VELOCITY);
        GraphicalObject go = new GraphicalRectangle(0d, 0d,
                NormalBullet.BULLET_WIDTH, NormalBullet.BULLET_HEIGHT, Color.BLUE);
        super.setObject(go);
    }
    
    /**
     * returns a copy of a bullet structure with a given direction and owner
     * @param direction
     * @param owner
     * @return
     */
    public NormalBullet getCopy(Vector2D direction, Player owner) {
        NormalBullet b = new NormalBullet();
        b.setDirection(direction);
        b.setOwner(owner);
        return b;
    }
    
}

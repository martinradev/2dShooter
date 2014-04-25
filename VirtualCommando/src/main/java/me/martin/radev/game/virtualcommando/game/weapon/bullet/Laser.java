/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon.bullet;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;

/**
 *
 * @author Marto
 */
public class Laser extends Bullet {
    
    private static final int DAMAGE = 15;
    private static final double VELOCITY = 8.3d;
    private static final double BULLET_WIDTH = 2d;
    private static final double BULLET_HEIGHT = 20d;
    public Laser(double angle) {
        super(Laser.DAMAGE, Laser.VELOCITY, angle);
        GraphicalRectangle go = new GraphicalRectangle(0d, 0d,
                Laser.BULLET_WIDTH, Laser.BULLET_HEIGHT, Color.BLUE);
        super.setObject(go);
    }
   
    
    public Laser getCopy(Vector2D direction, Player owner) {
        double angle = 
                MathUtil.getAngleBetweenVectors(direction, new Vector2D(0,1));
        Laser b = new Laser(angle);
        b.setDirection(direction);
        b.setOwner(owner);
        return b;
    }
    
    
}

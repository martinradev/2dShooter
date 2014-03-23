/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon.bullet;

import java.awt.Color;
import java.awt.Graphics2D;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public abstract class Bullet implements Cloneable {
    
    private double damage;
    private Vector2D direction;
    private GraphicalObject object;
    private double velocity;
    private Player owner;
    private Vector2D position;
    
    public Bullet(double damage, double velocity) {
        this.damage = damage;
        this.velocity = velocity;
    }

    public void setObject(GraphicalObject object) {
        this.object = object;
    }

    public void setPosition(Vector2D position) {
        
    }
    
    
    
    public void move() {
        object.getBody().translate(direction.getX()*velocity,
                direction.getY()*velocity);
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    
    public void render(Graphics2D g2d, double offsetX, double offsetY) {
        g2d.setColor(Color.yellow);
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon.bullet;

import java.awt.Color;
import java.awt.Graphics2D;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public abstract class Bullet implements Cloneable {
    
    private int damage;
    private Vector2D direction;
    private GraphicalObject object;
    private double velocity;
    private Player owner;
    private Vector2D position;
    
    public Bullet(int damage, double velocity) {
        this.damage = damage;
        this.velocity = velocity;
    }

    public void setObject(GraphicalObject object) {
        this.object = object;
    }

    public void setPosition(Vector2D position) {
        object.getBody().translate(-object.getBody().getCenter().getX(), 
                -object.getBody().getCenter().getY());
        object.getBody().translate(position.getX(), position.getY());
    }
    
    public void move() {
        object.getBody().translate(direction.getX()*velocity,
                direction.getY()*velocity);
        if (Global.getGameFlow().isBulletCollidingWithMap(this)) {
            dispose();
        }
        Player p = Global.getGameFlow().isBulletCollidingWithPlayer(this);
        if (p!=null) {
            p.takeDamage(this.damage);
        }
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
        object.render(g2d, (int)offsetX, (int)offsetY);
    }

    public GraphicalObject getObject() {
        return object;
    }

    public Player getOwner() {
        return owner;
    }
    
    public void dispose() {
        Global.getGame().getGameEntities().getBullets().remove(this);
    }
    
}

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
import me.martin.radev.game.virtualcommando.view.graphics.animation.Animation;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;

/**
 *
 * @author Marto
 */
public abstract class Bullet {
    
    private int damage;
    private Vector2D direction;
    private GraphicalRectangle object;
    private double velocity;
    private Player owner;
    private Vector2D position;
    private final double angle;
    /**
     *
     * @param damage
     * @param velocity
     * @param angle  
     */
    public Bullet(int damage, double velocity, double angle) {
        this.damage = damage;
        this.velocity = velocity;
        this.angle = angle;
    }

    /**
     *
     * @param object
     */
    public void setObject(GraphicalRectangle object) {
        this.object = object;
    }

    /**
     *
     * @param position
     */
    public void setPosition(Vector2D position) {
        object.getBody().translate(-object.getBody().getCenter().getX(), 
                -object.getBody().getCenter().getY());
        object.getBody().translate(position.getX(), position.getY());
    }
    
    /**
     *
     */
    public void move() {
        object.getBody().translate(direction.getX()*velocity,
                direction.getY()*velocity);
        if (Global.getGameFlow().isBulletCollidingWithMap(this)) {
            dispose();
        }
        Player p = Global.getGameFlow().isBulletCollidingWithPlayer(this);
        if (p!=null) {
            p.takeDamage(this.damage);
            if (!p.isPlayerActive()) {
                Global.getGame().getScreen().getScoreBoard().addFrag(owner.getName());
            }
            dispose();
        }
    }

    /**
     *
     * @param direction
     */
    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    
    /**
     *
     * @param g2d
     * @param offsetX
     * @param offsetY
     */
    public void render(Graphics2D g2d, double offsetX, double offsetY) {
        object.render(g2d, (int)offsetX, (int)offsetY, angle);
    }

    /**
     *
     * @return
     */
    public GraphicalObject getObject() {
        return object;
    }

    /**
     *
     * @return
     */
    public Player getOwner() {
        return owner;
    }
    
    /**
     *
     */
    public void dispose() {
        Global.getGame().getGameEntities().getBullets().remove(this);
    }
    
    /**
     *
     * @param direction
     * @param owner
     * @return
     */
    public abstract Bullet getCopy(Vector2D direction, Player owner);
    
}

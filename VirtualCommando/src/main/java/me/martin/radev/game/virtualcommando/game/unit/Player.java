/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;

/**
 *
 * @author Marto
 */
public abstract class Player {

    private GraphicalObject gObject;
    private double angleOffset = 0d;
    private int maxHealth;
    private int currentHealth;
    private double velocity = 2d;

    public Player(int maxHealth, Vector2D startingPosition,
            int gObjectWidth, int gObjectHeight, Color color) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.gObject = new GraphicalRectangle(startingPosition,
                (double)gObjectWidth, (double)gObjectHeight, color);
    }

    public void move(Vector2D direction) {
        gObject.getBody().translate(velocity * direction.getX(), velocity * direction.getY());
    }

    public void shoot(Vector2D direction) {
        System.out.println("Shoot: " + direction );
    }
    
    public void takeDamage(int damage) {
        this.currentHealth -= damage;
        if (this.currentHealth<0) kill();
    }
    
    public void regenerate(int health) {
        this.currentHealth = Math.min(currentHealth + health, maxHealth);
    }
    
    public void kill() {
        
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public GraphicalObject getgObject() {
        return gObject;
    }
    
    public abstract void processMovement();

    
    public void rotate(double angle) {
        this.getgObject().getBody().relativeRotate(
                this.getgObject().getBody().getCenter(),
                angle-angleOffset);
        angleOffset = angle;
        
    }

}

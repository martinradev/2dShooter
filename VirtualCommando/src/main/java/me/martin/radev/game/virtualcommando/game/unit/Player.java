/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.animation.Animation;
import me.martin.radev.game.virtualcommando.view.graphics.animation.LinearAnimation;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;

/**
 *
 * @author Marto
 */
public abstract class Player {

    private GraphicalObject gObject;
    private double angleOffset = 0d;
    private double currentAngleOfRotation = 0d;
    private int maxHealth;
    private int currentHealth;
    private double velocity = 2d;
    private LinearAnimation walkAnimation;
    private Sprite staticSprite;

    public Player(int maxHealth, Vector2D startingPosition,
            int gObjectWidth, int gObjectHeight, Color color) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.gObject = new GraphicalRectangle(startingPosition,
                (double)gObjectWidth, (double)gObjectHeight, color);
        staticSprite = (Sprite) Global.getAssetManager().load(AssetType.Sprite, "soldier/solid.png");
        ((GraphicalRectangle)this.gObject).setSprite(staticSprite);
        walkAnimation = new LinearAnimation();
        walkAnimation.addSprite((Sprite) Global.getAssetManager().load(AssetType.Sprite, "soldier/frame1.png"));
        walkAnimation.addSprite((Sprite) Global.getAssetManager().load(AssetType.Sprite, "soldier/frame2.png"));
        Global.getGame().bind(walkAnimation);
    }

    public void move(Vector2D direction) {
        ((GraphicalRectangle)this.gObject).setSprite(walkAnimation.getCurrent());
        gObject.getBody().translate(velocity * direction.getX(), velocity * direction.getY());
    }
    
    public void stopMovement() {
        ((GraphicalRectangle)this.gObject).setSprite(staticSprite);
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
        currentAngleOfRotation = angle-angleOffset;
        angleOffset = angle;
        
    }

    public double getCurrentAngleOfRotation() {
        return currentAngleOfRotation;
    }

    public double getAngleOffset() {
        return angleOffset;
    }

    
}

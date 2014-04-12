/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.martin.radev.game.virtualcommando.game.Updatable;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;

/**
 * An Animation interface. The animation consists of a series of sprites run in a 
 * specific order.
 * @author Marto
 */
public abstract class Animation implements Updatable {
    
    protected List<Sprite> spriteList;
    
    public Animation(Sprite [] sprites) {
        spriteList.addAll(Arrays.asList(sprites));
    }
    
    public Animation() {
        spriteList = new ArrayList<>();
    }
    
    /**
     * updates the animation by doing a specific action
     */
    public abstract void update();
    /**
     * gets the next sprite for the animation
     */
    public abstract void next();
    /**
     * returns the current sprite
     * @return
     */
    public abstract Sprite getCurrent();
    
    /**
     * adds a new sprite to the sequence
     * @param spr
     */
    public void addSprite(Sprite spr) {
        this.spriteList.add(spr);
    }
    
    public int getSize() {
        return this.spriteList.size();
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.animation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.game.Updatable;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;

/**
 * A linear animation runs all of the sprites in linear fashion.
 * @author Marto
 */
public class LinearAnimation implements Animation, Updatable {

    private List<Sprite> spriteList;
    private int currentIndex;
    private int framesPerSprite;
    private int framesCount;
    
    /**
     * Creates an animation from an array of sprites and framesPerSprite time.
     * @param sprites
     * @param framesPerSprite
     */
    public LinearAnimation(Sprite [] sprites, int framesPerSprite) {
        spriteList.addAll(Arrays.asList(sprites));
        this.framesPerSprite = framesPerSprite;
        this.framesCount = 0;
    }
    
    /**
     * Creates an empty animation where the frames per sprite is the FPS of the game.
     */
    public LinearAnimation() {
        spriteList = new ArrayList<>();
        this.framesPerSprite = Global.getFPS()/6;
    }
    
    /**
     * sets the next sprite for the animation
     */
    @Override
    public void next() {
        if (spriteList.isEmpty()) {
            Global.getExceptionHandler().notificate(
                    ExceptionHelper.IllegalArgumentException.getTitle(),
                    ExceptionHelper.IllegalArgumentException.getMessage());
            return;
        }
        currentIndex = (currentIndex+1)%spriteList.size();
    }

    /**
     * returns the current {@link Sprite}
     * @return
     */
    @Override
    public Sprite getCurrent() {
        return spriteList.get(currentIndex);
    }

    /**
     * updates the animation
     */
    @Override
    public void update() {
        ++framesCount;
        if (framesCount == framesPerSprite) {
            next();
            framesCount = 0;
        }
    }
    
    /**
     * adds a new sprite to the sequence
     * @param spr
     */
    public void addSprite(Sprite spr) {
        this.spriteList.add(spr);
    }
    
}

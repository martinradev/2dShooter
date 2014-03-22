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
 *
 * @author Marto
 */
public class LinearAnimation implements Animation, Updatable {

    private List<Sprite> spriteList;
    private int currentIndex;
    private int framesPerSprite;
    private int framesCount;
    
    public LinearAnimation(Sprite [] sprites, int framesPerSprite) {
        spriteList.addAll(Arrays.asList(sprites));
        this.framesPerSprite = framesPerSprite;
        this.framesCount = 0;
    }
    
    public LinearAnimation() {
        spriteList = new ArrayList<>();
        this.framesPerSprite = Global.getFPS();
    }
    
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

    @Override
    public Sprite getCurrent() {
        return spriteList.get(currentIndex);
    }

    @Override
    public void update() {
        ++framesCount;
        if (framesCount == framesPerSprite) {
            next();
            framesCount = 0;
        }
    }
    
    public void addSprite(Sprite spr) {
        this.spriteList.add(spr);
    }
    
}

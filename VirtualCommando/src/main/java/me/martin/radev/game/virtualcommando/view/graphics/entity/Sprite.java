/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Image;

/**
 * A sprite is a wrapper for an image.
 * @author Marto
 */
public class Sprite {
    
    private Image image;
    
    /**
     * Creates a sprite from an {@link Image} object
     * @param image
     */
    public Sprite(Image image) {
        this.image = image;
    }
    
    /**
     * returns the {@link Image} for this sprite
     * @return
     */
    public Image getImage() {
        return image;
    }
    
    /**
     * returns the width of the sprite
     * @return
     */
    public int getWidth() {
        return this.image.getWidth(null);
    }
    
    /**
     * returns the height of the sprite
     * @return
     */
    public int getHeight() {
        return this.image.getHeight(null);
    }
    
}

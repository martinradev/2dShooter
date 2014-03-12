/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Image;

/**
 *
 * @author Marto
 */
public class Sprite {
    
    private Image image;
    
    public Sprite(Image image) {
        this.image = image;
    }
    
    public Image getImage() {
        return image;
    }
    
}
